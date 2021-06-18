package com.zipc.garden.job.bp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.Test;

import com.zipc.garden.model.BPCreator;
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
import com.zipc.garden.model.bps.BPSFactory;
import com.zipc.garden.model.bps.BPSRoot;
import com.zipc.garden.model.bps.BPSStateCombinationOption;

public class BehaviorPatternGeneratorTest {
    private BehaviorPatternGenerator sut;

    private BPRoot createBPRootWithNullBehavior() {
        // create test model manually
        BPStateMachine stm1 = BPFactory.eINSTANCE.createBPStateMachine();
        stm1.setFsmId("as-12");
        BPState state1 = BPCreator.createState(stm1, "State_velocity", "state0");
        BPEvent event1 = BPCreator.createEvent(stm1, "Event_velocity", "event0");

        BPStateMachine stm2 = BPFactory.eINSTANCE.createBPStateMachine();
        stm2.setFsmId("ed-45-e");
        BPState state2 = BPCreator.createState(stm2, "State_velocity", "state3");
        BPEvent event2 = BPCreator.createEvent(stm2, "Event_velocity", "event3");

        BPBehavior bpBehavior1 = BPFactory.eINSTANCE.createBPBehavior();
        bpBehavior1.setStateMachineRef(stm1);
        BPCreator.createStep(bpBehavior1, state1);
        BPCreator.createStep(bpBehavior1, event1);

        BPBehavior bpBehavior2 = BPFactory.eINSTANCE.createBPBehavior();
        bpBehavior2.setStateMachineRef(stm2);
        BPCreator.createStep(bpBehavior2, state2);
        BPCreator.createStep(bpBehavior2, event2);

        BPBehavior bpBehavior3 = BPFactory.eINSTANCE.createBPBehavior();
        bpBehavior3.setStateMachineRef(stm1);
        BPCreator.createStep(bpBehavior3, state1);
        BPCreator.createStep(bpBehavior3, event1);

        BPBehavior bpBehavior4 = BPFactory.eINSTANCE.createBPBehavior();
        bpBehavior4.setStateMachineRef(stm2);
        BPCreator.createStep(bpBehavior4, state2);
        BPCreator.createStep(bpBehavior4, event2);

        BPSpecElement e = BPFactory.eINSTANCE.createBPSpecElement();
        e.setEvent(event1);
        BPSpec sp1 = BPFactory.eINSTANCE.createBPSpec();
        sp1.getPaths().add(e);

        BPBehaviorPattern pattern1 = BPFactory.eINSTANCE.createBPBehaviorPattern();
        pattern1.getBehaviors().add(bpBehavior1);
        pattern1.getBehaviors().add(bpBehavior2);
        pattern1.setId("1");
        pattern1.setSpecification(sp1);

        BPBehaviorPattern pattern2 = BPFactory.eINSTANCE.createBPBehaviorPattern();
        pattern2.getBehaviors().add(bpBehavior3);
        pattern2.getBehaviors().add(bpBehavior4);
        pattern2.setId("2");
        pattern2.setSpecification(sp1);

        BPBehaviorPattern pattern3 = BPFactory.eINSTANCE.createBPBehaviorPattern();
        BPBehaviorPattern pattern4 = BPFactory.eINSTANCE.createBPBehaviorPattern();

        BPSetting setting = BPFactory.eINSTANCE.createBPSetting();
        setting.getPattern().add(pattern1);
        setting.getPattern().add(pattern2);
        setting.getPattern().add(pattern3);
        setting.getPattern().add(pattern4);
        setting.getStateMachines().add(stm1);
        setting.getStateMachines().add(stm2);

        BPRoot model = BPFactory.eINSTANCE.createBPRoot();
        model.getSettings().add(setting);

        return model;
    }

    @Test
    public void testBPGenerationWithNullBehavior() {
        BPRoot bp = createBPRootWithNullBehavior();
        BPSetting setting = (BPSetting) bp.getSettings().get(0);

        BPRoot test = BPFactory.eINSTANCE.createBPRoot();
        long projectId = 1L;
        long fileId = 1L;
        long jobId = 1L;
        sut = new BehaviorPatternGenerator(jobId, projectId, fileId);
        sut.updateCountOfAll(test, setting.getPattern());

        assertThat(test.getAll(), is(2));
    }

    private BPBehavior createBeahviorWithFSM(BPBehaviorPattern parent, String fsmId) {
        BPBehavior b = BPFactory.eINSTANCE.createBPBehavior();
        BPStateMachine fsm = BPFactory.eINSTANCE.createBPStateMachine();
        fsm.setFsmId(fsmId);
        b.setStateMachineRef(fsm);
        parent.getBehaviors().add(b);
        return b;
    }

    private BPBehaviorPattern createBPWithThreeBehavior(BPSetting setting) {
        BPBehaviorPattern bp = BPFactory.eINSTANCE.createBPBehaviorPattern();
        createBeahviorWithFSM(bp, "1");
        createBeahviorWithFSM(bp, "2");
        createBeahviorWithFSM(bp, "3");
        setting.getPattern().add(bp);
        return bp;
    }

    @Test
    public void removeUnselectedStateMachines() {
        BPRoot bpRoot = BPFactory.eINSTANCE.createBPRoot();
        BPSetting setting = BPFactory.eINSTANCE.createBPSetting();
        bpRoot.getSettings().add(setting);
        createBPWithThreeBehavior(setting);
        createBPWithThreeBehavior(setting);
        createBPWithThreeBehavior(setting);
        BPSRoot input = BPSFactory.eINSTANCE.createBPSRoot();
        BPSStateCombinationOption option = BPSFactory.eINSTANCE.createBPSStateCombinationOption();
        Stream.of("1", "3").forEach(option.getTargetStateMachines()::add);
        input.getOptions().add(option);

        sut = new BehaviorPatternGenerator(0, 0, 0);
        sut.setInputBPS(input);
        sut.setGeneratedBP(bpRoot);
        // execute
        sut.removeUnselectedStateMachines();

        // assert. all behavior haven't fsm that has id "2".
        assertThat(setting.getPattern().get(0).getBehaviors().size(), is(2));
        assertThat(setting.getPattern().get(0).getBehaviors().get(0).getStateMachineRef().getFsmId(), is("1"));
        assertThat(setting.getPattern().get(0).getBehaviors().get(1).getStateMachineRef().getFsmId(), is("3"));
        assertThat(setting.getPattern().get(1).getBehaviors().size(), is(2));
        assertThat(setting.getPattern().get(1).getBehaviors().get(0).getStateMachineRef().getFsmId(), is("1"));
        assertThat(setting.getPattern().get(1).getBehaviors().get(1).getStateMachineRef().getFsmId(), is("3"));
        assertThat(setting.getPattern().get(2).getBehaviors().size(), is(2));
        assertThat(setting.getPattern().get(2).getBehaviors().get(0).getStateMachineRef().getFsmId(), is("1"));
        assertThat(setting.getPattern().get(2).getBehaviors().get(1).getStateMachineRef().getFsmId(), is("3"));

    }

    @Test
    public void testBPGenerationWithNoBehavior() {
        BPBehaviorPattern pattern1 = BPFactory.eINSTANCE.createBPBehaviorPattern();
        BPBehaviorPattern pattern2 = BPFactory.eINSTANCE.createBPBehaviorPattern();
        BPBehaviorPattern pattern3 = BPFactory.eINSTANCE.createBPBehaviorPattern();
        BPBehaviorPattern pattern4 = BPFactory.eINSTANCE.createBPBehaviorPattern();

        BPRoot bp = BPFactory.eINSTANCE.createBPRoot();
        BPSetting setting = BPFactory.eINSTANCE.createBPSetting();
        bp.getSettings().add(setting);
        setting.getPattern().add(pattern1);
        setting.getPattern().add(pattern2);
        setting.getPattern().add(pattern3);
        setting.getPattern().add(pattern4);
        BPRoot test = BPFactory.eINSTANCE.createBPRoot();
        long projectId = 1L;
        long fileId = 1L;
        long jobId = 1L;
        sut = new BehaviorPatternGenerator(jobId, projectId, fileId);
        sut.updateCountOfAll(test, setting.getPattern());

        assertThat(test.getAll(), is(0));
    }

}
