package io.github.fdero.bits4j.stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import io.github.fdero.bits4j.core.BitList;
import io.github.fdero.bits4j.core.BitListConversions;
import io.github.fdero.bits4j.core.BitValue;

class BitWriterTest {

    @Test
    void testWriteOfZeroBits() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BitWriter bitOutputStream = new BitWriter(outputStream);
        bitOutputStream.flush();
        assertEquals(0, outputStream.toByteArray().length);
    }

    @Test
    void testWriteOfZeroBitsWithRoundup() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BitWriter bitOutputStream = new BitWriter(outputStream);
        bitOutputStream.flush();
        bitOutputStream.roundUp();
        assertEquals(0, outputStream.toByteArray().length);
    }

    @Test
    void testWriteOfFewBits() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BitWriter bitOutputStream = new BitWriter(outputStream);
        bitOutputStream.write(BitValue.ONE);
        bitOutputStream.write(BitValue.ONE);
        bitOutputStream.write(BitValue.ZERO);
        bitOutputStream.write(BitValue.ONE);
        bitOutputStream.roundUp();
        bitOutputStream.flush();
        assertEquals(1, outputStream.toByteArray().length);
        assertEquals(1+2+8, outputStream.toByteArray()[0]);
    }

    @Test
    void testWriteOfALittleMoreBits() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BitWriter bitOutputStream = new BitWriter(outputStream);
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
        bitOutputStream.flush();
        assertEquals(2, outputStream.toByteArray().length);
        assertEquals(1+2+8, outputStream.toByteArray()[0]);
        assertEquals(1, outputStream.toByteArray()[1]);
    }

    @Test
    void testWriteOfManyBits() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BitWriter bitOutputStream = new BitWriter(outputStream);
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
        bitOutputStream.flush();
        assertEquals(3, outputStream.toByteArray().length);
        for (int i = 0; i < inputBytes.length; i++) {
            assertEquals(inputBytes[i], outputStream.toByteArray()[i]);
        }
    }
}
