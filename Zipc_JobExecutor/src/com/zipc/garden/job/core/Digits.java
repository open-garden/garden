package com.zipc.garden.job.core;

import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * An adder that allows you to specify the radix of each digit.
 * 
 * 各桁の基数（繰り上がる数）を指定できる足し算器．
 * </pre>
 */
public class Digits {

    /** Number of digits */
    final int indexOfUnitDigits;

    /** Radix of each digit */
    final int[] bases;

    /** Value of each digit */
    final int[] values;

    /**
     * <pre>
     * constructor.
     * The following field variables are initialized.
     *  {@link #indexOfUnitDigits}
     *  {@link #bases}
     *  {@link #values}
     * </pre>
     * 
     * @param baseOfEachDigit Radix of each digit<br>
     *            各桁の基数
     */
    public Digits(int... baseOfEachDigit) {
        indexOfUnitDigits = baseOfEachDigit.length - 1;
        values = new int[baseOfEachDigit.length];
        bases = baseOfEachDigit;
        check();
    }

    /**
     * <pre>
     * constructor.
     * The following field variables are initialized.
     *  {@link #indexOfUnitDigits}
     *  {@link #bases}
     *  {@link #values}
     * </pre>
     * 
     * @param baseOfEachDigit Radix of each digit<br>
     *            各桁の基数
     */
    public Digits(Integer... baseOfEachDigit) {
        indexOfUnitDigits = baseOfEachDigit.length - 1;
        values = new int[baseOfEachDigit.length];
        bases = new int[baseOfEachDigit.length];
        for (int i = 0; i < baseOfEachDigit.length; i++) {
            bases[i] = baseOfEachDigit[i].intValue();
        }
        check();
    }

    /**
     * Converts the specified argument to an array of integer type and executes {@link #Digits(Integer...)}.
     * @param baseOfEachDigit Radix of each digit<br>
     *            各桁の基数
     */
    public Digits(List<Integer> baseOfEachDigit) {
        this(baseOfEachDigit.toArray(new Integer[baseOfEachDigit.size()]));
    }

    /**
     * <pre>
     * It is checked whether each value of {@link #bases} is 0 or less.
     * If any of them are less than or equal to 0, an IllegalArgumentException will be thrown.
     * </pre>
     * 
     * @throws IllegalArgumentException
     */
    private void check() throws IllegalArgumentException {
        if (Arrays.stream(bases).anyMatch(i -> i <= 0)) {
            throw new IllegalArgumentException("baseOfEachDigit must lager than 0.");
        }
    }

    /**
     * Adds the specified value to the last digit.
     * @param addition The value to be added.
     */
    public void add(int addition) {
        if (addition < 0) {
            return;
        }
        add0(addition, indexOfUnitDigits);
    }

    /**
     * Adds the specified value to the last digit.
     * @param addition The value to be added.
     */
    public void add(long addition) {
        if (addition < 0) {
            return;
        }
        // FIXME: limit はオーバーフローしづらいように，適当にMAX_VALUEの半分とした．効率よく足すための値を考える．
        int limit = Integer.MAX_VALUE / 2;
        while (addition > limit) {
            addition -= limit;
            add0(limit, indexOfUnitDigits);
        }
        add0((int) addition, indexOfUnitDigits);
    }

    /**
     * <pre>
     * The value of the specified digit is added and the carry processing is performed.
     * 
     * 指定した桁の値の加算と、繰り上げ処理が行われます。
     * </pre>
     * 
     * @param addition The value to be added.
     * @param index specified digit.
     */
    void add0(int addition, int index) {
        values[index] += addition;
        carry(index);
    }

    /**
     * <pre>
     * Carry up the specified digit values.
     * 
     * 指定した桁の値を繰り上げます。
     * </pre>
     * 
     * @param index specified digit.
     */
    void carry(int index) {
        if (values[index] >= bases[index]) {
            int carry = values[index] / bases[index];
            values[index] = values[index] % bases[index];

            if (index <= 0) {
                // Ignore overflow
                return;
            }
            add0(carry, index - 1);
        }
    }

    /**
     * Gets the {@link #values} of the specified digit.
     * @param index specified digit.
     * @return {@link #values values[index]}
     */
    public int get(int index) {
        return values[index];
    }
}
