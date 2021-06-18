package com.zipc.garden.job.bp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;

import com.zipc.garden.model.bp.BPEvent;
import com.zipc.garden.model.bp.BPFactory;
import com.zipc.garden.model.bp.BPSpec;
import com.zipc.garden.model.bp.BPSpecElement;
import com.zipc.garden.model.bp.BPState;
import com.zipc.garden.model.bp.BPStateMachine;
import com.zipc.garden.model.bps.BPSInstancePseudoStateType;
import com.zipc.garden.model.bps.BPSInstanceState;
import com.zipc.garden.model.bps.BPSInstanceStateMachine;
import com.zipc.garden.model.bps.BPSInstanceTransition;

/**
 * Of the concrete paths of Behavior Pattern, the path for one FSM is generated.
 */
public abstract class PathGenerator implements IPathGenerator {

    /** A state machine that generates a path. */
    protected BPSInstanceStateMachine target;

    /** The name of the state machine that will generate the path. */
    protected String targetName;

    /** The setting destination of the generated path information. */
    protected List<BPSpec> paths;

    /** A cache of state machines that is temporarily created when the path is generated. */
    protected BPStateMachineCache cache;

    /**
     * <pre>
     * A flag that indicates whether to generate a path that stays in the same state (no event occurs).
     * If True, generate a path that stays in the same state.
     * </pre>
     */
    private boolean allowToStayOwnState;

    /**
     * constructor.
     */
    public PathGenerator() {
        cache = new BPStateMachineCache();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(BPSInstanceStateMachine target, String name) {
        this.target = target;
        this.targetName = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BPSpec> generate() {
        paths = new ArrayList<>();
        doGenerate();
        return paths;
    }

    /**
     * <pre>
     * Generate a path.
     * Register the generated path with {@link #addPath(BPSpec)}.
     * 
     * パスを生成する.
     * 生成したパスは {@link #addPath(BPSpec)}で登録すること．
     * </pre>
     */
    protected abstract void doGenerate();

    /**
     * Generates a path to the end state based on the specified initial state and path length.
     * @param state Initial state.
     * @param n the length of the path to be generated.
     */
    protected void generate(BPSInstanceState state, int n) {
        List<String> nameList = new ArrayList<>();
        BPSInstanceStateMachine target = get();
        if (!target.getStates().isEmpty()) {
            // 名称のリスト作成
            AtomicInteger index = new AtomicInteger();
            for (BPSInstanceState bpsState : target.getStates()) {
                if (BPSInstancePseudoStateType.NONE == bpsState.getType()) {
                    nameList.add(index.getAndIncrement(), bpsState.getOriginalName());
                }
            }

            PathTraversal graph = new PathTraversal(target.getStates().size() - 1, n, nameList);

            for (BPSInstanceState bpsState : target.getStates()) {
                if (BPSInstancePseudoStateType.NONE != bpsState.getType())
                    continue;

                // 自ノードへのStay遷移を追加
                if (isAllowToStayOwnState()) {
                    graph.addEdge(nameList.indexOf(bpsState.getOriginalName()), null, nameList.indexOf(bpsState.getOriginalName()));
                }

                // 遷移を追加
                for (BPSInstanceTransition bpsTransition : bpsState.getTransitions()) {
                    if (bpsTransition.isEnabled()) {
                        graph.addEdge(nameList.indexOf(bpsTransition.getSource().getOriginalName()), bpsTransition.getEvent(),
                                nameList.indexOf(bpsTransition.getTarget().getOriginalName()));
                    }
                }
            }

            // 初期ノードから各ノードへのパスを計算
            for (BPSInstanceState bpsState : target.getStates()) {
                if (BPSInstancePseudoStateType.NONE == bpsState.getType())
                    graph.printAllPaths(nameList.indexOf(state.getOriginalName()), nameList.indexOf(bpsState.getOriginalName()));
            }
        }
    }

    /**
     * Gets the state machine that will generate the path.
     * @return {@link #target}.
     */
    protected BPSInstanceStateMachine get() {
        return this.target;
    }

    /**
     * Adds the specified BPSpec to {@link #paths}.
     * @param spec specified BPSpec
     */
    protected void addPath(BPSpec spec) {
        this.paths.add(spec);
    }

    /**
     * <pre>
     * {@inheritDoc}
     * 
     * Since it is not supported, 1 will be returned.
     * </pre>
     * 
     * @return 1
     */
    @Override
    public int calculateNumberOfScripts(int numOfMaxSpecParScript) {
        return 1;
    }

    /**
     * {@inheritDoc}
     * @return {@link BPStateMachineCache#getStateMachines()}
     */
    @Override
    public List<BPStateMachine> getStateMachines() {
        return cache.getStateMachines();
    }

    /**
     * <pre>
     * Gets a flag to generate or not generate a path that stays in the same state (no event occurs).
     * 
     * 同じ状態にとどまる（イベントが発生しない）ようなパスを生成するかしないかを示すフラグを取得する
     * </pre>
     * 
     * @return {@link #allowToStayOwnState}
     */
    public boolean isAllowToStayOwnState() {
        return allowToStayOwnState;
    }

    /**
     * <pre>
     * Set a flag to indicate whether to generate a path that stays in the same state (no event occurs)
     * 
     * 同じ状態にとどまる（イベントが発生しない）ようなパスを生成するかしないかを示すフラグを設定する
     * </pre>
     * 
     * @param allowToStayOwnState true: Generate. false: Do not generate (default)
     */
    public void setAllowToStayOwnState(boolean allowToStayOwnState) {
        this.allowToStayOwnState = allowToStayOwnState;
    }

    /**
     * A cache of state machines that is temporarily created when the path is generated.
     */
    class BPStateMachineCache {

        /**
         * <pre>
         * For search StateMachine
         *  Key : The name of the state machine that will generate the path. 
         *  Value : A state machine generated based on the key.
         * </pre>
         */
        Map<String, BPStateMachine> fsms = new TreeMap<>((o1, o2) -> o1.compareTo(o2));

        /**
         * <pre>
         * For search state.
         *  Key: StateMachine
         *  Value: Element that has BPState (Key: State name / Value : BPState)
         * </pre>
         */
        Map<BPStateMachine, Map<String, BPState>> stateCache = new HashMap<>();

        /**
         * <pre>
         * For search event.
         *  Key: StateMachine
         *  Value: Element that has BPEvent (Key: Event name / Value : BPEvent)
         * </pre>
         */
        Map<BPStateMachine, Map<String, BPEvent>> eventCache = new HashMap<>();

        /**
         * Gets all state machines in {@link #fsms}.
         * @return All state machines in {@link #fsms}.
         */
        public List<BPStateMachine> getStateMachines() {
            return new ArrayList<>(fsms.values());
        }

        /**
         * Create new BPSpecElement that has found BPState. If BPState doesn't exist, create one.
         * @param fsmName State machine original uuid.
         * @param stateName Initial state name
         * @return BPSpecElement with initial state
         */
        BPSpecElement findOrCreateState(String fsmName, String stateName) {
            BPStateMachine fsm = getFsm();
            Map<String, BPState> cache = stateCache.get(fsm);
            BPSpecElement element = BPFactory.eINSTANCE.createBPSpecElement();
            BPState s = cache.get(stateName);
            if (s == null) {
                s = BPFactory.eINSTANCE.createBPState();
                s.setName(fsmName);
                s.setValue(stateName);
                fsm.getStates().add(s);
                cache.put(stateName, s);
            }
            element.setState(s);
            return element;
        }

        /**
         * Create new BPSpecElement that has found BPEvent. If BPEvent doesn't exist, create one.
         * @param fsmName State machine original uuid.
         * @param stateValue state name
         * @param eventValue event name
         * @return BPSpecElement with state and event.
         */
        BPSpecElement findOrCreateEvent(String fsmName, String stateValue, String eventValue) {
            BPStateMachine fsm = getFsm();

            // get state
            Map<String, BPState> stateMap = stateCache.get(fsm);
            BPSpecElement element = BPFactory.eINSTANCE.createBPSpecElement();
            BPState s = stateMap.get(stateValue);
            if (s == null) {
                s = BPFactory.eINSTANCE.createBPState();
                s.setName(fsmName);
                s.setValue(stateValue);
                fsm.getStates().add(s);
                stateMap.put(stateValue, s);
            }
            element.setState(s);

            // get event
            Map<String, BPEvent> eventMap = eventCache.get(fsm);
            BPEvent e = eventMap.get(eventValue);
            if (e == null) {
                e = BPFactory.eINSTANCE.createBPEvent();
                e.setName(fsmName);
                e.setValue(eventValue);
                fsm.getEvents().add(e);
                eventMap.put(eventValue, e);
            }
            element.setEvent(e);
            return element;
        }

        /**
         * <pre>
         * Get the state machine that matches {@link #targetName} from {@link #fsms}.
         * If there is no match, create a new state machine and put it in {@link #fsms}, {@link #stateCache} and {@link #eventCache}.
         * </pre>
         * 
         * @return The acquired or created state machine.
         */
        private BPStateMachine getFsm() {
            BPStateMachine fsm = fsms.get(targetName);
            if (fsm == null) {
                fsm = BPFactory.eINSTANCE.createBPStateMachine();
                fsm.setFsmId(get().getOriginalUuid());
                fsm.setName(targetName);
                fsms.put(targetName, fsm);
                stateCache.put(fsm, new HashMap<>());
                eventCache.put(fsm, new HashMap<>());
            }
            return fsm;
        }
    }

    /**
     * A class that generates a path from the initial state to the end state with the combination of FSM.
     */
    class PathTraversal {

        /** The number of states in the state machine. */
        private int vertices;

        /** The depth of the path to create. */
        private int depth;

        /**
         * <pre>
         * The state transition and the associated event name.
         *  key: event name
         *  value: target state
         *  arrays: source state
         * </pre>
         */
        private LinkedHashMap<String, Integer>[] adjMap;

        /** List of state names. */
        private List<String> additionalInfoList;

        /**
         * <pre>
         * constructor.
         * Initialize the following field variables.
         *  {@link #vertices}
         *  {@link #depth}
         *  {@link #additionalInfoList}
         *  {@link #adjMap}
         * </pre>
         * 
         * @param vertices The value to set to {@link #vertices}.
         * @param depth The value to set to {@link #depth}.
         * @param additionalInfoList The value to set to {@link #additionalInfoList}.
         */
        public PathTraversal(int vertices, int depth, List<String> additionalInfoList) {
            this.vertices = vertices;
            this.depth = depth;
            this.additionalInfoList = additionalInfoList;
            initAdjMap();
        }

        /**
         * Initialize {@link #adjMap}.
         */
        @SuppressWarnings("unchecked")
        private void initAdjMap() {
            adjMap = new LinkedHashMap[vertices];

            for (int i = 0; i < vertices; i++) {
                adjMap[i] = new LinkedHashMap<>();
            }
        }

        /**
         * Add the transition information to {@link #adjMap}.
         * @param source The position of the transition source.
         * @param event Transition event name
         * @param target The position of the transition target.
         */
        public void addEdge(int source, String event, int target) {
            adjMap[source].put(event, target);
        }

        /**
         * Generates a path from the initial state to the finished state.
         * @param source the initial state
         * @param target the finished state
         */
        public void printAllPaths(int source, int target) {
            ArrayList<String> pathList = new ArrayList<>();

            // add source to path[]
            pathList.add(additionalInfoList.get(source));

            // Call recursive utility
            printAllPathsUtil(source, target, pathList);
        }

        /**
         * Create a path from the initial state to the end state and execute the {@link #addLocalPath(localPathList)} process.
         * @param source the initial state
         * @param target the finished state
         * @param localPathList Path information from the initial state to the end state is created here. <br>
         *            The first round is only the initial state.
         */
        private void printAllPathsUtil(Integer source, Integer target, List<String> localPathList) {

            if (source.equals(target) && localPathList.size() - 1 == depth) {
                addLocalPath(localPathList);
                return;
            }

            // Recur for all the vertices
            // adjacent to current vertex
            String tempLocalPath;
            for (Map.Entry<String, Integer> entry : adjMap[source].entrySet()) {
                if (localPathList.size() - 1 < depth) {
                    // build current node
                    tempLocalPath = StringUtils.isEmpty(entry.getKey()) ? "»" + additionalInfoList.get(entry.getValue())
                            : entry.getKey() + "»" + additionalInfoList.get(entry.getValue());
                    // store current node in path[]
                    localPathList.add(tempLocalPath);
                    printAllPathsUtil(entry.getValue(), target, localPathList);

                    // remove current node in path[]
                    localPathList.remove(localPathList.size() - 1);
                }
            }
        }

        /**
         * Creates a BPSpec based on the specified localPathList and adds it to {@link #paths}.
         * @param localPathList Path information from the initial state to the end state.
         */
        private void addLocalPath(List<String> localPathList) {
            if (!localPathList.isEmpty()) {
                BPSpec bpSpec = BPFactory.eINSTANCE.createBPSpec();
                for (String first : localPathList) {
                    BPSpecElement stateElement = cache.findOrCreateState(get().getOriginalUuid(), first);
                    bpSpec.getPaths().add(stateElement);
                    break;
                }

                String[] localPath;
                for (int i = 1; i < localPathList.size(); i++) {
                    localPath = localPathList.get(i).split("»");
                    if (localPath.length == 2) {
                        BPSpecElement stateElement = cache.findOrCreateEvent(get().getOriginalUuid(), localPath[1], StringUtils.isEmpty(localPath[0]) ? null : localPath[0]);
                        bpSpec.getPaths().add(stateElement);
                    }
                }
                if (!bpSpec.getPaths().isEmpty()) {
                    addPath(bpSpec);
                }
            }
        }

    }
}
