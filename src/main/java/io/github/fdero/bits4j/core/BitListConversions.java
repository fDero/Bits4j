package io.github.fdero.bits4j.core;

import java.util.BitSet;

/**
 * A class that provides static methods to convert between {@link io.github.fdero.bits4j.core.BitList} and other types.
 * 
 * @see io.github.fdero.bits4j.core.BitList
 */
public final class BitListConversions {

    /**
     * This class should not be instantiated. It only provides static methods.
     */
    private BitListConversions() {
        throw new AssertionError("This class should not be instantiated");
    }

    /**
     * Converts a {@code byte} to a {@link BitList}.
     * 
     * <p> The resulting {@link BitList} will be of size eight </p>
     * 
     * <p> It will be possible to convert the output {@link BitList} back to the
     *     original {@code byte} using the {@code asByte} method </p>
     * 
     * @see #asByte(BitList)
     * 
     * @param bits the byte to convert
     * @return the {@link BitList} represented by the {@code byte}
     */
    public static BitList fromByte(byte bits) {
        BitSet bitSet = BitSet.valueOf(new long[] { bits });
        BitField bitField = new BitField(bitSet);
        return new BitList(bitField, 8);
    }

    /**
     * Converts an {@code int} to a {@link BitList}.
     * 
     * <p> The resulting {@link BitList} will be of size thirty-two </p>
     * 
     * <p> It will be possible to convert the output {@link BitList} back to the
     *     original {@code int} using the {@code asInt} method </p>
     * 
     * @see #asInt(BitList)
     * 
     * @param bits the int to convert
     * @return the {@link BitList} represented by the {@code int}
     */
    public static BitList fromInt(int bits) {
        BitSet bitSet = BitSet.valueOf(new long[] { bits });
        BitField bitField = new BitField(bitSet);
        return new BitList(bitField, 32);
    }

    /**
     * Converts a {@code long} to a {@link BitList}.
     * 
     * <p> The resulting {@link BitList} will be of size sixty-four </p>
     * 
     * <p> It will be possible to convert the output {@link BitList} back to the
     *     original {@code long} using the {@code asLong} method </p>
     * 
     * @see #asLong(BitList)
     * 
     * @param bits the long to convert
     * @return the {@link BitList} represented by the {@code long} 
     */
    public static BitList fromLong(long bits) {
        BitSet bitSet = BitSet.valueOf(new long[] { bits });
        BitField bitField = new BitField(bitSet);
        return new BitList(bitField, 64);
    }

    /**
     * Converts a binary string to a {@link BitList}.
     * 
     * <p> The string must be composed of '0' and '1' characters only </p>
     * 
     * <p> The resulting {@link BitList} will be of the same size of the input string.
     *     It will be possible to convert the output {@link BitList} back to the
     *     original {@code String} using the {@code asBinaryString} method </p>
     * 
     * @see #asBinaryString(BitList)
     * 
     * @param stringEncodedBits the string to convert
     * @return the {@link BitList} represented by the {@code String}
     */
    public static BitList fromBinaryString(String stringEncodedBits) {
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

    /**
     * Converts a {@link BitList} to a value of type {@code byte}.
     * 
     * <p> The {@link BitList} must be of size eight </p>
     * 
     * <p> It will be possible to convert resulting {@code byte} back to a {@link BitList}
     *     using the {@code fromByte} method </p>
     * 
     * @see #fromByte(byte)
     * 
     * @param bitList the {@link BitList} to convert
     * @return the byte represented by the list
     */
    public static byte asByte(BitList bitList) {
        assert bitList.size() == 8;
        return (byte) bitList.getBitField().getBitSet().toLongArray()[0];
    }

    /**
     * Converts a {@link BitList} to a value of type {@code int}.
     * 
     * <p> The {@link BitList} must be of size thirty-two </p>
     * 
     * <p> It will be possible to convert resulting {@code int} back to a {@link BitList}
     *     using the {@code fromInt} method </p>
     * 
     * @see #fromInt(int)
     * 
     * @param bitList the {@link BitList} to convert
     * @return the int represented by the list
     */
    public static int asInt(BitList bitList) {
        assert bitList.size() == 32;
        return (int) bitList.getBitField().getBitSet().toLongArray()[0];
    }

    /**
     * Converts a {@link io.github.fdero.bits4j.core.BitList} to a value of type {@code long}.
     * 
     * <p> The {@link io.github.fdero.bits4j.core.BitList} must be of size sixty-four </p>
     * 
     * <p> It will be possible to convert resulting {@code long} back to a {@link io.github.fdero.bits4j.core.BitList}
     *     using the {@code fromLong} method </p>
     * 
     * @see #fromLong(long)
     * 
     * @param bitList the {@link io.github.fdero.bits4j.core.BitList} to convert
     * @return the long represented by the list
     */
    public static long asLong(BitList bitList) {
        assert bitList.size() == 64;
        return (long) bitList.getBitField().getBitSet().toLongArray()[0];
    }

    /**
     * Converts a {@link io.github.fdero.bits4j.core.BitList} to a value of type {@code String}.
     * 
     * <p> The resulting string will be composed of '0' and '1' characters only and will be
     *     of the same size of the {@link io.github.fdero.bits4j.core.BitList} </p>
     * 
     * <p> The output string will be formatted in such a way that it will be possible
     *     to convert it back to a {@link io.github.fdero.bits4j.core.BitList} using the 
     *     {@code fromBinaryString} method </p>
     * 
     * @see #fromBinaryString(String)
     * 
     * @param bitList the {@link io.github.fdero.bits4j.core.BitList} to convert
     * @return the binary-string-representation by the list
     */
    public static String asBinaryString(BitList bitList) {
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