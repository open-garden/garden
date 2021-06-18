package com.zipc.garden.job.bp.nlength;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import com.zipc.garden.model.BPCreator;
import com.zipc.garden.model.BPSSTMCreator.STMBuilder;
import com.zipc.garden.model.bp.BPSpec;

@RunWith(Enclosed.class)
public class NLengthPathGeneratorTest {

    public static class NoOwnTransition {
        private NLengthPathGenerator sut;

        @Before
        public void setup() {
            sut = new NLengthPathGenerator();
            /* Initial --> s0 --e1--> s1 --e2--> s2 */
            STMBuilder builder = new STMBuilder("1", "fsm1");
            builder.addInitial().addState("s0").addState("s1").addState("s2");
            builder.addTransition(builder.getInitial(), builder.getState("s0"), null);
            builder.addTransition(builder.getState("s0"), builder.getState("s1"), "e1");
            builder.addTransition(builder.getState("s1"), builder.getState("s2"), "e2");
            sut.set(builder.get(), "fsm1");
        }

        @Test
        public void testOneLength() {
            /* setup */
            sut.setLength(1);

            /* execute */
            List<BPSpec> paths = sut.generate();

            /* assert */
            BPSpec expected1 = BPCreator.createBPSpec("1", new String[] { "s0" }, new String[] {});
            assertThat(paths.size(), is(1));
            assertThat(paths.get(0), is(expected1));
        }

        @Test
        public void testTwoLength() {
            /* setup */
            sut.setLength(2);

            /* execute */
            List<BPSpec> paths = sut.generate();

            /* assert */
            BPSpec expected1 = BPCreator.createBPSpec("1", "s0", new String[] { "»s0" });
            BPSpec expected2 = BPCreator.createBPSpec("1", "s0", new String[] { "e1»s1" });
            assertThat(paths.size(), is(2));
            assertThat(paths.get(0), is(expected1));
            assertThat(paths.get(1), is(expected2));
        }

        @Test
        public void testThreeLength() {
            /* setup */
            sut.setLength(3);

            /* execute */
            List<BPSpec> paths = sut.generate();

            /* assert */
            BPSpec expected1 = BPCreator.createBPSpec("1", "s0", new String[] { "»s0", "»s0" });
            BPSpec expected2 = BPCreator.createBPSpec("1", "s0", new String[] { "»s0", "e1»s1" });
            BPSpec expected3 = BPCreator.createBPSpec("1", "s0", new String[] { "e1»s1", "»s1" });
            BPSpec expected4 = BPCreator.createBPSpec("1", "s0", new String[] { "e1»s1", "e2»s2" });
            assertThat(paths.size(), is(4));
            assertThat(paths.get(0), is(expected1));
            assertThat(paths.get(1), is(expected2));
            assertThat(paths.get(2), is(expected3));
            assertThat(paths.get(3), is(expected4));
        }

        @Test
        public void testFourLength() {
            /* setup */
            sut.setLength(4);

            /* execute */
            List<BPSpec> paths = sut.generate();

            /* assert */
            BPSpec expected1 = BPCreator.createBPSpec("1", "s0", new String[] { "»s0", "»s0", "»s0" });
            BPSpec expected2 = BPCreator.createBPSpec("1", "s0", new String[] { "»s0", "»s0", "e1»s1" });
            BPSpec expected3 = BPCreator.createBPSpec("1", "s0", new String[] { "»s0", "e1»s1", "»s1" });
            BPSpec expected4 = BPCreator.createBPSpec("1", "s0", new String[] { "e1»s1", "»s1", "»s1" });
            BPSpec expected5 = BPCreator.createBPSpec("1", "s0", new String[] { "»s0", "e1»s1", "e2»s2" });
            BPSpec expected6 = BPCreator.createBPSpec("1", "s0", new String[] { "e1»s1", "»s1", "e2»s2" });
            BPSpec expected7 = BPCreator.createBPSpec("1", "s0", new String[] { "e1»s1", "e2»s2", "»s2" });
            assertThat(paths.size(), is(7));
            assertThat(paths.get(0), is(expected1));
            assertThat(paths.get(1), is(expected2));
            assertThat(paths.get(2), is(expected3));
            assertThat(paths.get(3), is(expected4));
            assertThat(paths.get(4), is(expected5));
            assertThat(paths.get(5), is(expected6));
            assertThat(paths.get(6), is(expected7));
        }

    }

    public static class IncludeOwnTransition {
        private NLengthPathGenerator sut;

        @Before
        public void setup() {
            sut = new NLengthPathGenerator();
            /* Initial --> s0 --e1--> s1 --e2--> s2 */
            /* And s1 --e3--> s1 */
            STMBuilder builder = new STMBuilder("1", "fsm1");
            builder.addInitial().addState("s0").addState("s1").addState("s2");
            builder.addTransition(builder.getInitial(), builder.getState("s0"), null);
            builder.addTransition(builder.getState("s0"), builder.getState("s1"), "e1");
            builder.addTransition(builder.getState("s1"), builder.getState("s2"), "e2");
            builder.addTransition(builder.getState("s1"), builder.getState("s1"), "e3");
            sut.set(builder.get(), "fsm1");
        }

        @Test(expected = IllegalArgumentException.class)
        public void testZeroLength() {
            sut.setLength(0);
        }

        @Test
        public void testOneLength() {
            /* setup */
            sut.setLength(1);

            /* execute */
            List<BPSpec> paths = sut.generate();

            /* assert */
            BPSpec expected1 = BPCreator.createBPSpec("1", "s0", new String[] {});
            assertThat(paths.size(), is(1));
            assertThat(paths.get(0), is(expected1));
        }

        @Test
        public void testTwoLength() {
            /* setup */
            sut.setLength(2);

            /* execute */
            List<BPSpec> paths = sut.generate();

            /* assert */
            BPSpec expected1 = BPCreator.createBPSpec("1", "s0", new String[] { "»s0" });
            BPSpec expected2 = BPCreator.createBPSpec("1", "s0", new String[] { "e1»s1" });
            assertThat(paths.size(), is(2));
            assertThat(paths.get(0), is(expected1));
            assertThat(paths.get(1), is(expected2));
        }

        @Test
        public void testThreeLength() {
            /* setup */
            sut.setLength(3);

            /* execute */
            List<BPSpec> paths = sut.generate();

            /* assert */
            BPSpec expected1 = BPCreator.createBPSpec("1", "s0", new String[] { "»s0", "»s0" });
            BPSpec expected2 = BPCreator.createBPSpec("1", "s0", new String[] { "»s0", "e1»s1" });
            BPSpec expected3 = BPCreator.createBPSpec("1", "s0", new String[] { "e1»s1", "»s1" });
            BPSpec expected4 = BPCreator.createBPSpec("1", "s0", new String[] { "e1»s1", "e3»s1" });
            BPSpec expected5 = BPCreator.createBPSpec("1", "s0", new String[] { "e1»s1", "e2»s2" });
            assertThat(paths.size(), is(5));
            assertThat(paths.get(0), is(expected1));
            assertThat(paths.get(1), is(expected2));
            assertThat(paths.get(2), is(expected3));
            assertThat(paths.get(3), is(expected4));
            assertThat(paths.get(4), is(expected5));
        }

        @Test
        public void testFourLength() {
            /* setup */
            sut.setLength(4);

            /* execute */
            List<BPSpec> paths = sut.generate();

            /* assert */
            BPSpec expected1 = BPCreator.createBPSpec("1", "s0", new String[] { "»s0", "»s0", "»s0" });
            BPSpec expected2 = BPCreator.createBPSpec("1", "s0", new String[] { "»s0", "»s0", "e1»s1" });
            BPSpec expected3 = BPCreator.createBPSpec("1", "s0", new String[] { "»s0", "e1»s1", "»s1" });
            BPSpec expected4 = BPCreator.createBPSpec("1", "s0", new String[] { "»s0", "e1»s1", "e3»s1" });
            BPSpec expected5 = BPCreator.createBPSpec("1", "s0", new String[] { "e1»s1", "»s1", "»s1" });
            BPSpec expected6 = BPCreator.createBPSpec("1", "s0", new String[] { "e1»s1", "»s1", "e3»s1" });
            BPSpec expected7 = BPCreator.createBPSpec("1", "s0", new String[] { "e1»s1", "e3»s1", "»s1" });
            BPSpec expected8 = BPCreator.createBPSpec("1", "s0", new String[] { "e1»s1", "e3»s1", "e3»s1" });
            BPSpec expected9 = BPCreator.createBPSpec("1", "s0", new String[] { "»s0", "e1»s1", "e2»s2" });
            BPSpec expected10 = BPCreator.createBPSpec("1", "s0", new String[] { "e1»s1", "»s1", "e2»s2" });
            BPSpec expected11 = BPCreator.createBPSpec("1", "s0", new String[] { "e1»s1", "e2»s2", "»s2" });
            BPSpec expected12 = BPCreator.createBPSpec("1", "s0", new String[] { "e1»s1", "e3»s1", "e2»s2" });

            assertThat(paths.size(), is(12));
            assertThat(paths.get(0), is(expected1));
            assertThat(paths.get(1), is(expected2));
            assertThat(paths.get(2), is(expected3));
            assertThat(paths.get(3), is(expected4));
            assertThat(paths.get(4), is(expected5));
            assertThat(paths.get(5), is(expected6));
            assertThat(paths.get(6), is(expected7));
            assertThat(paths.get(7), is(expected8));
            assertThat(paths.get(8), is(expected9));
            assertThat(paths.get(9), is(expected10));
            assertThat(paths.get(10), is(expected11));
            assertThat(paths.get(11), is(expected12));
        }

    }

}
