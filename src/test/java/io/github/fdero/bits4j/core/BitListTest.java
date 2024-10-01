package io.github.fdero.bits4j.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BitListTest {

    @Test
    void emptyBitListSizeIsZero() {
        assertEquals(0, new BitList().size());
    }

    @Test
    void afterAddingElementsSizeIncreasesAccordingly() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        assertEquals(3, bitList.size());
    }

    @Test
    void afterAddingElementsYouCanGetThemBack() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        assertEquals(BitValue.ONE, bitList.get(0));
        assertEquals(BitValue.ZERO, bitList.get(1));
        assertEquals(BitValue.ONE, bitList.get(2));
    }

    @Test
    void afterAddingElementsYouCanChangeThem() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.set(1, BitValue.ONE);
        assertEquals(BitValue.ONE, bitList.get(1));
    }

    @Test
    void convertEmptyBitListToObjectArray() {
        assertEquals(0, new BitList().toArray().length);
    }

    @Test
    void convertBitListToObjectArray() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        Object[] rawArray = bitList.toArray();
        assertEquals(3, rawArray.length);
        assertEquals(BitValue.ONE, rawArray[0]);
        assertEquals(BitValue.ZERO, rawArray[1]);
        assertEquals(BitValue.ONE, rawArray[2]);
    }

    @Test
    void convertEmptyBitListToBitValueArray() {
        BitValue[] rawArray = {};
        assertEquals(0, new BitList().toArray(rawArray).length);
    }

    @Test
    void convertBitListToBitValueArray() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        BitValue[] rawArray = {};
        rawArray = bitList.toArray(rawArray);
        assertEquals(3, rawArray.length);
        assertEquals(BitValue.ONE, rawArray[0]);
        assertEquals(BitValue.ZERO, rawArray[1]);
        assertEquals(BitValue.ONE, rawArray[2]);
    }

    @Test
    void removeOneFromFilledBitList() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.remove(BitValue.ONE);
        assertEquals(2, bitList.size());
        assertEquals(BitValue.ZERO, bitList.get(0));
        assertEquals(BitValue.ONE, bitList.get(1));
    }

    @Test
    void removeOneFirstAndLastFromFilledBitListUsingRemove() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.remove(BitValue.ONE);
        bitList.remove(BitValue.ONE);
        assertEquals(1, bitList.size());
        assertEquals(BitValue.ZERO, bitList.get(0));
    }

    @Test
    void removeLastFromFilledBitListUsingRemoveLast() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.removeLast();
        bitList.removeLast();
        assertEquals(1, bitList.size());
        assertEquals(BitValue.ONE, bitList.get(0));
    }

    @Test
    void removeFirstFromFilledBitListUsingAtIndex() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.remove(0);
        assertEquals(2, bitList.size());
        assertEquals(BitValue.ZERO, bitList.get(0));
        assertEquals(BitValue.ONE, bitList.get(1));
    }

    @Test
    void removeMiddleFromFilledBitListUsingAtIndex() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.remove(1);
        assertEquals(2, bitList.size());
        assertEquals(BitValue.ONE, bitList.get(0));
        assertEquals(BitValue.ONE, bitList.get(1));
    }

    @Test
    void removeLastFromFilledBitListUsingAtIndex() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.remove(2);
        assertEquals(2, bitList.size());
        assertEquals(BitValue.ONE, bitList.get(0));
        assertEquals(BitValue.ZERO, bitList.get(1));
    }

    @Test
    void AddOneToEmptyBitListUsingAddAtIndex() {
        BitList bitList = new BitList();
        bitList.add(0, BitValue.ONE);
        assertEquals(1, bitList.size());
        assertEquals(BitValue.ONE, bitList.get(0));
    }

    @Test
    void AddOneToFrontOfFilledBitListUsingAddAtIndex() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(0, BitValue.ONE);
        assertEquals(4, bitList.size());
        assertEquals(BitValue.ONE, bitList.get(0));
    }

    @Test
    void AddOneToMiddleOfFilledBitListUsingAddAtIndex() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(1, BitValue.ONE);
        assertEquals(4, bitList.size());
        assertEquals(BitValue.ONE, bitList.get(1));
    }

    @Test
    void AddOneToBackOfFilledBitListUsingAddAtIndex() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(2, BitValue.ONE);
        assertEquals(4, bitList.size());
        assertEquals(BitValue.ONE, bitList.get(2));
    }
}