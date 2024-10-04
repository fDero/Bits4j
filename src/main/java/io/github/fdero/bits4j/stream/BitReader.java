package io.github.fdero.bits4j.stream;

import java.io.IOException;
import java.io.InputStream;

import io.github.fdero.bits4j.core.BitList;
import io.github.fdero.bits4j.core.BitListConversions;
import io.github.fdero.bits4j.core.BitValue;

public class BitReader {

    private final InputStream inputStream;
    private BitList buffer = null;
    private int bufferIndex = 0;

    public BitReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

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
