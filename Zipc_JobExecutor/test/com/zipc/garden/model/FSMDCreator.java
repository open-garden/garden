package com.zipc.garden.model;

import com.zipc.garden.model.fsm.FSMDPseudoStateType;
import com.zipc.garden.model.fsm.FSMDRegion;
import com.zipc.garden.model.fsm.FSMDState;
import com.zipc.garden.model.fsm.FSMDStateMachine;
import com.zipc.garden.model.fsm.FSMDTransition;
import com.zipc.garden.model.fsm.FSMDVariable;
import com.zipc.garden.model.fsm.FSMFactory;

public class FSMDCreator {

    public static class FSMBuilder {
        final FSMDStateMachine fsm;

        public FSMBuilder(String id) {
            fsm = FSMFactory.eINSTANCE.createFSMDStateMachine();
            fsm.setId(id);
            fsm.getRegions().add(FSMFactory.eINSTANCE.createFSMDRegion());
        }

        public FSMDStateMachine get() {
            return fsm;
        }

        private FSMDRegion getRegion(int index) {
            return fsm.getRegions().get(index);
        }

        /**
         * 先頭RegionにStateを追加する
         * @param name
         * @return
         */
        public FSMBuilder addState(String name) {
            return addState(0, name);
        }

        /**
         * 指定RegionにStateを追加する
         * @param regionIndex
         * @param name
         * @return
         */
        public FSMBuilder addState(int regionIndex, String name) {
            FSMDRegion region = getRegion(regionIndex);
            createState(region, name);
            return this;
        }

        private FSMDState initialState;

        public FSMBuilder addInitial() {
            initialState = createNode(getRegion(0), FSMDPseudoStateType.INITIAL);
            return this;
        }

        /**
         * @return created initial node or {@code null} if not created.
         */
        public FSMDState getInitial() {
            return initialState;
        }

        private FSMDState getState(int regionIndex, int index) {
            return getRegion(regionIndex).getStates().get(index);
        }

        /**
         * 先頭Regionから，一致する名称のStateを取得する
         * @param name
         * @return
         */
        public FSMDState getState(String name) {
            return getRegion(0).getStates().stream().filter(s -> name.equals(s.getName())).findFirst().orElse(null);
        }

        /**
         * 指定のState間にTransitionを追加する
         * @param source
         * @param target
         * @param event
         * @return
         */
        public FSMBuilder addTransition(int source, int target, String event) {
            return addTransition(getState(0, source), getState(0, target), event);
        }

        /**
         * 指定のState間にTransitionを追加する
         * @param source
         * @param target
         * @param event
         * @return
         */
        public FSMBuilder addTransition(FSMDState source, FSMDState target, String event) {
            createTransition(fsm, source, target, event);
            return this;
        }

        /**
         * 変数を追加する
         * @param name
         * @param type
         * @return
         */
        public FSMBuilder addVariable(String name, String type) {
            createVariable(fsm, name, type);
            return this;
        }
    }

    public static FSMDState createState(FSMDRegion owner, String name) {
        FSMDState st = FSMFactory.eINSTANCE.createFSMDState();
        st.setName(name);
        owner.getStates().add(st);
        return st;
    }

    /**
     * {@link FSMDPseudoStateType#NONE} 以外のStateノードを作成することを目的としたメソッド． {@link FSMDPseudoStateType#NONE}
     * 以外のStateノードを作成することを目的としたメソッド．
     * @param container
     * @param type
     * @return
     */
    public static FSMDState createNode(FSMDRegion container, FSMDPseudoStateType type) {
        FSMDState st = createState(container, null);
        st.setType(type);
        return st;
    }

    public static FSMDTransition createTransition(FSMDStateMachine owner, FSMDState source, FSMDState target, String event, int priority) {
        FSMDTransition t = FSMFactory.eINSTANCE.createFSMDTransition();
        t.setSource(source);
        t.setTarget(target);
        t.setEvent(event);
        t.setPriority(priority);
        owner.getTransitions().add(t);
        return t;
    }

    public static FSMDTransition createTransition(FSMDStateMachine owner, FSMDState source, FSMDState target, String event) {
        return createTransition(owner, source, target, event, 0);
    }

    /**
     * @param owner
     * @param name
     * @param type
     */
    public static void createVariable(FSMDStateMachine owner, String name, String type) {
        FSMDVariable var = FSMFactory.eINSTANCE.createFSMDVariable();
        var.setName(name);
        var.setType(type);
        if (owner != null) {
            owner.getVariables().add(var);
        }
    }
}
