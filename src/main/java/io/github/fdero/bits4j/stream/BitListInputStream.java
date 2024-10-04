package io.github.fdero.bits4j.stream;

import java.io.InputStream;
import java.util.List;

import io.github.fdero.bits4j.core.BitList;
import io.github.fdero.bits4j.core.BitListConversions;
import io.github.fdero.bits4j.core.BitValue;

public class BitListInputStream extends InputStream {
        
    private final List<BitValue> bitList;
    private int byteCursor = 0;

    public BitListInputStream(List<BitValue> bitList) {
        this.bitList = bitList;
    }

    @Override
    public int read() {
        if (byteCursor + 8 >= bitList.size()) {
            return -1;
        }
        BitList byteBits = new BitList(); 
        byteBits.addAll(bitList.subList(byteCursor, byteCursor + 8));
        byteCursor += 8;
        return BitListConversions.asByte(byteBits);
    }
}
