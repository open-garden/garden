package com.zipc.garden.model;

import com.zipc.garden.model.bps.BPSFactory;
import com.zipc.garden.model.bps.BPSInstancePseudoStateType;
import com.zipc.garden.model.bps.BPSInstanceState;
import com.zipc.garden.model.bps.BPSInstanceStateMachine;
import com.zipc.garden.model.bps.BPSInstanceTransition;
import com.zipc.garden.model.bps.BPSVariable;

public class BPSSTMCreator {

    public static class STMBuilder {
        final BPSInstanceStateMachine bpsStm;

        public STMBuilder(String id, String name) {
            bpsStm = BPSFactory.eINSTANCE.createBPSInstanceStateMachine();
            bpsStm.setOriginalUuid(id);
            bpsStm.setOriginalName(name);
        }

        public BPSInstanceStateMachine get() {
            return bpsStm;
        }

        /**
         * 指定RegionにStateを追加する
         * @param regionIndex
         * @param name
         * @return
         */
        public STMBuilder addState(String name) {
            createState(bpsStm, name);
            return this;
        }

        private BPSInstanceState initialState;

        public STMBuilder addInitial() {
            initialState = createNode(bpsStm, BPSInstancePseudoStateType.INITIAL);
            bpsStm.setInitialState(initialState);
            return this;
        }

        /**
         * @return created initial node or {@code null} if not created.
         */
        public BPSInstanceState getInitial() {
            return initialState;
        }

        private BPSInstanceState getState(int index) {
            return bpsStm.getStates().get(index);
        }

        /**
         * 一致する名称のStateを取得する
         * @param name
         * @return
         */
        public BPSInstanceState getState(String name) {
            return bpsStm.getStates().stream().filter(s -> name.equals(s.getOriginalName())).findFirst().orElse(null);
        }

        /**
         * 指定のState間にTransitionを追加する
         * @param source
         * @param target
         * @param event
         * @return
         */
        public STMBuilder addTransition(int source, int target, String event) {
            return addTransition(getState(source), getState(target), event);
        }

        /**
         * 指定のState間にTransitionを追加する
         * @param source
         * @param target
         * @param event
         * @return
         */
        public STMBuilder addTransition(BPSInstanceState source, BPSInstanceState target, String event) {
            createTransition(source, target, event);
            return this;
        }

        /**
         * 変数を追加する
         * @param name
         * @param type
         * @return
         */
        public STMBuilder addVariable(String name, String type) {
            createVariable(bpsStm, name, type);
            return this;
        }
    }

    /**
     * {@link BPSInstancePseudoStateType#NONE} 以外のStateノードを作成することを目的としたメソッド． {@link BPSInstancePseudoStateType#NONE}
     * 以外のStateノードを作成することを目的としたメソッド．
     * @param container
     * @param type
     * @return
     */
    public static BPSInstanceState createNode(BPSInstanceStateMachine container, BPSInstancePseudoStateType type) {
        BPSInstanceState st = createState(container, null);
        st.setType(type);
        return st;
    }

    public static BPSInstanceState createState(BPSInstanceStateMachine owner, String name) {
        BPSInstanceState st = BPSFactory.eINSTANCE.createBPSInstanceState();
        st.setOriginalName(name);
        owner.getStates().add(st);
        return st;
    }

    public static BPSInstanceTransition createTransition(BPSInstanceState source, BPSInstanceState target, String event) {
        return createTransition(source, target, event, 0);
    }

    public static BPSInstanceTransition createTransition(BPSInstanceState source, BPSInstanceState target, String event, int priority) {
        BPSInstanceTransition t = BPSFactory.eINSTANCE.createBPSInstanceTransition();
        t.setSource(source);
        t.setTarget(target);
        t.setEvent(event);
        t.setPriority(priority);
        source.getTransitions().add(t);
        return t;
    }

    /**
     * @param owner
     * @param name
     * @param type
     */
    public static void createVariable(BPSInstanceStateMachine owner, String name, String type) {
        BPSVariable var = BPSFactory.eINSTANCE.createBPSVariable();
        var.setName(name);
        var.setType(type);
        if (owner != null) {
            owner.getVariables().add(var);
        }
    }
}
