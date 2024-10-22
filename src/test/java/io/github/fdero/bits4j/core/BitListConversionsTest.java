package io.github.fdero.bits4j.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BitListConversionsTest {

    @Test
    void byteConversionTest() {
        byte byteValue = (byte) 'a';
        BitList bitList = BitListConversions.fromByte(byteValue);
        assertEquals(8, bitList.size());
        assertEquals(byteValue, BitListConversions.asByte(bitList));
    }

    @Test
    void integerConversionTest() {
        int integerValue = 4;
        BitList bitList = BitListConversions.fromInt(integerValue);
        assertEquals(32, bitList.size());
        assertEquals(integerValue, BitListConversions.asInt(bitList));
    }

    @Test
    void longConversionTest() {
        long byteValue = (byte) 'a';
        BitList bitList = BitListConversions.fromLong(byteValue);
        assertEquals(64, bitList.size());
        assertEquals(byteValue, BitListConversions.asLong(bitList));
    }

    @Test
    void binaryStringConversionTest() {
        String byteValue = "1101";
        BitList bitList = BitListConversions.fromBinaryString(byteValue);
        assertEquals(4, bitList.size());
        assertEquals(byteValue, BitListConversions.asBinaryString(bitList));
    }

    @Test
    void emptyBinaryStringConversionTest() {
        String byteValue = "";
        BitList bitList = BitListConversions.fromBinaryString(byteValue);
        assertTrue(bitList.isEmpty());
        assertEquals(byteValue, BitListConversions.asBinaryString(bitList));
    }
}