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
        assertEquals(0, bitList.size());
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
    void removeFromSingleElementBitList() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ONE);
        bitList.remove(0);
        assertEquals(0, bitList.size());
        assertTrue(bitList.isEmpty());
    }

    @Test
    void removeMultipleElementsSequentially() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);

        bitList.remove(0);
        assertEquals(3, bitList.size());
        bitList.remove(1);
        assertEquals(2, bitList.size());
        bitList.remove(1);
        assertEquals(1, bitList.size());
        assertEquals(BitValue.ZERO, bitList.get(0));
    }

    @Test
    void throwsExceptionWhenRemovingNegativeIndex() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> new BitList().remove(-1)
        );
    }

    @Test
    void throwsExceptionWhenRemovingOutOfBounds() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> new BitList().remove(0)
        );
    }

    @Test
    void removeAllElementsOneByOne() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);

        int originalSize = bitList.size();
        for (int i = 0; i < originalSize; i++) {
            bitList.remove(0);
            assertEquals(originalSize - (i + 1), bitList.size());
        }
        assertTrue(bitList.isEmpty());
    }

    @Test
    void removeAndAddMixedOperations() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.remove(0);
        bitList.add(BitValue.ONE);
        bitList.remove(1);
        assertEquals(1, bitList.size());
        assertEquals(BitValue.ZERO, bitList.get(0));
    }

    @Test
    void addToEmptyBitListUsingAddOne() {
        BitList bitList = new BitList();
        assertTrue(bitList.addOne());
        assertEquals(1, bitList.size());
        assertEquals(BitValue.ONE, bitList.get(0));
    }

    @Test
    void addToEmptyBitListUsingAddZero() {
        BitList bitList = new BitList();
        assertTrue(bitList.addZero());
        assertEquals(1, bitList.size());
        assertEquals(BitValue.ZERO, bitList.get(0));
    }

    @Test
    void addToEmptyBitListUsingAddAtIndex() {
        BitList bitList = new BitList();
        bitList.add(0, BitValue.ONE);
        assertEquals(1, bitList.size());
        assertEquals(BitValue.ONE, bitList.get(0));
    }

    @Test
    void addToFrontOfFilledBitListUsingAddAtIndex() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(0, BitValue.ONE);
        assertEquals(4, bitList.size());
        assertEquals(BitValue.ONE, bitList.get(0));
    }

    @Test
    void addToMiddleOfFilledBitListUsingAddAtIndex() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(1, BitValue.ONE);
        assertEquals(4, bitList.size());
        assertEquals(BitValue.ONE, bitList.get(1));
    }

    @Test
    void addToBackOfFilledBitListUsingAddAtIndex() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(2, BitValue.ONE);
        assertEquals(4, bitList.size());
        assertEquals(BitValue.ONE, bitList.get(2));
    }

    @Test
    void addToActualEndOfFilledBitListUsingAddAtIndex() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(3, BitValue.ONE);
        assertEquals(4, bitList.size());
        assertEquals(BitValue.ONE, bitList.get(3));
    }

    @Test
    void throwsExceptionForNegativeIndex() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> new BitList().add(-1, BitValue.ONE)
        );
    }

    @Test
    void throwsExceptionForTooLargeIndex() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> new BitList().add(1, BitValue.ONE)
        );
    }

    @Test
    void addAllToEmptyBitListUsingAddAtIndex() {
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
    void addAllToFrontOfFilledBitListUsingAddAtIndex() {
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
    void addAllToMiddleOfFilledBitListUsingAddAtIndex() {
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
    void addAllToBackOfFilledBitListUsingAddAtIndex() {
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
    void addAllToTailOfFilledBitListUsingAddAtIndex() {
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
    void addAllToTailOfFilledBitListUsingAddAll() {
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