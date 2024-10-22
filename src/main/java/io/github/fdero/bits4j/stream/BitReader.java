package io.github.fdero.bits4j.stream;

import java.io.IOException;
import java.io.InputStream;

import io.github.fdero.bits4j.core.BitList;
import io.github.fdero.bits4j.core.BitListConversions;
import io.github.fdero.bits4j.core.BitValue;

/**
 * A class that reads one bit at a time from an {@link InputStream}.
 * 
 * <p> While {@link InputStream} allows reading bytes, this class allows reading bits.
 *     It reads bits from the input stream one at a time, and returns them as {@link BitValue}.
 *     When the end of the input stream is reached, it returns {@code null}. </p>
 */
public class BitReader {

    // The input stream to read bits from
    private final InputStream inputStream;

    // The buffer that holds the bits read from the input stream
    private BitList buffer = null;
    
    // The index of the next bit to read from the buffer
    private int bufferIndex = 0;

    /**
     * Constructs a new {@link BitReader} that reads bits from the given {@link InputStream}.
     * 
     * @param inputStream the input stream to read bits from
     */
    public BitReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * Updates internal buffer with the next byte from the input stream if needed.
     * 
     * <p> If the buffer is null or all bits in the buffer have been read, this method 
     *     reads the next byte from the {@link InputStream} </p
     * 
     * @throws IOException if an I/O error occurs during the read operation from the underlying {@link InputStream}
     */
    private void setupBuffer() throws IOException {
        if (buffer == null || bufferIndex == 8) {
            int integerEncodedByte = inputStream.read();
            if (integerEncodedByte == -1) {
                buffer = null;
                bufferIndex = 0;
                return;
            }
            byte byteValue = (byte) integerEncodedByte;
            buffer = BitListConversions.fromByte(byteValue);
            bufferIndex = 0;
        }
    }

    /**
     * Reads the next bit from the input stream.
     * 
     * <p> This method reads the next bit from the input stream and returns it as {@link BitValue}. </p>
     * 
     * <p> If the current buffer is null or all bits in the buffer have been read, this method reads the next byte from 
     *     underlying {@link java.io.InputStream}. If the end of the stream is reached, it returns {@code null} </p> 
     * 
     * @return the next bit from the input stream as {@link BitValue}, or {@code null} if the end of the input stream is reached
     * @throws IOException if an I/O error occurs during the read operation from the underlying {@link InputStream}
     */
    public BitValue read() throws IOException {
        setupBuffer();
        if (buffer == null) {
            return null;
        }
        int oldBufferIndex = bufferIndex;
        bufferIndex++;
        return buffer.get(oldBufferIndex);
    }
}
