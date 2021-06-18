package com.zipc.garden.job.bp.nlength;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import com.zipc.garden.job.bp.nlength.CombinationIterator.Combination;
import com.zipc.garden.model.BPCreator;
import com.zipc.garden.model.bp.BPSpec;

@RunWith(Enclosed.class)
public class CombinationIteratorTest {
    public static class NoCombinations {
        @Test(expected = IllegalArgumentException.class)
        public void testNoCombinations() {
            List<List<Object>> elements = new ArrayList<>();
            elements.add(new ArrayList<>());
            elements.add(new ArrayList<>());
            elements.add(new ArrayList<>());
            new CombinationIterator<Object>(elements);
        }
    }

    public static class SomeCombinations {
        private CombinationIterator<BPSpec> sut;

        @Before
        public void setup() {
            List<BPSpec> paths1 = new ArrayList<>();
            paths1.add(BPCreator.createBPSpec("1", new String[] { "s10", "s10" }, new String[] { null }));
            paths1.add(BPCreator.createBPSpec("1", new String[] { "s10", "s11" }, new String[] { "e1" }));

            List<BPSpec> paths2 = new ArrayList<>();
            paths2.add(BPCreator.createBPSpec("2", new String[] { "s20", "s20" }, new String[] { null }));
            paths2.add(BPCreator.createBPSpec("2", new String[] { "s20", "s21" }, new String[] { "e1" }));
            paths2.add(BPCreator.createBPSpec("2", new String[] { "s21", "s22" }, new String[] { "e1" }));

            List<BPSpec> paths3 = new ArrayList<>();
            paths3.add(BPCreator.createBPSpec("3", new String[] { "s30", "s30" }, new String[] { null }));
            paths3.add(BPCreator.createBPSpec("3", new String[] { "s30", "s31" }, new String[] { "e1" }));
            paths3.add(BPCreator.createBPSpec("3", new String[] { "s31", "s32" }, new String[] { "e2" }));

            List<List<BPSpec>> elements = new ArrayList<>();
            elements.add(paths1);
            elements.add(paths2);
            elements.add(paths3);
            sut = new CombinationIterator<>(elements);
        }

        @Test
        public void testNumOfAll() {
            int pathCount = 0;
            while (sut.hasNext()) {
                sut.next();
                pathCount++;
            }
            assertThat(pathCount, is(18));
        }

        @Test
        public void testEachCombinationContents() {
            Combination<BPSpec> actual1 = sut.next();
            assertThat(actual1.size(), is(3));
            assertThat(actual1.get(0).getPaths().get(0).getState().getValue(), is("s10"));
            assertThat(actual1.get(0).getPaths().get(1).getEvent().getValue(), is(nullValue()));
            assertThat(actual1.get(0).getPaths().get(2).getState().getValue(), is("s10"));
            assertThat(actual1.get(1).getPaths().get(0).getState().getValue(), is("s20"));
            assertThat(actual1.get(1).getPaths().get(1).getEvent().getValue(), is(nullValue()));
            assertThat(actual1.get(1).getPaths().get(2).getState().getValue(), is("s20"));
            assertThat(actual1.get(2).getPaths().get(0).getState().getValue(), is("s30"));
            assertThat(actual1.get(2).getPaths().get(1).getEvent().getValue(), is(nullValue()));
            assertThat(actual1.get(2).getPaths().get(2).getState().getValue(), is("s30"));

            IntStream.range(0, 10).forEach(i -> sut.next()); // 間の組み合わせ内容を確認するために，適当に10回スキップ

            Combination<BPSpec> actual2 = sut.next();
            assertThat(actual2.size(), is(3));
            assertThat(actual2.get(0).getPaths().get(0).getState().getValue(), is("s10"));
            assertThat(actual2.get(0).getPaths().get(1).getEvent().getValue(), is("e1"));
            assertThat(actual2.get(0).getPaths().get(2).getState().getValue(), is("s11"));
            assertThat(actual2.get(1).getPaths().get(0).getState().getValue(), is("s20"));
            assertThat(actual2.get(1).getPaths().get(1).getEvent().getValue(), is(nullValue()));
            assertThat(actual2.get(1).getPaths().get(2).getState().getValue(), is("s20"));
            assertThat(actual2.get(2).getPaths().get(0).getState().getValue(), is("s31"));
            assertThat(actual2.get(2).getPaths().get(1).getEvent().getValue(), is("e2"));
            assertThat(actual2.get(2).getPaths().get(2).getState().getValue(), is("s32"));
        }

    }
}
