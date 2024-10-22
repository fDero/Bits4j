package io.github.fdero.bits4j.stream;

import java.io.IOException;
import java.io.OutputStream;

import io.github.fdero.bits4j.core.BitList;
import io.github.fdero.bits4j.core.BitListConversions;
import io.github.fdero.bits4j.core.BitValue;

/**
 * A class that writes one bit at a time to an {@link OutputStream}.
 * 
 * <p> While {@link OutputStream} allows writing bytes, this class allows writing bits.
 *     It writes bits to the output stream one at a time, expecting to receive them as {@link BitValue}. </p>
 */
public class BitWriter {

    // The output stream to write bits to
    private final OutputStream outputStream;

    // The buffer that holds the bits to write to the output stream
    private BitList buffer = new BitList();

    /**
     * Constructs a new {@link BitWriter} that writes bits to the given {@link OutputStream}.
     * 
     * @param outputStream the output stream to write bits to
     */
    public BitWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * Writes a bit to the output stream.
     * 
     * <p> Writing doesn't happen immediately, instead it is buffered.
     *     Once eight bits are buffered, they are written to the underlying {@link java.io.OutputStream} as a byte. 
     *     If eight bits are never reached, then nothing get written (you might want to call {@code addPadding} to 
     *     add some trailing zeros) </p>
     * 
     * <p> If such underlying implementation it's also buffered itself, you might need to call the {@code flush} 
     *     method to see changes. </p>
     * 
     * @see #flush() 
     * @see #addPadding()
     * 
     * @param bitValue the bit to write
     * @throws IOException if an I/O error occurs during the write operation to the underlying {@link OutputStream}
     */
    public void write(BitValue bitValue) throws IOException {
        assert bitValue != null;
        partialFlush();
        buffer.add(bitValue);
    }

    /**
     * Writes currently buffered bits to the output stream if there are 8 of them.
     * 
     * <p> If the underlying {@link OutputStream} implementation it's buffered, you might need to 
     *     call the {@code flush} method to see changes.  </p>
     * 
     * @see #flush() 
     * 
     * @throws IOException if an I/O error occurs during the write operation to the underlying {@link OutputStream}
     */
    private void partialFlush() throws IOException {
        if (buffer.size() == 8) {
            outputStream.write(BitListConversions.asByte(buffer));
            buffer = new BitList();
        }
    }

    /**
     * Ensure that all the bytes buffered so far get written to the output underlying {@link OutputStream}.
     * 
     * <p> Internally calls {@code flush} on the underlying {@link OutputStream}. </p> 
     * 
     * <p> Keep in mind that bits are actually written in batches of 8 bits at a time, 
     *     hence you might want to call {@code addPadding} to add some trailing zeros </p>
     * 
     * @see #addPadding()
     * 
     * @throws IOException if an I/O error occurs during the flush operation to the underlying {@link OutputStream}
     */
    public void flush() throws IOException {
        partialFlush();
        outputStream.flush();
    }

    /**
     * Add multiple occurrencies of {@code BitValue.ZERO} to complete the current byte as long as it is not empty.
     * 
     * <p> If the currently buffered byte contains no bits, it does nothing, otherwise it adds trailing zeros to it so that
     *     calling the {@code flush} method actually ensure that every bit gets written without loss of information </p>
     * 
     * @see #flush()
     */
    public void addPadding() {
        if (!buffer.isEmpty()) {
            while (buffer.size() < 8) {
                buffer.add(BitValue.ZERO);
            }
        }
    }
}
