package io.github.fdero.bits4j.core;

import java.util.*;

public class BitList implements List<BitValue> {

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
        if (index < 0  || index > size) {
            throw new IndexOutOfBoundsException("Can't set a value at the specified index, it's out of bounds");
        }
        if (bitValue == null) {
            throw new IllegalArgumentException("Can't add a null BitValue to a BitList");
        }
        if (index == size) {
            add(bitValue);
            return null;
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
    public boolean isEmpty() {
        return size == 0;
    }

    protected ArrayList<BitValue> toArrayList(int fromIndex, int toIndex) {
        ArrayList<BitValue> arrayList = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            BitValue bitValue = get(i);
            arrayList.add(bitValue);
        }
        return arrayList;
    }

    protected ArrayList<BitValue> toArrayList() {
        return toArrayList(0, size);
    }

    @Override
    public Object[] toArray() {
        return toArrayList().toArray();
    }

    @Override
    public <T> T[] toArray(T[] alreadyExistingArray) {
        return toArrayList().toArray(alreadyExistingArray);
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

    @Override
    public boolean removeAll(Collection<?> targets) {
        boolean listHasChanged = false;
        for (Object target : targets) {
            listHasChanged |= remove(target);
        }
        return listHasChanged;
    }

    @Override
    public boolean contains(Object target) {
        return indexOf(target) != -1;
    }

    @Override
    public boolean containsAll(Collection<?> targets) {
        boolean oneIsAlreadyBeenFound = false;
        boolean zeroIsAlreadyBeenFound = false;
        for (Object target : targets) {
            boolean searchingForOne = Objects.equals(target, BitValue.ONE);
            boolean searchingForZero = Objects.equals(target, BitValue.ZERO);
            boolean worthSearching = searchingForOne || searchingForZero;
            if (!worthSearching) {
                return false;
            }
            boolean alreadyTakenCareOf = searchingForOne && oneIsAlreadyBeenFound;
            alreadyTakenCareOf |= searchingForZero && zeroIsAlreadyBeenFound;
            if (alreadyTakenCareOf) {
                continue;
            }
            boolean success = true;
            if (searchingForOne) {
                boolean oneIsFound = contains(BitValue.ONE);
                success &= oneIsFound;
                oneIsAlreadyBeenFound = oneIsFound;
            }
            if (searchingForZero) {
                boolean zeroIsFound = contains(BitValue.ZERO);
                success &= zeroIsFound;
                zeroIsAlreadyBeenFound = true;
            }
            if (!success) {
                return false;
            }
        }
        return true;
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
    public boolean addAll(Collection<? extends BitValue> bitValues) {
        for (BitValue bitValue : bitValues) {
            add(bitValue);
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

    @Override
    public boolean addAll(int index, Collection<? extends BitValue> bitValues) {
        for (BitValue bitValue : bitValues) {
            add(index, bitValue);
            index++;
        }
        return (!bitValues.isEmpty());
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not Implemented Yet!");
    }

    @Override
    public int indexOf(Object target) {
        if (target == null) {
            return -1;
        }
        for (int i = 0; i < size; i++) {
            BitValue currentValue = get(i);
            if (currentValue.equals(target)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object target) {
        if (target == null) {
            return -1;
        }
        for (int i = size - 1; i >= 0; i--) {
            BitValue currentValue = get(i);
            if (currentValue.equals(target)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<BitValue> iterator() {
        return listIterator();
    }

    @Override
    public ListIterator<BitValue> listIterator() {
        return new BitListIterator(this, -1);
    }

    @Override
    public ListIterator<BitValue> listIterator(int index) {
        return new BitListIterator(this, index);
    }

    @Override
    public List<BitValue> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not Implemented Yet!");
    }
}
