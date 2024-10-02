package io.github.fdero.bits4j.core;

import java.util.*;

public class BitList extends AbstractList<BitValue> {

    protected final BitField bitSet;
    protected int size;

    protected BitList(BitField bitSet, int size) {
        this.bitSet = bitSet;
        this.size = size;
    }

    public BitList() {
        this(new BitField(), 0);
    }

    public BitValue get(int index) {
        if (index < 0  || index >= size) {
            throw new IndexOutOfBoundsException("Can't set a value at the specified index, it's out of bounds");
        }
        return bitSet.get(index);
    }

    @Override
    public BitValue set(int index, BitValue bitValue) {
        if (index < 0  || index >= size) {
            throw new IndexOutOfBoundsException("Can't set a value at the specified index, it's out of bounds");
        }
        if (bitValue == null) {
            throw new IllegalArgumentException("Can't add a null BitValue to a BitList");
        }
        BitValue old = get(index);
        bitSet.set(index, bitValue);
        return old;
    }

    public int size() {
        return size;
    }

    @Override
    public void clear() {
        bitSet.clear();
        this.size = 0;
    }

    @Override
    public boolean remove(Object target) {
        int index = indexOf(target);
        if (index == -1) {
            return false;
        }
        BitValue removedValue = remove(index);
        return (removedValue != null);
    }

    @Override
    public BitValue removeLast() {
        BitValue lastBit = get(size - 1);
        size--;
        return lastBit;
    }

    @Override
    public BitValue remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Can't remove element at the given index because it's out of bounds");
        }
        for (int i = index; i <= size - 2; i++) {
            BitValue followingValue = get(i+1);
            set(i, followingValue);
        }
        return removeLast();
    }

    public void addZero() {
        bitSet.set(size, BitValue.ZERO);
        size++;
    }

    public void addOne() {
        bitSet.set(size, BitValue.ONE);
        size++;
    }

    @Override
    public boolean add(BitValue bitValue) {
        if (bitValue == null) {
            throw new IllegalArgumentException("Can't add a null BitValue to a BitList");
        }
        switch (bitValue) {
            case ONE -> addOne();
            case ZERO -> addZero();
        }
        return true;
    }

    @Override
    public void add(int index, BitValue bitValue) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        add(bitValue);
        for (int i = size - 1; i > index; i--) {
            BitValue leftBitValue = get(i - 1);
            BitValue rightBitValue = get(i);
            set(i - 1, rightBitValue);
            set(i, leftBitValue);
        }
    }
}
