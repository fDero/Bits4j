package io.github.fdero.bits4j.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

class BitInputStreamTest {

    @Test
    void testReadOfZeroBits() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(new byte[]{});
        BitInputStream bitInputStream = new BitInputStream(inputStream);
        BitList outputBuffer = new BitList();
        BitValue extractedValue = null;
        while ((extractedValue = bitInputStream.read()) != null) {
            outputBuffer.add(extractedValue);
        }
        assertTrue(outputBuffer.isEmpty());
    }

    @Test
    void testReadFewBits() throws IOException {
        byte inputByte = (byte) 'a';
        InputStream inputStream = new ByteArrayInputStream(new byte[]{inputByte});
        BitInputStream bitInputStream = new BitInputStream(inputStream);
        BitList outputBuffer = new BitList();
        BitValue extractedValue = null;
        while ((extractedValue = bitInputStream.read()) != null) {
            outputBuffer.add(extractedValue);
        }
        byte outputByte = BitListConversions.asByte(outputBuffer);
        assertEquals(inputByte, outputByte);
    }

    @Test
    void testReadManyBits() throws IOException {
        byte[] inputBytes = new byte[]{ (byte)'a', (byte)'b', (byte)'c', (byte)'d' };
        InputStream inputStream = new ByteArrayInputStream(inputBytes);
        BitInputStream bitInputStream = new BitInputStream(inputStream);
        BitList outputBuffer = new BitList();
        int inputBytesCursor = 0;
        BitValue extractedValue = null;
        while ((extractedValue = bitInputStream.read()) != null) {
            outputBuffer.add(extractedValue);
            if (outputBuffer.size() == 8) {
                byte outputByte = BitListConversions.asByte(outputBuffer);
                assertEquals(inputBytes[inputBytesCursor], outputByte);
                outputBuffer.clear();
                inputBytesCursor++;
            }
        }
        assertEquals(inputBytes.length, inputBytesCursor);
    }
}