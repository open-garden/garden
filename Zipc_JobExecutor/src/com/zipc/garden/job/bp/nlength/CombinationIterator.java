package com.zipc.garden.job.bp.nlength;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.zipc.garden.job.bp.nlength.CombinationIterator.Combination;
import com.zipc.garden.job.core.Digits;

/**
 * Generate element combinations.
 * @param <E> Type of Element
 */
public class CombinationIterator<E> implements Iterator<Combination<E>> {

    /** A list of combinations. */
    private final List<Block<E>> elements;

    /** A class with a number that corresponds to a particular combination */
    private final Digits counter;

    /**
     * If false, true will be returned for the first {@link #hasNext()}. <br>
     * (Because the case where there is no combination is excluded by the constructor)
     */
    private boolean calledNext;

    /**
     * <pre>
     * constructor.
     * The following field variables are initialized.
     *  {@link #elements}
     *  {@link #counter}
     *  {@link #calledNext}
     * </pre>
     * 
     * @param elements The value to set for {@link #elements}.
     * @throws IllegalArgumentException all elements are empty.
     */
    public CombinationIterator(List<List<E>> elements) throws IllegalArgumentException {
        List<Integer> sizes = elements.stream().map(element -> element.size()).collect(Collectors.toList());
        boolean empty = sizes.stream().allMatch(i -> i == 0);
        if (empty) {
            throw new IllegalArgumentException("elements must include element.");
        }

        this.elements = elements.stream().map(Block<E>::new).collect(Collectors.toList());
        counter = new Digits(sizes);
        calledNext = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        // すべての桁が0でなければ，次がある．(次の組み合わせに相当する数値を保持しているので)
        boolean hasNext = IntStream.range(0, elements.size()).anyMatch(i -> counter.get(i) > 0);
        if (hasNext) {
            return true;
        }
        // 一度も呼ばれていなければ，次がある(組み合わせがひとつもないケースは，コンストラクタで弾いているので)
        return !calledNext;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Combination<E> next() {
        calledNext = true;

        Combination<E> combination = new Combination<E>();
        IntStream.range(0, size()).forEach(i -> combination.add(getElement(i, counter.get(i))));

        counter.add(1);
        return combination;
    }

    /**
     * Get the size of {@link #elements}.
     * @return The size of {@link #elements}.
     */
    private int size() {
        return elements.size();
    }

    /**
     * Gets the element at the specified position.
     * @param elementsIndex the specified position in {@link #elements} list.
     * @param combinationIndex {@link #elements} The specified position of {@link Block} in the list.
     * @return element
     */
    private E getElement(int elementsIndex, int combinationIndex) {
        return elements.get(elementsIndex).getElement(combinationIndex);
    }

    /**
     * A class that manages the combination of elements.
     * @param <E> the combination of elements.
     */
    private static class Block<E> {

        /** the combination of elements. */
        private final List<E> elements;

        /**
         * constructor
         * @param elements The value to set for {@link #elements}.
         */
        Block(List<E> elements) {
            this.elements = new ArrayList<>(elements);
        }

        /**
         * The value at the specified position of {@link #elements}.
         * @param index specified position
         * @return element
         */
        E getElement(int index) {
            return elements.get(index);
        }
    }

    /**
     * Combination of each elements.
     */
    public static class Combination<E> extends ArrayList<E> {

        /** A unique identifier for the Serializable class */
        private static final long serialVersionUID = 388200880735087714L;
    }
}
