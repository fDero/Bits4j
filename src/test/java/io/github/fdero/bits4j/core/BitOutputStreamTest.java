package io.github.fdero.bits4j.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class BitOutputStreamTest {

    @Test
    void testWriteOfZeroBits() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BitOutputStream bitOutputStream = new BitOutputStream(outputStream);
        bitOutputStream.tryFlush();
        assertEquals(0, outputStream.toByteArray().length);
    }

    @Test
    void testWriteOfZeroBitsWithRoundup() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BitOutputStream bitOutputStream = new BitOutputStream(outputStream);
        bitOutputStream.tryFlush();
        bitOutputStream.roundUp();
        assertEquals(0, outputStream.toByteArray().length);
    }

    @Test
    void testWriteOfFewBits() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BitOutputStream bitOutputStream = new BitOutputStream(outputStream);
        bitOutputStream.write(BitValue.ONE);
        bitOutputStream.write(BitValue.ONE);
        bitOutputStream.write(BitValue.ZERO);
        bitOutputStream.write(BitValue.ONE);
        bitOutputStream.roundUp();
        bitOutputStream.tryFlush();
        assertEquals(1, outputStream.toByteArray().length);
        assertEquals(1+2+8, outputStream.toByteArray()[0]);
    }

    @Test
    void testWriteOfALittleMoreBits() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BitOutputStream bitOutputStream = new BitOutputStream(outputStream);
        bitOutputStream.write(BitValue.ONE);
        bitOutputStream.write(BitValue.ONE);
        bitOutputStream.write(BitValue.ZERO);
        bitOutputStream.write(BitValue.ONE);
        bitOutputStream.write(BitValue.ZERO);
        bitOutputStream.write(BitValue.ZERO);
        bitOutputStream.write(BitValue.ZERO);
        bitOutputStream.write(BitValue.ZERO);
        bitOutputStream.write(BitValue.ONE);
        bitOutputStream.roundUp();
        bitOutputStream.tryFlush();
        assertEquals(2, outputStream.toByteArray().length);
        assertEquals(1+2+8, outputStream.toByteArray()[0]);
        assertEquals(1, outputStream.toByteArray()[1]);
    }

    @Test
    void testWriteOfManyBits() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BitOutputStream bitOutputStream = new BitOutputStream(outputStream);
        BitList inputBuffer = new BitList();
        byte[] inputBytes = new byte[]{ (byte)17, (byte)16, (byte)15 };
        BitList firstByteAsBitList = BitListConversions.fromByte(inputBytes[0]);
        BitList secondByteAsBitList = BitListConversions.fromByte(inputBytes[1]);
        BitList thirdByteAsBitList = BitListConversions.fromByte(inputBytes[2]);
        inputBuffer.addAll(firstByteAsBitList);
        inputBuffer.addAll(secondByteAsBitList);
        inputBuffer.addAll(thirdByteAsBitList);
        for (BitValue bitValue : inputBuffer) {
            bitOutputStream.write(bitValue);
        }
        bitOutputStream.roundUp();
        bitOutputStream.tryFlush();
        assertEquals(3, outputStream.toByteArray().length);
        for (int i = 0; i < inputBytes.length; i++) {
            assertEquals(inputBytes[i], outputStream.toByteArray()[i]);
        }
    }
}
