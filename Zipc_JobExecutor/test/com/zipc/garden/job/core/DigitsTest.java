package com.zipc.garden.job.core;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class DigitsTest {

    private Digits sut;

    @Test
    public void testInt() {
        sut = new Digits(new int[] { 2, 2, 3 });
        sut.add(1);
        assertThat(sut.get(0), is(0));
        assertThat(sut.get(1), is(0));
        assertThat(sut.get(2), is(1));
    }

    @Test
    public void testInt_Carry() {
        sut = new Digits(new int[] { 2, 2, 2 });
        sut.add(5);
        assertThat(sut.get(0), is(1));
        assertThat(sut.get(1), is(0));
        assertThat(sut.get(2), is(1));
    }

    @Test
    public void testInt_Overflow() {
        // spec: 上位桁が落ちる．最下位桁には戻ってこない
        sut = new Digits(new int[] { 10, 10, 10 });
        sut.add(1001);
        assertThat(sut.get(0), is(0));
        assertThat(sut.get(1), is(0));
        assertThat(sut.get(2), is(1));
    }

    @Test
    public void testLong() {
        sut = new Digits(new int[] { 10, 1000, 1000, 1000 }); // until 100 billion
        System.out.println(Integer.MAX_VALUE / 2 + 10);
        sut.add((long) (Integer.MAX_VALUE / 2 + 10)); // 1073741833
        assertThat(sut.get(0), is(1));
        assertThat(sut.get(1), is(73));
        assertThat(sut.get(2), is(741));
        assertThat(sut.get(3), is(833));
    }
}
