package io.github.fdero.bits4j.core;

import java.util.*;

/**
 * An efficient {@link List} implementation to store {@link BitValue}.
 */
public class BitList extends AbstractList<BitValue> {

    // The bit field that stores the bits
    private final BitField bitField;

    // The number of bits in the list
    private int size;

    /**
     * Constructs a new empty {@link BitList}.
     */
    public BitList() {
        this(new BitField(), 0);
    }

    /**
     * Constructs a new {@link BitList} with the given {@link BitField} and size.
     * 
     * @param bitField the bit field that stores the bits
     * @param size the number of bits in the list
     */
    BitList(BitField bitField, int size) {
        this.bitField = bitField;
        this.size = size;
    }

    /**
     * Returns the underlying {@link BitField} that stores the bits.
     * 
     * <p> This method is intended to be used to perform conversions between {@link BitList} and other types.
     *     It should not be used to modify the bits stored in the list. </p>
     * 
     * @return the underlying {@link BitField} that stores the bits
     */
    BitField getBitField() {
        return bitField;
    }

    /**
     * Returns the {@link BitValue} at the specified position in this list
     * 
     * @param index the index of the {@link BitValue} to return
     * 
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index >= size()})
     */
    @Override
    public BitValue get(int index) {
        if (index < 0  || index >= size) {
            throw new IndexOutOfBoundsException("Can't set a value at the specified index, it's out of bounds");
        }
        return bitField.get(index);
    }

    /**
     * Replaces the {@link BitValue} at the specified position in this list with the specified element as long as it's not null.
     * 
     * @param index the index of the {@link BitValue} to replace
     * 
     * @param bitValue the {@link BitValue} to be stored at the specified position
     * 
     * @throws IllegalArgumentException if the input value is null
     *         ({@code bitValue == null})
     * 
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index >= size()}) 
     */
    @Override
    public BitValue set(int index, BitValue bitValue) {
        if (index < 0  || index >= size) {
            throw new IndexOutOfBoundsException("Can't set a value at the specified index, it's out of bounds");
        }
        if (bitValue == null) {
            throw new IllegalArgumentException("Can't add a null BitValue to a BitList");
        }
        BitValue old = get(index);
        bitField.set(index, bitValue);
        return old;
    }

    /**
     * Returns the number of bits in this list.
     * 
     * @return the number of bits in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Resets the state of this list. 
     * 
     * <p> After calling this method, the list will result empty and
     *     will act as if it was a fresh new instance. </p>
     */
    @Override
    public void clear() {
        bitField.clear();
        this.size = 0;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * 
     * @param target the element to be removed from this list, if present
     * 
     * @return {@code true} if this list contained the specified element, {@code false} otherwise
     */
    @Override
    public boolean remove(Object target) {
        int index = indexOf(target);
        if (index == -1) {
            return false;
        }
        BitValue removedValue = remove(index);
        return (removedValue != null);
    }

    /**
     * Removes the last occurrence of the specified element from this list, if it is present.
     * 
     * @throws IndexOutOfBoundsException if the list is empty
     * 
     * @return {@code true} if this list contained the specified element, {@code false} otherwise
     */
    @Override
    public BitValue removeLast() {
        BitValue lastBit = get(size - 1);
        size--;
        return lastBit;
    }

    /**
     * Removes the bit at the specified position in this list.
     * 
     * @param index the index of the bit to be removed
     * 
     * @throws IndexOutOfBoundsException if the index is out of range
     *        ({@code index < 0 || index >= size()})
     * 
     * @return the bit that was removed from the list (since the list contains no null values, this method will never return null)
     */
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

    /**
     * Adds a {@code BitValue.ZERO} to the back of the list.
     * 
     * @return always {@code true} (inspired by {@link Collection#add}) since the list always gets updated 
     */
    public boolean addZero() {
        bitField.set(size, BitValue.ZERO);
        size++;
        return true;
    }

    /**
     * Adds a {@code BitValue.ONE} to the back of the list.
     * 
     * @return always {@code true} (inspired by {@link Collection#add}) since the list always gets updated 
     */
    public boolean addOne() {
        bitField.set(size, BitValue.ONE);
        size++;
        return true;
    }

    /**
     * Adds a {@link BitValue} to the front of the list.
     * 
     * @param bitValue the {@link BitValue} to add to the front of the list
     * 
     * @throws IllegalArgumentException if the input value is null
     *        ({@code bitValue == null})
     * 
     * @return always {@code true} (as specified by {@link Collection#add}) since the list always gets updated 
     */
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

    /**
     * Adds a {@link BitValue} to the specified position in this list.
     * 
     * @param index the index at which the specified element is to be inserted
     * 
     * @param bitValue the {@link BitValue} to be inserted
     * 
     * @throws IllegalArgumentException if the input value is null
     *         ({@code bitValue == null})
     * 
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index > size()})
     */
    @Override
    public void add(int index, BitValue bitValue) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        add(bitValue);
        for (int i = size - 1; i > index; i--) {
            BitValue leftBitValue = get(i - 1);
            set(i, leftBitValue);
        }
        set(index, bitValue);
    }
}
