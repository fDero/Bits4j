package io.github.fdero.bits4j.core;

import java.util.BitSet;

class BitField {

    private final java.util.BitSet internalBitSetImplementation;

    BitField(java.util.BitSet internalBitSetImplementation) {
        this.internalBitSetImplementation = internalBitSetImplementation;
    }

    public BitField() {
        this(new java.util.BitSet());
    }

    public BitValue get(int index) {
        if (internalBitSetImplementation.get(index)) {
            return BitValue.ONE;
        }
        else {
            return BitValue.ZERO;
        }
    }

    public void set(int index, BitValue bitValue) {
        assert bitValue != null;
        if (bitValue.equals(BitValue.ONE)) {
            internalBitSetImplementation.set(index);
        }
        else {
            internalBitSetImplementation.clear(index);
        }
    }

    public void clear() {
        internalBitSetImplementation.clear();
    }

    protected BitSet getBitSet() {
        return internalBitSetImplementation;
    }
}
