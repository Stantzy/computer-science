package data_structures.array;

import java.util.Arrays;

public class BitVector {
    private static final int DEFAULT_SIZE = 32;

    private final byte[] vector;

    public BitVector() {
        this(DEFAULT_SIZE);
    }

    public BitVector(int size) {
        vector = new byte[size];
    }

    private record BitAddress(int bytePosition, byte bitOffset) { }

    private BitAddress getBitAddress(int i) {
        int bytePosition = i / Byte.SIZE;

        if(i < 0 || bytePosition >= vector.length)
            return null;

        byte bitOffset = (byte) (i % Byte.SIZE);

        return new BitAddress(bytePosition, bitOffset);
    }

    public void set(int i) {
        BitAddress bitAddress = getBitAddress(i);
        if(bitAddress == null)
            return;

        byte mask = (byte) (1 << bitAddress.bitOffset());

        vector[bitAddress.bytePosition()] |= mask;
    }

    public void clear(int i) {
        BitAddress bitAddress = getBitAddress(i);
        if(bitAddress == null)
            return;

        byte mask = (byte) ~(1 << bitAddress.bitOffset());

        vector[bitAddress.bytePosition()] &= mask;
    }

    public void toggle(int i) {
        BitAddress bitAddress = getBitAddress(i);
        if(bitAddress == null)
            return;

        byte mask = (byte) (1 << bitAddress.bitOffset());

        vector[bitAddress.bytePosition()] ^= mask;
    }

    public boolean get(int i) {
        BitAddress bitAddress = getBitAddress(i);
        if(bitAddress == null)
            return false;

        byte mask = (byte) (1 << bitAddress.bitOffset());

        return (vector[bitAddress.bytePosition()] & mask) == mask;
    }

    public void reset() {
        Arrays.fill(vector, (byte) 0);
    }

    public void fill() {
        Arrays.fill(vector, (byte) 0xFF);
    }

    public byte[] copy() {
        return Arrays.copyOf(vector, vector.length);
    }

    public int countOnes() {
        int count = 0;

        for (byte current : vector) {
            int value = current & 0xFF;

            while(value != 0) {
                value &= (value - 1);
                count++;
            }
        }

        return count;
    }

    public int countZeros() {
        int count = 0;

        for (byte current : vector) {
            int value = current & 0xFF;
            int inverted = (~value) & 0xFF;

            while(inverted != 0) {
                inverted &= (inverted - 1);
                count++;
            }
        }

        return count;
    }

    public void shiftLeft() {
        byte carry = 0;
        for(int i = 0; i < vector.length; i++) {
            byte current = vector[i];
            byte newCarry = (byte) ((current & 0x80) >>> 7);

            vector[i] = (byte) ((current << 1) | carry);

            carry = newCarry;
        }
    }

    public void shiftRight() {
        byte carry = 0;

        for(int i = vector.length - 1; i >= 0; i--) {
            byte current = vector[i];
            byte newCarry = (byte) (current & 0x01);

            vector[i] = (byte) (((current & 0xFF) >>> 1) | (carry << 7));

            carry = newCarry;
        }
    }
}
