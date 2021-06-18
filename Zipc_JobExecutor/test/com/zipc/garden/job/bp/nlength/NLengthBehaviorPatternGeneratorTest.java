package com.zipc.garden.job.bp.nlength;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import com.zipc.garden.fsm.simulator.IFSMSimulator;
import com.zipc.garden.job.bp.GenerationCancellationException;
import com.zipc.garden.job.bp.IGenerationHandler;
import com.zipc.garden.model.BPCreator;
import com.zipc.garden.model.BPSSTMCreator.STMBuilder;
import com.zipc.garden.model.bp.BPBehavior;
import com.zipc.garden.model.bp.BPBehaviorPattern;
import com.zipc.garden.model.bp.BPSpec;
import com.zipc.garden.model.bps.BPSInstanceArc;
import com.zipc.garden.model.bps.BPSInstanceStateMachine;

@RunWith(Enclosed.class)
public class NLengthBehaviorPatternGeneratorTest {

    public static class WithStubSimulator {
        private NLengthBehaviorPatternGenerator sut;

        private StubSimulator stubSimulator;

        private StubHandler handler;

        @Before
        public void setup() {
            sut = new NLengthBehaviorPatternGenerator();
            stubSimulator = new StubSimulator();
            handler = new StubHandler();

            sut.setSimulator(stubSimulator);
            sut.addGenerationHandler(handler);
        }

        @Test
        public void testHandlerCalled() throws GenerationCancellationException {
            /* setup */
            stubSimulator.addStates("withoutOwn1", "s0");
            stubSimulator.addEvents("withoutOwn1", "s0->null");

            /* execute */
            sut.generate(Collections.singletonList(fsmNoOwnTransition("1", "withoutOwn1")), 1);

            /* assert */
            assertThat(handler.callStart, is(true));
            assertThat(handler.callFinished, is(true));
            assertThat(handler.generated.size(), is(1));
        }

        @Test
        public void testSimulatorCalled1() throws GenerationCancellationException {
            /* setup */
            stubSimulator.addStates("withoutOwn1", "s0");
            stubSimulator.addEvents("withoutOwn1", "s0->null");

            /* execute */
            sut.generate(Collections.singletonList(fsmNoOwnTransition("1", "withoutOwn1")), 1);

            /* assert */
            assertThat(stubSimulator.reset.executed, is(true));
            assertThat(stubSimulator.clearAllFsmEvent.executed, is(false));
            assertThat(stubSimulator.setFsmEvent.executed, is(false));
            assertThat(stubSimulator.execute.executed, is(false));
            assertThat(stubSimulator.getFsmCurrentState.executed, is(false));
        }

        @Test
        public void testSimulatorCalled2() throws GenerationCancellationException {
            /* setup */
            stubSimulator.addStates("withoutOwn1", "s0");
            stubSimulator.addEvents("withoutOwn1", "s0->null");

            /* execute */
            sut.generate(Collections.singletonList(fsmNoOwnTransition("1", "withoutOwn1")), 2);

            /* assert */
            assertThat(stubSimulator.reset.executed, is(true));
            assertThat(stubSimulator.clearAllFsmEvent.executed, is(true));
            assertThat(stubSimulator.setFsmEvent.executed, is(true));
            assertThat(stubSimulator.execute.executed, is(true));
            assertThat(stubSimulator.getFsmCurrentState.executed, is(true));
        }

        @Test
        public void testNotGenerateUnsatisfiedPath() throws GenerationCancellationException {
            final String fsmId1 = "2";
            final String fsmId2 = "3";
            final String fsmName1 = "withoutOwn1";
            final String fsmName2 = "withoutOwn2";
            // スタブが返す現在ステートを指定
            stubSimulator.addStates(fsmName1, "s1");
            stubSimulator.addStates(fsmName2, "s0");
            stubSimulator.addEvents(fsmName1, "s0->e0");
            stubSimulator.addEvents(fsmName2, "s0->null");

            // execute
            List<BPSInstanceStateMachine> fsms = new ArrayList<BPSInstanceStateMachine>();
            fsms.add(fsmNoOwnTransition(fsmId1, fsmName1));
            fsms.add(fsmNoOwnTransition(fsmId2, fsmName2));
            sut.generate(fsms.stream().sorted((o1, o2) -> o1.getOriginalUuid().compareTo(o2.getOriginalUuid())).collect(Collectors.toList()), 2);

            // assert
            assertThat(handler.generated.size(), is(1));
            BPBehaviorPattern actual = handler.generated.get(0);
            assertThat(actual.getId(), is("1"));
            assertThat(actual.getBehaviors().size(), is(2));
            assertBehavior(actual.getBehaviors().get(0), fsmId1, fsmName1, BPCreator.createBPSpec(fsmName1, "s0", new String[] { "e0»s1" }, true));
            // null イベントの場合，遷移しないのでイベントとその後の遷移情報がなかったことにされる
            BPSpec specWithEmpty = BPCreator.createBPSpec(fsmName2, "s0", new String[] { "»s0" }, true);
            assertBehavior(actual.getBehaviors().get(1), fsmId2, fsmName2, specWithEmpty);
        }

        /**
         * Stub simulator
         * @author kousuke_morishima
         */
        private class StubSimulator implements IFSMSimulator {
            /**
             * fsmName -> return states
             */
            private Map<String, List<String>> returnStates = new HashMap<>();

            /**
             * fsmName -> return events
             */
            private Map<String, List<String>> returnEvents = new HashMap<>();

            private Map<String, Integer> stateCounter = new HashMap<>();

            private Map<String, Integer> eventCounter = new HashMap<>();

            class CallFlag {
                boolean executed;

                public void call() {
                    executed = true;
                }
            }

            private CallFlag getFsmCurrentState = new CallFlag();

            private CallFlag getFsmOccurredEvent = new CallFlag();

            private CallFlag reset = new CallFlag();

            private CallFlag configure = new CallFlag();

            private CallFlag setFsmEvent = new CallFlag();

            private CallFlag execute = new CallFlag();

            private CallFlag clearAllFsmEvent = new CallFlag();

            /**
             * {@link IFSMSimulator#getFsmCurrentState} が呼ばれたときに返却するStateの名称. このメソッドの呼び出し1回で，ひとつのFSMに
             * @param fsmName
             * @param states
             */
            public void addStates(String fsmName, String... states) {
                returnStates.put(fsmName, Arrays.asList(states));
                stateCounter.put(fsmName, 0);
            }

            /**
             * {@link IFSMSimulator#getFsmCurrentState} が呼ばれたときに返却するEventの名称. このメソッドの呼び出し1回で，ひとつのFSMに
             * @param fsmName
             * @param events
             */
            public void addEvents(String fsmName, String... events) {
                returnEvents.put(fsmName, Arrays.asList(events));
                eventCounter.put(fsmName, 0);
            }

            @Override
            public String getFsmCurrentState(String fsmName) {
                getFsmCurrentState.call();
                Integer count = stateCounter.get(fsmName);
                stateCounter.put(fsmName, count + 1);
                return returnStates.get(fsmName).get(count.intValue());
            }

            @Override
            public String getFsmOccurredEvent(String fsmName) {
                getFsmOccurredEvent.call();
                Integer count = eventCounter.get(fsmName);
                eventCounter.put(fsmName, count + 1);
                return returnEvents.get(fsmName).get(count.intValue());
            }

            @Override
            public void reset() {
                reset.call();
                for (Map.Entry<String, Integer> e : stateCounter.entrySet()) {
                    stateCounter.replace(e.getKey(), 0);
                }
                for (Map.Entry<String, Integer> e : eventCounter.entrySet()) {
                    eventCounter.replace(e.getKey(), 0);
                }
            }

            @Override
            public boolean configure(BPSInstanceArc arc) {
                configure.call();
                return true;
            }

            @Override
            public boolean setFsmPriority(Map<String, Integer> fsmPriorityMap) {
                return true;
            }

            @Override
            public boolean setFsmEvent(String fsmInstanceName, String event) {
                setFsmEvent.call();
                return true;
            }

            @Override
            public boolean execute() {
                execute.call();
                return true;
            }

            @Override
            public void clearAllFsmEvent() {
                clearAllFsmEvent.call();
                for (Map.Entry<String, Integer> e : stateCounter.entrySet()) {
                    stateCounter.replace(e.getKey(), 0);
                }
                for (Map.Entry<String, Integer> e : eventCounter.entrySet()) {
                    eventCounter.replace(e.getKey(), 0);
                }
            }

            @Override
            public boolean clearFsmEvent(String fsmInstanceName) {
                throw new UnsupportedOperationException("Not support yet.");
            }

            @Override
            public String getFsmVarCurrentValue(String fsmInstanceName, String variableName) {
                throw new UnsupportedOperationException("Not support yet.");
            }

            @Override
            public boolean isSelfTransitionSuccess(String fsmInstanceName) {
                return true;
            }
        }
    }

    private static BPSInstanceStateMachine fsmNoOwnTransition(String id, String name) {
        /* (Initial) --> s0 --e0--> s1 */
        STMBuilder builder = new STMBuilder(id, name);
        builder.addInitial().addState("s0").addState("s1");
        builder.addTransition(builder.getInitial(), builder.getState("s0"), null);
        builder.addTransition(builder.getState("s0"), builder.getState("s1"), "e0");
        return builder.get();
    }

    private static void assertBehavior(BPBehavior actual, String fsmId, String fsmName, BPSpec expected) {
        assertThat(actual.getStateMachineRef().getFsmId(), is(fsmId));
        assertThat(actual.getStateMachineRef().getName(), is(fsmName));
        assertThat(actual.getSteps().size(), is(expected.getPaths().size()));
        IntStream.range(0, actual.getSteps().size()).forEach(i -> {
            assertThat(Integer.toString(i), actual.getSteps().get(i).getState(), is(expected.getPaths().get(i).getState()));
            assertThat(Integer.toString(i), actual.getSteps().get(i).getEvent(), is(expected.getPaths().get(i).getEvent()));
        });
    }

    private static class StubHandler implements IGenerationHandler<BPBehaviorPattern> {
        private boolean callStart = false;

        private boolean callFinished = false;

        private List<BPBehaviorPattern> generated = new ArrayList<>();

        @Override
        public void start() {
            callStart = true;
        }

        @Override
        public void generated(BPBehaviorPattern newElement) {
            generated.add(newElement);
        }

        @Override
        public void finished() {
            callFinished = true;
        }

        @Override
        public void setAmount(double amount) {
        }

        @Override
        public void setCurrent(double current) {
        }

    }

}
