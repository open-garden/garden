package com.zipc.garden.job.bp.nlength;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;

import com.zipc.garden.fsm.simulator.FSMSimulatorUtil;
import com.zipc.garden.fsm.simulator.IFSMSimulator;
import com.zipc.garden.job.bp.BPGenerationException;
import com.zipc.garden.job.bp.GenerationCancellationException;
import com.zipc.garden.job.bp.IGenerationHandler;
import com.zipc.garden.job.bp.nlength.CombinationIterator.Combination;
import com.zipc.garden.model.arc.ARCState;
import com.zipc.garden.model.bp.BPBehavior;
import com.zipc.garden.model.bp.BPBehaviorPattern;
import com.zipc.garden.model.bp.BPEvent;
import com.zipc.garden.model.bp.BPFactory;
import com.zipc.garden.model.bp.BPSpec;
import com.zipc.garden.model.bp.BPSpecElement;
import com.zipc.garden.model.bp.BPStateMachine;
import com.zipc.garden.model.bp.BPStep;
import com.zipc.garden.model.bps.BPSInstanceArc;
import com.zipc.garden.model.bps.BPSInstancePseudoStateType;
import com.zipc.garden.model.bps.BPSInstanceState;
import com.zipc.garden.model.bps.BPSInstanceStateMachine;
import com.zipc.garden.model.bps.BPSPathCombinationOption;

/**
 * This class generates behavior patterns based on the path length.
 */
public class NLengthBehaviorPatternGenerator {

    /** Error message when the architecture obtained from the bps file is empty. */
    private static final String Message_FailedGetARC = "Can't load ARC file.";

    /** Error message when the StateMachine to create the pattern does not exist. */
    private static final String Message_FailedGetTargetFSM = "Can't load target StateMachine.";

    /** Error message when an FSM node type that is not supported by the BP generator is specified. */
    private static final String Message_IncludeNotSupportedTypeNodes = "Supported only Finite State Machine that include only an Initial node or State nodes.";

    /** Error message when no one state node exists. */
    private static final String Message_NoStateNodes = "Finiste State Machine must include State node.";

    /** A handler that generates behavior patterns. */
    private List<IGenerationHandler<BPBehaviorPattern>> handlers;

    /**
     * <pre>
     * An option to find a path consisting of transitions from the initial state of all finite state machines included in ARC and generate a BP from a combination of them.
     * ステートマシンの初期状態からの遷移で構成されるパスを，ARCに含まれるすべてのステートマシンに対して求め，それらの組み合わせからBPを生成するオプション
     * </pre>
     */
    private BPSPathCombinationOption option;

    /** An instance of the architecture obtained from the bps file. */
    private BPSInstanceArc architecture;

    /** A StateMachine that creates a pattern. */
    private List<BPSInstanceStateMachine> stateMachines;

    /** Simulator generation logic */
    private IFSMSimulator simulator;

    /**
     * constructor
     */
    public NLengthBehaviorPatternGenerator() {
        handlers = new ArrayList<>();
    }

    /**
     * Configure the behavior pattern generator.
     * @param architecture An instance of the architecture obtained from the bps file.
     * @param option The value to set for {@link #option}.
     * @throws BPGenerationException is thrown if failed load models or include invalid state machines.
     */
    public void configure(BPSInstanceArc architecture, BPSPathCombinationOption option) throws BPGenerationException {
        this.option = option;
        load(architecture);
        validate();
    }

    /**
     * Generate paths of each state machines.
     * @throws GenerationCancellationException
     */
    public void generate() throws BPGenerationException, GenerationCancellationException {
        createSimulator();
        generate(stateMachines, this.option.getPathLength());
    }

    /**
     * Build a simulator environment.
     */
    private void createSimulator() {
        simulator = FSMSimulatorUtil.createSimulator();
        simulator.configure(architecture);
    }

    /**
     * <pre>
     * The specified architecture is set to {@link #architecture}.
     * If the specified architecture is empty or the state machines in the architecture are empty, a BPGenerationException will be thrown.
     * </pre>
     * 
     * @param architecture An instance of the architecture obtained from the bps file.
     * @throws BPGenerationException
     */
    private void load(BPSInstanceArc architecture) throws BPGenerationException {
        this.architecture = architecture;
        if (architecture == null) {
            throw new BPGenerationException(Message_FailedGetARC);
        }

        stateMachines = architecture.getStateMachines().stream().filter(BPSInstanceStateMachine::isEnabled).sorted((o1, o2) -> o1.getOriginalUuid().compareTo(o2.getOriginalUuid()))
                .collect(Collectors.toList());
        if (stateMachines.isEmpty()) {
            throw new BPGenerationException(Message_FailedGetTargetFSM);
        }
    }

    /**
     * <pre>
     * Validate the node type set in the State Machine.
     * If no unsupported node type or state node is set, a BPGenerationException will be thrown.
     * </pre>
     * 
     * @throws BPGenerationException
     */
    private void validate() throws BPGenerationException {
        // only include InitialNode or StateNode
        boolean onlyValidNode = stateMachines.stream().flatMap(stm -> stm.getStates().stream()).map(BPSInstanceState::getType).allMatch(this::supported);
        if (!onlyValidNode) {
            throw new BPGenerationException(Message_IncludeNotSupportedTypeNodes);
        }

        // include at least one State node
        boolean hasStateNodes = stateMachines.stream().flatMap(stm -> stm.getStates().stream()).map(s -> s.getType()).anyMatch(BPSInstancePseudoStateType.NONE::equals);
        if (!hasStateNodes) {
            throw new BPGenerationException(Message_NoStateNodes);
        }
    }

    /**
     * <pre>
     * Checks if the node type is supported by BP Generator.
     * 
     * BPGenerator が対応しているFSMのノード種別
     * </pre>
     * 
     * @param type node type
     * @return True: Supported / false: Not supported
     */
    boolean supported(BPSInstancePseudoStateType type) {
        switch (type) {
        case INITIAL:
        case NONE:
            return true;
        default:
            return false;
        }
    }

    /**
     * A pattern is generated based on the specified state machine and path length.
     * @param targetStateMachines only user selected state machines.
     * @param length Path length
     * @throws GenerationCancellationException
     */
    void generate(List<BPSInstanceStateMachine> targetStateMachines, int length) throws GenerationCancellationException {
        handlers.stream().forEach(IGenerationHandler::start);

        /* generate paths each state machines. */
        NLengthPathGenerator generator = new NLengthPathGenerator();
        generator.setLength(length);
        // memo: 名称の降順に並べることで，名前の昇順にパスが網羅されていくような順序で組み合わせを生成できる(表示時に見た目がきれいになる)
        List<List<Path>> paths = targetStateMachines.stream().sorted((e1, e2) -> e2.getOriginalName().compareTo(e1.getOriginalName()))
                .map(e -> generatePath(generator, e, e.getOriginalName())).collect(Collectors.toList());

        double amount = paths.stream().map(element -> element.size()).map(i -> (double) i).reduce(1.0, (mul, d) -> mul * d).doubleValue();
        handlers.forEach(h -> h.setAmount(amount));

        /* check combination each combinations. */
        List<BPStateMachine> stateMachines = generator.getStateMachines();
        Iterator<Combination<Path>> it = new CombinationIterator<Path>(paths);
        long bpId = 0L;
        while (it.hasNext()) {
            Combination<Path> combination = it.next();
            boolean transitionCompleted = simulate(combination);
            if (transitionCompleted) {
                bpId++;
                double current = bpId;
                handlers.forEach(h -> h.setCurrent(current));
                BPBehaviorPattern generated = convertToBP(combination, stateMachines, bpId);
                for (IGenerationHandler<BPBehaviorPattern> h : handlers) {
                    h.generated(generated);
                }
            }
        }

        handlers.stream().forEach(IGenerationHandler::finished);
    }

    /**
     * Simulates the event transition of the specified element.
     * @param combination specified element.
     * @return true if all Paths reach last state. False if it fails in the middle.
     */
    private boolean simulate(Combination<Path> combination) {
        int maxStep = getPathSize(combination);
        simulator.reset();
        for (int step = 1; step < maxStep; step++) { // Ignore step0 because it equals initial state.
            int index = step;

            Map<String, String> occurredEvents = combination.stream().filter(path -> path.getEventValue(index) != null)
                    .collect(Collectors.toMap(p -> p.getFsmInstanceName(), p -> p.getEventValue(index)));
            simulator.clearAllFsmEvent();

            occurredEvents.forEach((fsmInstanceName, event) -> simulator.setFsmEvent(fsmInstanceName, event));
            simulator.execute();

            // compare simulation result and next path
            boolean transitionCompleted = combination.stream().allMatch(path -> successAllTransition(path, index));
            if (!transitionCompleted) {
                return false;
            }
        }
        return true;
    }

    /**
     * The path of the specified state machine is generated.
     * @param generator A class that executes generation processing with the path length.
     * @param fsm specified state machine
     * @param fsmInstanceName The name of the FiniteStateMachine.
     * @return The path of the specified state machine.
     */
    List<Path> generatePath(NLengthPathGenerator generator, BPSInstanceStateMachine fsm, String fsmInstanceName) {
        generator.set(fsm, fsmInstanceName);
        List<Path> path = generator.generate().stream().peek(spec -> overwriteOwnFsmName(spec, fsmInstanceName)).map(p -> new Path(p, fsmInstanceName))
                .collect(Collectors.toList());
        return path;
    }

    /**
     * Replaces the Name of State and Event in the specified BPSpec with the specified newName.
     * @param spec A BPSpec that has a "state" and an "event" to be replaced.
     * @param newName Name of "State" and "Event" after replacement.
     */
    void overwriteOwnFsmName(BPSpec spec, String newName) {
        spec.getPaths().stream().filter(e -> e.getState() != null).forEach(e -> e.getState().setName(newName));
        spec.getPaths().stream().filter(e -> e.getEvent() != null).forEach(e -> e.getEvent().setName(newName));
    }

    /**
     * Gets the path length of the first element of the specified Combination.
     * @param combination the specified Combination.
     * @return The length of the path of the first element.<br>
     *         最初の要素のパスの長さ（状態数＋イベント数）
     */
    int getPathSize(Combination<Path> combination) {
        return combination.get(0).path.getPaths().size();
    }

    /**
     * Compare the simulation result with the next path.
     * @param path Path info
     * @param stateIndex Step number of the next pass
     * @return True if it matches the expected result. False if they do not match.
     */
    boolean successAllTransition(Path path, int stateIndex) {
        String actualState = simulator.getFsmCurrentState(path.getFsmInstanceName());
        String expectedState = path.getStateValue(stateIndex);

        String actualEvent = simulator.getFsmOccurredEvent(path.getFsmInstanceName());
        actualEvent = actualEvent == null ? "" : actualEvent;
        String expectedEvent = path.getEventValue(stateIndex);

        String preState = path.getStateValue(stateIndex - 1);
        expectedEvent = preState + "->" + expectedEvent;
        return actualEvent.equals(expectedEvent) && actualState.equals(expectedState) && simulator.isSelfTransitionSuccess(path.getFsmInstanceName());
    }

    /**
     * Convert the created path to BP Behavior Pattern and return it.
     * @param combination The combination of generated paths.
     * @param fsms The BPStateMachine referenced by the generated path.
     * @param bpId Pattern no
     * @return After converting the path.
     */
    BPBehaviorPattern convertToBP(Combination<Path> combination, List<BPStateMachine> fsms, long bpId) {
        BPBehaviorPattern bp = BPFactory.eINSTANCE.createBPBehaviorPattern();
        bp.setId(Long.toString(bpId));
        Map<BPStateMachine, BPBehavior> referMap = new HashMap<>();

        // Make reference Behavior and StateMachine
        fsms.stream().map(fsm -> {
            BPBehavior behavior = BPFactory.eINSTANCE.createBPBehavior();
            behavior.setStateMachineRef(fsm);
            referMap.put(fsm, behavior);
            return behavior;
        }).forEach(bp.getBehaviors()::add);

        // Set Step to Behavior
        for (Path path : combination) {
            BPBehavior behavior = referMap.get(path.getStateMachineRef());
            behavior.getSteps().addAll(path.toBPSteps());
        }

        return bp;
    }

    /**
     * Adds the specified handler to {@link #handlers}.
     * @param handler specified handler
     */
    public void addGenerationHandler(IGenerationHandler<BPBehaviorPattern> handler) {
        handlers.add(handler);
    }

    /**
     * for JUnit. Sets the specified IFSMSimulator to {@link #simulator}.
     * @param simulator
     */
    void setSimulator(IFSMSimulator simulator) {
        this.simulator = simulator;
    }

    /**
     * The path from the initial state to the state of the final step is managed.
     */
    class Path {

        /**
         * <pre>
         * A component of the path.
         * 
         * パスの構成要素
         * </pre>
         */
        private BPSpec path;

        /**
         * <pre>
         * The name corresponding to the state machine from which {@link #path} was generated ({@link ARCState#getName()}).
         * 
         * pathの生成元となったステートマシンと対応する{@link ARCState#getName()}．
         * </pre>
         */
        private String fsmInstanceName;

        /**
         * constructor.
         * @param path The value to set for {@link #path}.
         * @param fsmInstanceName The value to set for {@link #fsmInstanceName}.
         */
        Path(BPSpec path, String fsmInstanceName) {
            this.path = path;
            this.fsmInstanceName = fsmInstanceName;
        }

        /**
         * Gets the element of {@link #path} at the specified position.
         * @param index the specified position.
         * @return Element of {@link BPSpec#getPaths()}
         */
        public BPSpecElement get(int index) {
            return path.getPaths().get(index);
        }

        /**
         * Gets the state machine name.
         * @return {@link #fsmInstanceName}
         */
        public String getFsmInstanceName() {
            return fsmInstanceName;
        }

        /**
         * <pre>
         * Convert {@link #path} to BPStep.
         * If the step remains in its original state because the event did not occur, that part is excluded and returned.
         * 
         * イベントが発生しなかったために元の状態にとどまっているようなステップになった場合，その部分は除外して返却する．
         * 
         * e.g. State1 -> null      -> State1    -> event1 -> State2
         *  ==> State1 -> emptyStep -> emptyStep -> event1 -> State2
         * </pre>
         * 
         * @return
         */
        public List<BPStep> toBPSteps() {
            List<BPStep> steps = this.path.getPaths().stream().map(this::toBPStep).collect(Collectors.toList());

            // remove step of null event and the next step
            for (Iterator<BPStep> it = steps.iterator(); it.hasNext();) {
                BPStep step = it.next();
                if (step.getEvent() == null) {
                    continue;
                }
                BPEvent event = step.getEvent();
                if (event.getValue() == null) {
                    step.setEvent(null);
                }
            }
            return steps;
        }

        /**
         * Creates and returns a BPStep with the State and Event information of the specified BPSpecElement.
         * @param element specified BPSpecElement
         * @return BPStep
         */
        private BPStep toBPStep(BPSpecElement element) {
            BPStep step = BPFactory.eINSTANCE.createBPStep();
            step.setState(element.getState());
            step.setEvent(element.getEvent());
            return step;
        }

        /**
         * Gets the value of the state for the specified step number.
         * @param index specified step number.
         * @return the value of the state
         */
        public String getEventValue(int index) {
            return get(index).getEvent().getValue();
        }

        /**
         * Gets the value of the event for the specified step number.
         * @param index specified step number.
         * @return the value of the event
         */
        public String getStateValue(int index) {
            return get(index).getState().getValue();
        }

        /**
         * <pre>
         * Get the BPStateMachine, which is the parent object of "State" of {@link #path}.
         * If there is no "State", get the BPStateMachine from the "Event".
         * If neither is present, null is returned.
         * </pre>
         * 
         * @return BPStateMachine or null
         */
        public BPStateMachine getStateMachineRef() {
            Predicate<EObject> notNull = eObj -> eObj != null;
            Function<EObject, BPStateMachine> cast = eObj -> (BPStateMachine) eObj;
            Function<EObject, EObject> container = EObject::eContainer;

            Optional<BPStateMachine> fsm;
            fsm = path.getPaths().stream().filter(p -> p.getState() != null).map(BPSpecElement::getState).map(container).filter(notNull).map(cast).findFirst();
            if (fsm.isPresent()) {
                return fsm.get();
            }
            fsm = path.getPaths().stream().filter(p -> p.getEvent() != null).map(BPSpecElement::getEvent).map(container).filter(notNull).map(cast).findFirst();
            return fsm.orElse(null);
        }

        /**
         * Returns the string of {@link #path}.
         * @return path.toString()
         */
        @Override
        public String toString() {
            return path.toString();
        }
    }

}
