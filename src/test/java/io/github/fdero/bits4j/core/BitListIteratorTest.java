package io.github.fdero.bits4j.core;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

public class BitListIteratorTest {

    @Test
    void iteratorOnEmptyBitListHasNoNext() {
        BitListIterator bitListIterator = new BitListIterator(new BitList(), 0);
        BitListIterator bitListIterator2 = new BitListIterator(new BitList(), 1);
        assertFalse(bitListIterator.hasNext());
        assertFalse(bitListIterator2.hasNext());
    }

    @Test
    void iteratorOnEmptyBitListHasNoPrev() {
        BitListIterator bitListIterator = new BitListIterator(new BitList(), 0);
        BitListIterator bitListIterator2 = new BitListIterator(new BitList(), 1);
        assertFalse(bitListIterator.hasPrevious());
        assertFalse(bitListIterator2.hasPrevious());
    }

    @Test
    void iteratorOnSingleElementBitListHasGoodBehaviour() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        BitListIterator bitListIterator = new BitListIterator(bitList, 0);
        BitListIterator bitListIterator2 = new BitListIterator(bitList, 1);
        assertTrue(bitListIterator.hasNext());
        assertFalse(bitListIterator2.hasNext());
    }

    @Test
    void iteratorOnSingleElementBitListHasNoPrev() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        BitListIterator bitListIterator = new BitListIterator(bitList, 0);
        BitListIterator bitListIterator2 = new BitListIterator(bitList, 1);
        assertFalse(bitListIterator.hasPrevious());
        assertTrue(bitListIterator2.hasPrevious());
    }

    @Test
    void forwardIterationWorksAsExpected() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ONE);
        double binaryRepresentation = 0;
        int iterationCounter = 0;
        BitListIterator bitListIterator = new BitListIterator(bitList, 0);
        while (bitListIterator.hasNext()) {
            BitValue extracted = bitListIterator.next();
            if (extracted.equals(BitValue.ONE)) {
                binaryRepresentation += Math.pow(2, iterationCounter);
            }
            iterationCounter++;
        }
        assertEquals(2+8+16, binaryRepresentation);
    }

    @Test
    void backwardsIterationWorksAsExpected() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ONE);
        double binaryRepresentation = 0;
        int iterationCounter = 0;
        BitListIterator bitListIterator = new BitListIterator(bitList, 6);
        while (bitListIterator.hasPrevious()) {
            BitValue extracted = bitListIterator.previous();
            if (extracted.equals(BitValue.ONE)) {
                binaryRepresentation += Math.pow(2, iterationCounter);
            }
            iterationCounter++;
        }
        assertEquals(1+2+8+32, binaryRepresentation);
    }

    @Test
    void comparativeForwardIterationWithArrayList() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ONE);
        ArrayList<BitValue> arrayList = new ArrayList<>();
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ONE);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ONE);
        arrayList.add(BitValue.ONE);
        ListIterator<BitValue> referenceIterator = arrayList.listIterator(0);
        ListIterator<BitValue> testedIterator = bitList.listIterator(0);
        while (referenceIterator.hasNext() || testedIterator.hasNext()) {
            assertEquals(referenceIterator.hasNext(), testedIterator.hasNext());
            assertEquals(referenceIterator.next(), testedIterator.next());
        }
    }

    @Test
    void comparativeBackwardsIterationWithArrayList() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ONE);
        ArrayList<BitValue> arrayList = new ArrayList<>();
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ONE);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ONE);
        arrayList.add(BitValue.ONE);
        ListIterator<BitValue> referenceIterator = arrayList.listIterator(5);
        ListIterator<BitValue> testedIterator = bitList.listIterator(5);
        while (referenceIterator.hasPrevious() || testedIterator.hasPrevious()) {
            assertEquals(referenceIterator.hasPrevious(), testedIterator.hasPrevious());
            assertEquals(referenceIterator.previous(), testedIterator.previous());
        }
    }

    @Test
    void comparativeForwardIterationWithArrayListWhileAddingNewElements() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ONE);
        ArrayList<BitValue> arrayList = new ArrayList<>();
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ONE);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ONE);
        arrayList.add(BitValue.ONE);
        ListIterator<BitValue> referenceIterator = arrayList.listIterator(0);
        ListIterator<BitValue> testedIterator = bitList.listIterator(0);
        int iterationCounter = 0;
        while (referenceIterator.hasNext() || testedIterator.hasNext()) {

            if (++iterationCounter == 2) {
                referenceIterator.add(BitValue.ONE);
                testedIterator.add(BitValue.ONE);
            }
            assertEquals(referenceIterator.hasNext(), testedIterator.hasNext());
            assertEquals(referenceIterator.next(), testedIterator.next());
        }
    }

    @Test
    void comparativeBackwardsIterationWithArrayListWhileAddingNewElements() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ONE);
        ArrayList<BitValue> arrayList = new ArrayList<>();
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ONE);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ONE);
        arrayList.add(BitValue.ONE);
        ListIterator<BitValue> referenceIterator = arrayList.listIterator(5);
        ListIterator<BitValue> testedIterator = bitList.listIterator(5);
        int iterationCounter = 0;
        while (referenceIterator.hasPrevious() || testedIterator.hasPrevious()) {

            if (++iterationCounter == 2) {
                referenceIterator.add(BitValue.ONE);
                testedIterator.add(BitValue.ONE);
            }
            assertEquals(referenceIterator.hasPrevious(), testedIterator.hasPrevious());
            assertEquals(referenceIterator.previous(), testedIterator.previous());
        }
    }

    @Test
    void comparativeForwardIterationWithArrayListWhileRemovingElements() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ONE);
        ArrayList<BitValue> arrayList = new ArrayList<>();
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ONE);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ONE);
        arrayList.add(BitValue.ONE);
        ListIterator<BitValue> referenceIterator = arrayList.listIterator(0);
        ListIterator<BitValue> testedIterator = bitList.listIterator(0);
        int iterationCounter = 0;
        while (referenceIterator.hasNext() || testedIterator.hasNext()) {
            assertEquals(referenceIterator.hasNext(), testedIterator.hasNext());
            assertEquals(referenceIterator.next(), testedIterator.next());
            if (++iterationCounter == 2) {
                referenceIterator.remove();
                testedIterator.remove();
            }
        }
    }

    @Test
    void comparativeBackwardsIterationWithArrayListWhileRemovingElements() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ONE);
        ArrayList<BitValue> arrayList = new ArrayList<>();
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ONE);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ONE);
        arrayList.add(BitValue.ONE);
        ListIterator<BitValue> referenceIterator = arrayList.listIterator(5);
        ListIterator<BitValue> testedIterator = bitList.listIterator(5);
        int iterationCounter = 0;
        while (referenceIterator.hasPrevious() || testedIterator.hasPrevious()) {
            assertEquals(referenceIterator.hasPrevious(), testedIterator.hasPrevious());
            assertEquals(referenceIterator.previous(), testedIterator.previous());
            if (++iterationCounter == 2) {
                referenceIterator.remove();
                testedIterator.remove();
            }
        }
    }

    @Test
    void comparativeForwardIterationWithArrayListWhileSettingElements() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ONE);
        ArrayList<BitValue> arrayList = new ArrayList<>();
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ONE);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ONE);
        arrayList.add(BitValue.ONE);
        ListIterator<BitValue> referenceIterator = arrayList.listIterator(0);
        ListIterator<BitValue> testedIterator = bitList.listIterator(0);
        int iterationCounter = 0;
        while (referenceIterator.hasNext() || testedIterator.hasNext()) {
            assertEquals(referenceIterator.hasNext(), testedIterator.hasNext());
            assertEquals(referenceIterator.next(), testedIterator.next());
            if (++iterationCounter == 2) {
                referenceIterator.set(BitValue.ONE);
                testedIterator.set(BitValue.ONE);
            }
        }
    }

    @Test
    void comparativeBackwardsIterationWithArrayListWhileSettingElements() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ONE);
        ArrayList<BitValue> arrayList = new ArrayList<>();
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ONE);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ONE);
        arrayList.add(BitValue.ONE);
        ListIterator<BitValue> referenceIterator = arrayList.listIterator(5);
        ListIterator<BitValue> testedIterator = bitList.listIterator(5);
        int iterationCounter = 0;
        while (referenceIterator.hasPrevious() || testedIterator.hasPrevious()) {
            assertEquals(referenceIterator.hasPrevious(), testedIterator.hasPrevious());
            assertEquals(referenceIterator.previous(), testedIterator.previous());
            if (++iterationCounter == 2) {
                referenceIterator.set(BitValue.ONE);
                testedIterator.set(BitValue.ONE);
            }
        }
    }

    @Test
    void compareListsAfterSetViaIteratorsInitializedWithNext() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        ArrayList<BitValue> arrayList = new ArrayList<>();
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        ListIterator<BitValue> referenceIterator = arrayList.listIterator(2);
        ListIterator<BitValue> testedIterator = bitList.listIterator(2);
        assertEquals(referenceIterator.next(), testedIterator.next());
        referenceIterator.set(BitValue.ONE);
        testedIterator.set(BitValue.ONE);
        for (int i = 0; i < bitList.size(); i++) {
            assertEquals(arrayList.get(i), bitList.get(i));
        }
    }

    @Test
    void compareListsAfterRemoveViaIteratorsInitializedWithNext() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        ArrayList<BitValue> arrayList = new ArrayList<>();
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        ListIterator<BitValue> referenceIterator = arrayList.listIterator(2);
        ListIterator<BitValue> testedIterator = bitList.listIterator(2);
        assertEquals(referenceIterator.next(), testedIterator.next());
        referenceIterator.remove();
        testedIterator.remove();
        for (int i = 0; i < bitList.size(); i++) {
            assertEquals(arrayList.get(i), bitList.get(i));
        }
    }

    @Test
    void compareListsAfterAddViaIteratorsInitializedWithNext() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        ArrayList<BitValue> arrayList = new ArrayList<>();
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        ListIterator<BitValue> referenceIterator = arrayList.listIterator(2);
        ListIterator<BitValue> testedIterator = bitList.listIterator(2);
        assertEquals(referenceIterator.next(), testedIterator.next());
        referenceIterator.add(BitValue.ONE);
        testedIterator.add(BitValue.ONE);
        for (int i = 0; i < bitList.size(); i++) {
            assertEquals(arrayList.get(i), bitList.get(i));
        }
    }

    @Test
    void compareListsAfterSetViaIteratorsInitializedWithPrev() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        ArrayList<BitValue> arrayList = new ArrayList<>();
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        ListIterator<BitValue> referenceIterator = arrayList.listIterator(2);
        ListIterator<BitValue> testedIterator = bitList.listIterator(2);
        assertEquals(referenceIterator.previous(), testedIterator.previous());
        referenceIterator.set(BitValue.ONE);
        testedIterator.set(BitValue.ONE);
        for (int i = 0; i < bitList.size(); i++) {
            assertEquals(arrayList.get(i), bitList.get(i));
        }
    }

    @Test
    void compareListsAfterRemoveViaIteratorsInitializedWithPrev() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        ArrayList<BitValue> arrayList = new ArrayList<>();
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        ListIterator<BitValue> referenceIterator = arrayList.listIterator(2);
        ListIterator<BitValue> testedIterator = bitList.listIterator(2);
        assertEquals(referenceIterator.previous(), testedIterator.previous());
        referenceIterator.remove();
        testedIterator.remove();
        for (int i = 0; i < bitList.size(); i++) {
            assertEquals(arrayList.get(i), bitList.get(i));
        }
    }

    @Test
    void compareListsAfterAddViaIteratorsInitializedWithPrev() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        ArrayList<BitValue> arrayList = new ArrayList<>();
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        ListIterator<BitValue> referenceIterator = arrayList.listIterator(2);
        ListIterator<BitValue> testedIterator = bitList.listIterator(2);
        assertEquals(referenceIterator.previous(), testedIterator.previous());
        referenceIterator.add(BitValue.ONE);
        testedIterator.add(BitValue.ONE);
        for (int i = 0; i < bitList.size(); i++) {
            assertEquals(arrayList.get(i), bitList.get(i));
        }
    }

    @Test
    void compareListsAfterSetViaIteratorsInitializedWithPrevOnLastPosition() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        ArrayList<BitValue> arrayList = new ArrayList<>();
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        ListIterator<BitValue> referenceIterator = arrayList.listIterator(5);
        ListIterator<BitValue> testedIterator = bitList.listIterator(5);
        assertEquals(referenceIterator.previous(), testedIterator.previous());
        referenceIterator.set(BitValue.ONE);
        testedIterator.set(BitValue.ONE);
        for (int i = 0; i < bitList.size(); i++) {
            assertEquals(arrayList.get(i), bitList.get(i));
        }
    }

    @Test
    void compareListsAfterRemoveViaIteratorsInitializedWithPrevOnLastPosition() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        ArrayList<BitValue> arrayList = new ArrayList<>();
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        ListIterator<BitValue> referenceIterator = arrayList.listIterator(5);
        ListIterator<BitValue> testedIterator = bitList.listIterator(5);
        assertEquals(referenceIterator.previous(), testedIterator.previous());
        referenceIterator.remove();
        testedIterator.remove();
        for (int i = 0; i < bitList.size(); i++) {
            assertEquals(arrayList.get(i), bitList.get(i));
        }
    }

    @Test
    void compareListsAfterAddViaIteratorsInitializedWithPrevOnLastPosition() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        ArrayList<BitValue> arrayList = new ArrayList<>();
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        ListIterator<BitValue> referenceIterator = arrayList.listIterator(5);
        ListIterator<BitValue> testedIterator = bitList.listIterator(5);
        assertEquals(referenceIterator.previous(), testedIterator.previous());
        referenceIterator.add(BitValue.ONE);
        testedIterator.add(BitValue.ONE);
        for (int i = 0; i < bitList.size(); i++) {
            assertEquals(arrayList.get(i), bitList.get(i));
        }
    }

    @Test
    void compareListsAfterSetViaIteratorsInitializedWithNextOnFirstPosition() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        ArrayList<BitValue> arrayList = new ArrayList<>();
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        ListIterator<BitValue> referenceIterator = arrayList.listIterator(0);
        ListIterator<BitValue> testedIterator = bitList.listIterator(0);
        assertEquals(referenceIterator.next(), testedIterator.next());
        referenceIterator.set(BitValue.ONE);
        testedIterator.set(BitValue.ONE);
        for (int i = 0; i < bitList.size(); i++) {
            assertEquals(arrayList.get(i), bitList.get(i));
        }
    }

    @Test
    void compareListsAfterRemoveViaIteratorsInitializedWithNextOnFirstPosition() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        ArrayList<BitValue> arrayList = new ArrayList<>();
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        ListIterator<BitValue> referenceIterator = arrayList.listIterator(0);
        ListIterator<BitValue> testedIterator = bitList.listIterator(0);
        assertEquals(referenceIterator.next(), testedIterator.next());
        referenceIterator.remove();
        testedIterator.remove();
        for (int i = 0; i < bitList.size(); i++) {
            assertEquals(arrayList.get(i), bitList.get(i));
        }
    }

    @Test
    void compareListsAfterAddViaIteratorsInitializedWithNextOnFirstPosition() {
        BitList bitList = new BitList();
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        bitList.add(BitValue.ZERO);
        ArrayList<BitValue> arrayList = new ArrayList<>();
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        arrayList.add(BitValue.ZERO);
        ListIterator<BitValue> referenceIterator = arrayList.listIterator(0);
        ListIterator<BitValue> testedIterator = bitList.listIterator(0);
        assertEquals(referenceIterator.next(), testedIterator.next());
        referenceIterator.add(BitValue.ONE);
        testedIterator.add(BitValue.ONE);
        for (int i = 0; i < bitList.size(); i++) {
            assertEquals(arrayList.get(i), bitList.get(i));
        }
    }
}
