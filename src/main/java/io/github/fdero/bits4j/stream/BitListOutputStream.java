package io.github.fdero.bits4j.stream;

import java.io.OutputStream;
import java.util.List;

import io.github.fdero.bits4j.core.BitList;
import io.github.fdero.bits4j.core.BitListConversions;
import io.github.fdero.bits4j.core.BitValue;

/**
 * An {@link OutputStream} that writes bytes by adding them as sequences of eight bits 
 * to a {@link List} of {@link BitValue}.
 * 
 * <p> This class is intended to be used in conjunction with {@link BitWriter}, 
 *     by providing a way to write bytes to a {@link List} of {@link BitValue}
 *     using the {@link java.io.OutputStream} interface. </p>
 * 
 * @see io.github.fdero.bits4j.stream.BitWriter
 * @see java.io.OutputStream
 * 
 */
public class BitListOutputStream extends OutputStream {
    
    // The list of bits to write bytes to
    private final List<BitValue> bitList;

    /**
     * Constructs a new {@link BitListOutputStream} that writes bytes to the given {@link java.util.List} of {@link io.github.fdero.bits4j.core.BitValue}.
     * 
     * @param bitList the list of bits to write bytes to
     */
    public BitListOutputStream(List<BitValue> bitList) {
        this.bitList = bitList;
    }

    /**
     * Writes an {@code int} encoded byte by adding them as sequences of 8 bits 
     * to the underlying {@link List} of {@link BitValue}
     * 
     * <p> writing happens immediately, no buffers are used </p>
     */
    @Override
    public void write(int b) {
        assert b >= 0 && b <= 255;
        BitList byteBits = BitListConversions.fromByte((byte) b);
        bitList.addAll(byteBits);
    }
}