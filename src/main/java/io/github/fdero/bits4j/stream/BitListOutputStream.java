package io.github.fdero.bits4j.stream;

import java.io.OutputStream;
import java.util.List;

import io.github.fdero.bits4j.core.BitList;
import io.github.fdero.bits4j.core.BitListConversions;
import io.github.fdero.bits4j.core.BitValue;

public class BitListOutputStream extends OutputStream {
    
    private final List<BitValue> bitList;

    public BitListOutputStream(List<BitValue> bitList) {
        this.bitList = bitList;
    }

    @Override
    public void write(int b) {
        assert b >= 0 && b <= 255;
        BitList byteBits = BitListConversions.fromByte((byte) b);
        bitList.addAll(byteBits);
    }
}