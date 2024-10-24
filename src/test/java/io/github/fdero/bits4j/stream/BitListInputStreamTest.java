package io.github.fdero.bits4j.stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import io.github.fdero.bits4j.core.BitList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.fdero.bits4j.core.BitListConversions;
import io.github.fdero.bits4j.core.BitValue;

public class BitListInputStreamTest {
    
    @Test
    void testReadOfZeroBits() throws IOException {
        List<BitValue> bitList = List.of();
        try (InputStream bitInputStream = new BitListInputStream(bitList)) {
            assertEquals(-1, bitInputStream.read());
        }
    }

    @Test
    void testReadOfSomeBitsInsufficientToFormByte() throws IOException {
        List<BitValue> bitList = List.of(BitValue.ONE, BitValue.ZERO, BitValue.ONE);
        try (InputStream bitInputStream = new BitListInputStream(bitList)) {
            assertEquals(-1, bitInputStream.read());
        }
    }

    @Test
    void testReadOfSomeBitsThatFormsBytes() throws IOException {
        List<BitValue> bitList = BitListConversions.fromByte((byte) 'a');
        bitList.addAll(BitListConversions.fromByte((byte) 'b'));
        bitList.add(BitValue.ONE);
        bitList.add(BitValue.ZERO);
        try (InputStream bitInputStream = new BitListInputStream(bitList)) {
            assertEquals((byte) 'a', bitInputStream.read());
            assertEquals((byte) 'b', bitInputStream.read());
            assertEquals(-1, bitInputStream.read());
        }
    }

    @Test
    void testReadOfSomeBitsThatFormsExactBytes() throws IOException {
        List<BitValue> bitList = BitListConversions.fromByte((byte) 'a');
        bitList.addAll(BitListConversions.fromByte((byte) 'b'));
        try (InputStream bitInputStream = new BitListInputStream(bitList)) {
            assertEquals((byte) 'a', bitInputStream.read());
            assertEquals((byte) 'b', bitInputStream.read());
            assertEquals(-1, bitInputStream.read());
        }
    }
}
