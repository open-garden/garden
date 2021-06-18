package com.zipc.garden.model;

import com.zipc.garden.model.arc.ARCFactory;
import com.zipc.garden.model.arc.ARCLine;
import com.zipc.garden.model.arc.ARCRoot;
import com.zipc.garden.model.arc.ARCState;

public class ARCCreator {

    public static ARCState createState(ARCRoot parent, String name, String refUuid) {
        return createState(parent, name, refUuid, 0);
    }

    public static ARCState createState(ARCRoot parent, String name, String refUuid, int priority) {
        ARCState state = ARCFactory.eINSTANCE.createARCState();
        state.setName(name);
        state.setFileId(refUuid);
        state.setEvalPriority(0);
        parent.getStates().add(state);
        return state;
    }

    public static ARCLine createLine(ARCRoot parent, ARCState source, ARCState target, String varialbe) {
        ARCLine line = ARCFactory.eINSTANCE.createARCLine();
        line.setVariableName(varialbe);
        line.setSource(source);
        line.setTarget(target);
        parent.getLines().add(line);
        return line;
    }

    public static class ARCBuilder {
        final ARCRoot arc;

        public ARCBuilder(String id) {
            arc = ARCFactory.eINSTANCE.createARCRoot();
            arc.setId(id);
        }

        public ARCRoot get() {
            return arc;
        }

        /**
         * Stateを追加する
         * @param name
         * @param refUuid
         * @return
         */
        public ARCBuilder addState(String name) {
            addState(name, "dummy", 0);
            return this;
        }

        /**
         * Stateを追加する
         * @param name
         * @param refUuid
         * @return
         */
        public ARCBuilder addState(String name, String refUuid) {
            addState(name, refUuid, 0);
            return this;
        }

        /**
         * Stateを追加する
         * @param name
         * @param refUuid
         * @param priority
         * @return
         */
        public ARCBuilder addState(String name, String refUuid, int priority) {
            createState(arc, name, refUuid, priority);
            return this;
        }

        private ARCState getState(int index) {
            return arc.getStates().get(index);
        }

        /**
         * Get state that has specified name.
         * @param name
         * @return
         */
        public ARCState getState(String name) {
            return arc.getStates().stream().filter(s -> name.equals(s.getName())).findFirst().orElse(null);
        }

        /**
         * Add line between specified states.
         * @param source
         * @param target
         * @param varialble
         * @return
         */
        public ARCBuilder addLine(int source, int target, String varialble) {
            return addLine(getState(source), getState(target), varialble);
        }

        /**
         * Add line between specified states.
         * @param source
         * @param target
         * @param variable
         * @return
         */
        public ARCBuilder addLine(ARCState source, ARCState target, String variable) {
            createLine(arc, source, target, variable);
            return this;
        }
    }

}
