package io.github.fdero.bits4j.core;

import java.io.IOException;
import java.io.OutputStream;

public class BitOutputStream {

    private final OutputStream outputStream;
    private BitList buffer = new BitList();

    public BitOutputStream(OutputStream inputStream) {
        this.outputStream = inputStream;
    }

    public void write(BitValue bitValue) throws IOException {
        assert bitValue != null;
        partialFlush();
        buffer.add(bitValue);
    }

    private void partialFlush() throws IOException {
        if (buffer.size() == 8) {
            outputStream.write(BitListConversions.asByte(buffer));
            buffer = new BitList();
        }
    }

    public void flush() throws IOException {
        partialFlush();
        outputStream.flush();
    }

    public void roundUp() throws IOException {
        if (!buffer.isEmpty()) {
            while (buffer.size() < 8) {
                buffer.add(BitValue.ZERO);
            }
        }
    }
}
