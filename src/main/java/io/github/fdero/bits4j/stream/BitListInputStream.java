package io.github.fdero.bits4j.stream;

import java.io.InputStream;
import java.util.List;

import io.github.fdero.bits4j.core.BitList;
import io.github.fdero.bits4j.core.BitListConversions;
import io.github.fdero.bits4j.core.BitValue;

/**
 * An {@link InputStream} that reads bytes by iterating over a {@link List} of 
 * {@link BitValue} eight bytes at a time.
 */
public class BitListInputStream extends InputStream {
        
    // The list of bits to read bytes from
    private final List<BitValue> bitList;

    // The counter that keeps track of the current byte being read
    private int byteCounter = 0;

    /**
     * Constructs a new {@link BitListInputStream} that reads bytes from the given {@link List} of {@link BitValue}.
     * 
     * @param bitList the list of bits to read bytes from
     */
    public BitListInputStream(List<BitValue> bitList) {
        this.bitList = bitList;
    }

    /**
     * Reads a byte from the list of bits.
     * 
     * <p> Reads eight consecutive bits from the underlying {@link List} of {@link BitValue}
     *     if possible. If there are no more such sequences (hence, even if there are still seven bits or less) the stream act as if the end is reached. </p>
     * 
     * @return the next byte read from the list of bits, or -1 if the end of the stream is reached
     */
    @Override
    public int read() {
        if (byteCounter + 8 > bitList.size()) {
            return -1;
        }
        BitList byteBits = new BitList(); 
        byteBits.addAll(bitList.subList(byteCounter, byteCounter + 8));
        byteCounter += 8;
        return BitListConversions.asByte(byteBits);
    }
}
