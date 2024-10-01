package io.github.fdero.bits4j.core;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BitListIterator implements ListIterator<BitValue> {

    private final BitList bitList;
    private int currentIndex;
    private Integer lastElementReturned = null;

    BitListIterator(BitList bitList, int currentIndex) {
        if (currentIndex < 0) {
            throw new IllegalArgumentException();
        }
        this.bitList = bitList;
        this.currentIndex = currentIndex;
    }

    @Override
    public boolean hasNext() {
        try {
            BitValue extracted = bitList.get(nextIndex());
            return (extracted != null);
        }
        catch (Exception exception) {
            return false;
        }
    }

    @Override
    public boolean hasPrevious() {
        try {
            BitValue extracted = bitList.get(previousIndex());
            return (extracted != null);
        }
        catch (Exception exception) {
            return false;
        }
    }

    @Override
    public BitValue next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        lastElementReturned = nextIndex();
        BitValue extracted = bitList.get(currentIndex);
        currentIndex++;
        return extracted;
    }

    @Override
    public BitValue previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        lastElementReturned = previousIndex();
        currentIndex--;
        return bitList.get(currentIndex);
    }

    @Override
    public int nextIndex() {
        return currentIndex;
    }

    @Override
    public int previousIndex() {
        return currentIndex - 1;
    }

    @Override
    public void remove() {
        if (lastElementReturned == null) {
            throw new IllegalStateException();
        }
        bitList.remove(lastElementReturned.intValue());
        currentIndex = lastElementReturned;
        lastElementReturned = null;
    }

    @Override
    public void set(BitValue bitValue) {
        if (lastElementReturned == null) {
            throw new IllegalStateException("set() can only be called after next() or previous()");
        }
        bitList.set(lastElementReturned, bitValue);
    }

    @Override
    public void add(BitValue bitValue) {
        bitList.add(currentIndex, bitValue);
        currentIndex++;
        lastElementReturned = null;
    }
}
