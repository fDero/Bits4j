package io.github.fdero.bits4j.core;

import java.util.BitSet;

public class BitSequence {

    private final BitSet bitSet;
    private int size;

    private BitSequence(BitSet bitSet, int size) {
        this.bitSet = bitSet;
        this.size = size;
    }

    public BitSequence() {
        this(new BitSet(), 0);
    }

    public void pushZero() {
        bitSet.clear(size);
        size++;
    }

    public void pushOne() {
        bitSet.set(size);
        size++;
    }

    public int size() {
        return size;
    }

    public boolean get(int index) {
        if (index > size) {
            throw new ArithmeticException();
        }
        return bitSet.get(index);
    }
}
