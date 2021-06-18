package com.zipc.garden.job.bp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.zipc.garden.model.bp.BPEvent;
import com.zipc.garden.model.bp.BPSpec;
import com.zipc.garden.model.bp.BPState;

public class PathGeneratorTest {

    /**
     * @param actual 生成結果
     * @param expected 期待値. 1パスごとにステート名，イベント名をカンマ区切りにした文字列.
     */
    public static void assertSpecDetails(List<BPSpec> actual, String[] expected) {
        assertThat(actual.size(), is(expected.length));

        for (int i = 0; i < actual.size(); i++) {
            BPSpec bpSpec = actual.get(i);
            String actualString = bpSpec.getPaths().stream().map(path -> {
                List<String> values = new ArrayList<>(2);
                BPState bpState = path.getState();
                if (bpState != null) {
                    values.add(bpState.getValue());
                }
                BPEvent bpEvent = path.getEvent();
                if (bpEvent != null) {
                    values.add(bpEvent.getValue());
                }
                return String.join(",", values);
            }).collect(Collectors.joining(","));

            assertEquals(message(i, expected[i], actualString), expected[i], actualString);
        }
    }

    private static String message(int i, String expected, String actual) {
        return new StringBuilder().append(i).append(": ").toString();
    }

    /**
     * @param actual 生成結果
     * @param expected 期待値. 1パスごとにステート名，イベント名をカンマ区切りにした文字列.
     * @param expectFsmId 各Stepが持つFSMの名称
     */
    public static void assertSpecDetails(List<BPSpec> actual, String[] expected, String expectFsmId) {
        assertThat(actual.size(), is(expected.length));

        for (int i = 0; i < actual.size(); i++) {
            BPSpec bpSpec = actual.get(i);
            String actualString = bpSpec.getPaths().stream().map(path -> {
                List<String> values = new ArrayList<>(2);
                BPState bpState = path.getState();
                if (bpState != null) {
                    assertEquals(expectFsmId, bpState.getName());
                    values.add(bpState.getValue());
                }
                BPEvent bpEvent = path.getEvent();
                if (bpEvent != null) {
                    assertEquals(expectFsmId, bpEvent.getName());
                    values.add(bpEvent.getValue());
                }
                return String.join(",", values);
            }).collect(Collectors.joining(","));

            assertEquals(expected[i], actualString);
        }
    }

}
