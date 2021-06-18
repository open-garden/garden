package com.zipc.garden.model;

import java.util.Optional;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

import com.zipc.garden.model.bp.BPBehavior;
import com.zipc.garden.model.bp.BPBehaviorPattern;
import com.zipc.garden.model.bp.BPEvent;
import com.zipc.garden.model.bp.BPFactory;
import com.zipc.garden.model.bp.BPRoot;
import com.zipc.garden.model.bp.BPSetting;
import com.zipc.garden.model.bp.BPSpec;
import com.zipc.garden.model.bp.BPSpecElement;
import com.zipc.garden.model.bp.BPState;
import com.zipc.garden.model.bp.BPStateMachine;
import com.zipc.garden.model.bp.BPStep;

public class BPCreator {

    public static class BPBuilder {
        BPRoot root;

        public BPBuilder() {
            root = BPFactory.eINSTANCE.createBPRoot();
        }

        public BPBuilder addPattern(BPSetting setting, String id) {
            createBehaviorPattern(setting, id);
            return this;
        }

        public BPBuilder addBehavior(BPBehaviorPattern parent, BPStateMachine fsm) {
            createBehavior(parent, fsm);
            return this;
        }

        public BPBuilder addBehavior(BPSetting setting, String bpId, String fsmId) {
            return addBehavior(getPattern(setting, bpId), getStateMachine(setting, fsmId));
        }

        public BPBuilder addStateMachine(BPSetting setting, String id, String name) {
            createStateMachine(setting, id, name);
            return this;
        }

        public BPBuilder addState(BPStateMachine parent, String value) {
            createState(parent, parent.getFsmId(), value);
            return this;
        }

        public BPBuilder addEvent(BPStateMachine parent, String value) {
            createEvent(parent, parent.getFsmId(), value);
            return this;
        }

        public BPBuilder addStateStep(BPBehavior parent, String value) {
            if (value == null) {
                createStep(parent, (BPState) null);
            } else {
                Optional<BPState> state = parent.getStateMachineRef().getStates().stream().filter(s -> s.getValue().equals(value)).findFirst();
                if (state.isPresent()) {
                    createStep(parent, state.get());
                } else {
                    BPStateMachine fsm = parent.getStateMachineRef();
                    createStep(parent, createState(fsm, fsm.getFsmId(), value));
                }
            }
            return this;
        }

        public BPBuilder addEventStep(BPBehavior parent, String value) {
            if (value == null) {
                createStep(parent, (BPEvent) null);
            } else {
                Optional<BPEvent> event = parent.getStateMachineRef().getEvents().stream().filter(e -> e.getValue().equals(value)).findFirst();
                if (event.isPresent()) {
                    createStep(parent, event.get());
                } else {
                    BPStateMachine fsm = parent.getStateMachineRef();
                    createStep(parent, createEvent(fsm, fsm.getFsmId(), value));
                }
            }
            return this;
        }

        public BPRoot get() {
            return root;
        }

        public BPStateMachine getStateMachine(BPSetting setting, String id) {
            return setting.getStateMachines().stream().filter(fsm -> fsm.getFsmId().equals(id)).findFirst().orElse(null);
        }

        public BPBehaviorPattern getPattern(BPSetting setting, String id) {
            return setting.getPattern().stream().filter(bp -> bp.getId().equals(id)).findFirst().orElse(null);
        }

        public BPBehavior getBehavior(BPSetting setting, String bpId, String fsmId) {
            return setting.getPattern().stream().filter(bp -> bp.getId().equals(bpId)).flatMap(bp -> bp.getBehaviors().stream())
                    .filter(b -> b.getStateMachineRef().getFsmId().equals(fsmId)).findFirst().orElse(null);
        }
    }

    public static BPBehaviorPattern createBehaviorPattern(BPSetting setting, String id) {
        BPBehaviorPattern bp = BPFactory.eINSTANCE.createBPBehaviorPattern();
        bp.setId(id);
        setting.getPattern().add(bp);
        return bp;
    }

    public static BPBehavior createBehavior(BPBehaviorPattern parent, BPStateMachine fsm) {
        BPBehavior b = BPFactory.eINSTANCE.createBPBehavior();
        b.setStateMachineRef(fsm);
        parent.getBehaviors().add(b);
        return b;
    }

    public static BPStateMachine createStateMachine(BPSetting setting, String id, String name) {
        BPStateMachine fsm = BPFactory.eINSTANCE.createBPStateMachine();
        fsm.setFsmId(id);
        fsm.setName(name);
        setting.getStateMachines().add(fsm);
        return fsm;
    }

    public static BPState createState(BPStateMachine parent, String name, String value) {
        BPState state = BPFactory.eINSTANCE.createBPState();
        state.setName(name);
        state.setValue(value);
        parent.getStates().add(state);
        return state;
    }

    public static BPEvent createEvent(BPStateMachine parent, String name, String value) {
        BPEvent event = BPFactory.eINSTANCE.createBPEvent();
        event.setName(name);
        event.setValue(value);
        parent.getEvents().add(event);
        return event;
    }

    public static BPStep createStep(BPBehavior parent, BPState state) {
        BPStep bpStep = BPFactory.eINSTANCE.createBPStep();
        bpStep.setState(state);
        parent.getSteps().add(bpStep);
        return bpStep;
    }

    public static BPStep createStep(BPBehavior parent, BPEvent event) {
        BPStep bpStep = BPFactory.eINSTANCE.createBPStep();
        bpStep.setEvent(event);
        parent.getSteps().add(bpStep);
        return bpStep;
    }

    /**
     * Create BPSpec that has states and events like below order.<br/>
     * {@code states[0]}, {@code events[0]}, {@code states[1]}, {@code events[1]}, ..., {@code states[n-1]},
     * {@code events[n-1]}, {@code states[n]}
     * @param fsmId TODO
     * @param states パスに含む状態．前から順番に指定する．
     * @param events パスに含むイベント．前から順に指定する． 状態よりもひとつ少ないように指定すること．
     * @param fsmId
     * @return
     */
    public static BPSpec createBPSpec(String fsmId, String[] states, String[] events) {
        BPSpec spec = BPFactory.eINSTANCE.createBPSpec();
        createBPStateElement(spec, fsmId, states[0]);
        IntStream.range(0, events.length).forEach(i -> {
            createBPEventElement(spec, fsmId, events[i]);
            createBPStateElement(spec, fsmId, states[i + 1]);
        });

        return spec;
    }

    public static BPSpecElement createBPStateElement(BPSpec parent, String name, String value) {
        BPSpecElement sElem = BPFactory.eINSTANCE.createBPSpecElement();
        BPState state = BPFactory.eINSTANCE.createBPState();
        state.setName(name);
        state.setValue(value);
        sElem.setState(state);
        parent.getPaths().add(sElem);
        return sElem;
    }

    public static BPSpecElement createBPEventElement(BPSpec parent, String name, String value) {
        BPSpecElement eElem = BPFactory.eINSTANCE.createBPSpecElement();
        BPEvent event = BPFactory.eINSTANCE.createBPEvent();
        event.setName(name);
        event.setValue(value);
        eElem.setEvent(event);
        parent.getPaths().add(eElem);
        return eElem;
    }

    public static void createEmptyElement(BPSpec parent) {
        parent.getPaths().add(BPFactory.eINSTANCE.createBPSpecElement());
    }

    public static BPSpecElement createBPTransitionElement(BPSpec parent, String name, String value, boolean isNLengthBehaviorPattern) {
        String[] values = value.split("»");
        BPSpecElement eElem = BPFactory.eINSTANCE.createBPSpecElement();
        if (!StringUtils.isEmpty(values[0])) {
            BPEvent event = BPFactory.eINSTANCE.createBPEvent();
            event.setName(name);
            event.setValue(values[0]);
            eElem.setEvent(event);
        } else {
            if (isNLengthBehaviorPattern) {
                eElem.setEvent(null);
            } else {
                BPEvent event = BPFactory.eINSTANCE.createBPEvent();
                event.setName(name);
                event.setValue(null);
                eElem.setEvent(event);
            }
        }
        BPState state = BPFactory.eINSTANCE.createBPState();
        state.setName(name);
        state.setValue(values[1]);
        eElem.setState(state);
        parent.getPaths().add(eElem);
        return eElem;
    }

    public static BPSpec createBPSpec(String fsmId, String initialState, String[] transitions) {
        return createBPSpec(fsmId, initialState, transitions, false);
    }

    public static BPSpec createBPSpec(String fsmId, String initialState, String[] transitions, boolean isNLengthBehaviorPattern) {
        BPSpec spec = BPFactory.eINSTANCE.createBPSpec();
        createBPStateElement(spec, fsmId, initialState);
        IntStream.range(0, transitions.length).forEach(i -> {
            createBPTransitionElement(spec, fsmId, transitions[i], isNLengthBehaviorPattern);
        });
        return spec;
    }
}
