package io.github.fdero.core;

import io.github.fdero.bits4j.core.BitSequence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BitSequenceTest {

    @Test
    void pushAndQueryTest() {
        BitSequence bitSequence = new BitSequence();
        bitSequence.pushOne();
        bitSequence.pushZero();
        assertTrue(bitSequence.get(0));
        assertFalse(bitSequence.get(1));
        assertEquals(2, bitSequence.size());
    }
}
