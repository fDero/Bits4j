package io.github.fdero.bits4j.core;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
    void removeAllFromEmptyBitList() {
        List<BitValue> bitValues = new ArrayList<>();
        bitValues.add(BitValue.ONE);
        bitValues.add(BitValue.ONE);
        bitValues.add(BitValue.ZERO);
        assertFalse(new BitList().removeAll(bitValues));
    }

    @Test
    void removeAllFromFullBitList() {
        List<BitValue> bitValues = new ArrayList<>();
        bitValues.add(BitValue.ONE);
        bitValues.add(BitValue.ONE);
        bitValues.add(BitValue.ZERO);
        BitList bitList = new BitList();
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        assertTrue(bitList.removeAll(bitValues));
        assertEquals(1, bitList.size());
        assertEquals(BitValue.ZERO, bitList.get(0));
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

    @Test
    void AddAllToEmptyBitListUsingAddAtIndex() {
        List<BitValue> bitValues = new ArrayList<>();
        bitValues.add(BitValue.ONE);
        bitValues.add(BitValue.ONE);
        bitValues.add(BitValue.ZERO);
        BitList bitList = new BitList();
        bitList.addAll(0, bitValues);
        assertEquals(3, bitList.size());
        assertEquals(BitValue.ONE, bitList.get(0));
        assertEquals(BitValue.ONE, bitList.get(1));
        assertEquals(BitValue.ZERO, bitList.get(2));
    }

    @Test
    void AddAllToFrontOfFilledBitListUsingAddAtIndex() {
        List<BitValue> bitValues = new ArrayList<>();
        bitValues.add(BitValue.ONE);
        bitValues.add(BitValue.ZERO);
        bitValues.add(BitValue.ONE);
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.addAll(0, bitValues);
        assertEquals(6, bitList.size());
        assertEquals(BitValue.ONE, bitList.get(0));
        assertEquals(BitValue.ZERO, bitList.get(1));
        assertEquals(BitValue.ONE, bitList.get(2));
        assertEquals(BitValue.ZERO, bitList.get(3));
        assertEquals(BitValue.ZERO, bitList.get(4));
        assertEquals(BitValue.ZERO, bitList.get(5));
    }

    @Test
    void AddAllToMiddleOfFilledBitListUsingAddAtIndex() {
        List<BitValue> bitValues = new ArrayList<>();
        bitValues.add(BitValue.ONE);
        bitValues.add(BitValue.ZERO);
        bitValues.add(BitValue.ONE);
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.addAll(1, bitValues);
        assertEquals(6, bitList.size());
        assertEquals(BitValue.ZERO, bitList.get(0));
        assertEquals(BitValue.ONE, bitList.get(1));
        assertEquals(BitValue.ZERO, bitList.get(2));
        assertEquals(BitValue.ONE, bitList.get(3));
        assertEquals(BitValue.ZERO, bitList.get(4));
        assertEquals(BitValue.ZERO, bitList.get(5));
    }

    @Test
    void AddAllToBackOfFilledBitListUsingAddAtIndex() {
        List<BitValue> bitValues = new ArrayList<>();
        bitValues.add(BitValue.ONE);
        bitValues.add(BitValue.ZERO);
        bitValues.add(BitValue.ONE);
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.addAll(2, bitValues);
        assertEquals(6, bitList.size());
        assertEquals(BitValue.ZERO, bitList.get(0));
        assertEquals(BitValue.ZERO, bitList.get(1));
        assertEquals(BitValue.ONE, bitList.get(2));
        assertEquals(BitValue.ZERO, bitList.get(3));
        assertEquals(BitValue.ONE, bitList.get(4));
        assertEquals(BitValue.ZERO, bitList.get(5));
    }

    @Test
    void AddAllToTailOfFilledBitListUsingAddAtIndex() {
        List<BitValue> bitValues = new ArrayList<>();
        bitValues.add(BitValue.ONE);
        bitValues.add(BitValue.ZERO);
        bitValues.add(BitValue.ONE);
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.addAll(3, bitValues);
        assertEquals(6, bitList.size());
        assertEquals(BitValue.ZERO, bitList.get(0));
        assertEquals(BitValue.ZERO, bitList.get(1));
        assertEquals(BitValue.ZERO, bitList.get(2));
        assertEquals(BitValue.ONE, bitList.get(3));
        assertEquals(BitValue.ZERO, bitList.get(4));
        assertEquals(BitValue.ONE, bitList.get(5));
    }

    @Test
    void AddAllToTailOfFilledBitListUsingAddAll() {
        List<BitValue> bitValues = new ArrayList<>();
        bitValues.add(BitValue.ONE);
        bitValues.add(BitValue.ZERO);
        bitValues.add(BitValue.ONE);
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.addAll(bitValues);
        assertEquals(6, bitList.size());
        assertEquals(BitValue.ZERO, bitList.get(0));
        assertEquals(BitValue.ZERO, bitList.get(1));
        assertEquals(BitValue.ZERO, bitList.get(2));
        assertEquals(BitValue.ONE, bitList.get(3));
        assertEquals(BitValue.ZERO, bitList.get(4));
        assertEquals(BitValue.ONE, bitList.get(5));
    }

    @Test
    void containsOnFullBitListWhenTrue() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        assertTrue(bitList.contains(BitValue.ONE));
    }

    @Test
    void containsOnFullBitListWhenFalse() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        assertFalse(bitList.contains(BitValue.ONE));
    }

    @Test
    void containsOnEmptyList() {
        assertFalse(new BitList().contains(BitValue.ONE));
    }

    @Test
    void containsAllOnFullBitListWhenTrue() {
        List<BitValue> bitValues = new ArrayList<>();
        bitValues.add(BitValue.ONE);
        bitValues.add(BitValue.ONE);
        bitValues.add(BitValue.ZERO);
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        assertTrue(bitList.containsAll(bitValues));
    }

    @Test
    void containsAllOnFullBitListWhenFalse() {
        List<BitValue> bitValues = new ArrayList<>();
        bitValues.add(BitValue.ONE);
        bitValues.add(BitValue.ONE);
        bitValues.add(BitValue.ZERO);
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        assertFalse(bitList.containsAll(bitValues));
    }

    @Test
    void containsAllOnEmptyList() {
        List<BitValue> bitValues = new ArrayList<>();
        bitValues.add(BitValue.ONE);
        bitValues.add(BitValue.ONE);
        bitValues.add(BitValue.ZERO);
        assertFalse(new BitList().containsAll(bitValues));
    }

    @Test
    void indexOfOnEmptyBitList() {
        assertEquals(-1, new BitList().indexOf(BitValue.ONE));
    }

    @Test
    void lastIndexOfOnEmptyBitList() {
        assertEquals(-1, new BitList().lastIndexOf(BitValue.ZERO));
    }

    @Test
    void indexOfOnFullBitList() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ONE);
        assertEquals(1, bitList.indexOf(BitValue.ONE));
    }

    @Test
    void lastIndexOfOnFullBitList() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        assertEquals(1, bitList.lastIndexOf(BitValue.ZERO));
    }
}