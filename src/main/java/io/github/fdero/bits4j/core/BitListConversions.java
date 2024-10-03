package io.github.fdero.bits4j.core;

import java.util.BitSet;

public class BitListConversions {

    public static BitList fromByte(byte bits) {
        BitSet bitSet = BitSet.valueOf(new long[] { bits });
        BitField bitField = new BitField(bitSet);
        return new BitList(bitField, 8);
    }

    public static BitList fromInt(int bits) {
        BitSet bitSet = BitSet.valueOf(new long[] { bits });
        BitField bitField = new BitField(bitSet);
        return new BitList(bitField, 32);
    }

    public static BitList fromLong(long bits) {
        BitSet bitSet = BitSet.valueOf(new long[] { bits });
        BitField bitField = new BitField(bitSet);
        return new BitList(bitField, 64);
    }

    public static BitList fromString(String stringEncodedBits) {
        BitList bitList = new BitList();
        for (char c : stringEncodedBits.toCharArray()) {
            switch (c) {
                case '0' -> bitList.addZero();
                case '1' -> bitList.addOne();
                default -> throw new IllegalArgumentException();
            }
        }
        return bitList;
    }

    public static byte asByte(BitList bitList) {
        assert bitList.size() == 8;
        return (byte) bitList.getBitField().getBitSet().toLongArray()[0];
    }

    public static int asInt(BitList bitList) {
        assert bitList.size() == 32;
        return (int) bitList.getBitField().getBitSet().toLongArray()[0];
    }

    public static long asLong(BitList bitList) {
        assert bitList.size() == 64;
        return (long) bitList.getBitField().getBitSet().toLongArray()[0];
    }

    public static String asString(BitList bitList) {
        StringBuilder sb = new StringBuilder();
        for (BitValue bitValue : bitList) {
            switch (bitValue) {
                case ZERO -> sb.append("0");
                case ONE -> sb.append("1");
                case null -> throw new IllegalStateException();
            }
        }
        return sb.toString();
    }
}
