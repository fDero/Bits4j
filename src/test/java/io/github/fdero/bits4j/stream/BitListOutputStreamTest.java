package io.github.fdero.bits4j.stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.github.fdero.bits4j.core.BitList;
import io.github.fdero.bits4j.core.BitListConversions;
import io.github.fdero.bits4j.core.BitValue;

public class BitListOutputStreamTest {

    @Test
    void testWriteOfZeroBits() throws IOException {
        List<BitValue> bitList = new BitList();
        OutputStream bitInputStream = new BitListOutputStream(bitList);
        bitInputStream.close();
        assertEquals(0, bitList.size());
    }

    @Test
    void testWriteOfSomeBits() throws IOException {
        List<BitValue> bitList = new BitList();
        byte[] inputBytes = new byte[]{ (byte)'a', (byte)'b', (byte)'c', (byte)'d' };
        try (OutputStream bitInputStream = new BitListOutputStream(bitList)) {
            bitInputStream.write(inputBytes[0]);
            bitInputStream.write(inputBytes[1]);
            bitInputStream.write(inputBytes[2]);
        }
        assertEquals(3*8, bitList.size());
        for (int i = 0; i < 3; i++) {
            BitList byteBits = new BitList();
            byteBits.addAll(bitList.subList(i*8, (i+1)*8));
            byte outputByte = BitListConversions.asByte(byteBits);
            assertEquals(inputBytes[i], outputByte);
        }
    }
}
