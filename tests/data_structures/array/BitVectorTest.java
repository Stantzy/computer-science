package tests.data_structures.array;

import data_structures.array.BitVector;

import java.util.Arrays;

public class BitVectorTest {
    public static void main(String[] args) {
        testSetAndGetBit();
        testClear();
        testToggle();
        testReset();
        testFill();
        testCopy();
        testCountOnes();
        testCountZeros();
        testShiftLeft();
        testShiftRight();
    }

    private static void testSetAndGetBit() {
        final int SIZE = 1;

        BitVector bitVector = new BitVector(SIZE);
        byte[] expected = new byte[SIZE];

        expected[0] = 1;
        bitVector.set(0);
        assert Arrays.equals(expected, bitVector.copy()) :
                "testSetAndGetBit: expected true";

        expected[0] = 0b0000_0011;
        bitVector.set(1);
        assert Arrays.equals(expected, bitVector.copy()) :
                "testSetAndGetBit: expected true";

        expected[0] = 0b0000_0111;
        bitVector.set(2);
        assert Arrays.equals(expected, bitVector.copy()) :
                "testSetAndGetBit: expected true";

        expected[0] = 0b0100_0111;
        bitVector.set(6);
        assert Arrays.equals(expected, bitVector.copy()) :
                "testSetAndGetBit: expected true";

        expected[0] = (byte) 0b1100_0111;
        bitVector.set(7);
        assert Arrays.equals(expected, bitVector.copy()) :
                "testSetBit: expected true";

        for(int i = 0; i < Byte.SIZE; i++) {
            if(i < 3 || i > 5) {
                assert bitVector.get(i) : "testSetAndGetBit: expected true";
            } else {
                assert !bitVector.get(i) : "testSetAndGetBit: expected false";
            }
        }
    }

    private static void testClear() {
        final int SIZE = 32;
        BitVector bitVector = new BitVector(SIZE);

        for(int i = 0; i < SIZE; i++) {
            bitVector.set(i);
            bitVector.clear(i);
        }

        assert Arrays.equals(new byte[SIZE], bitVector.copy()) :
                "testClear: expected true";
    }

    private static void testToggle() {
        final int SIZE = 32;

        BitVector bitVector = new BitVector(SIZE);
        byte[] expected = new byte[SIZE];

        Arrays.fill(expected, (byte) 0xFF);
        for(int i = 0; i < SIZE * Byte.SIZE; i++) {
            bitVector.toggle(i);
        }
        assert Arrays.equals(expected, bitVector.copy()) :
                "testToggle: expected true";

        Arrays.fill(expected, (byte) 0);
        for (int i = 0; i < SIZE * Byte.SIZE; i++)
            bitVector.toggle(i);
        assert Arrays.equals(expected, bitVector.copy()) :
                "testToggle: expected true";
    }

    private static void testReset() {
        final int SIZE = 32;

        BitVector bitVector = new BitVector(SIZE);
        byte[] expected = new byte[SIZE];

        for(int i = 0; i < SIZE * Byte.SIZE; i++) {
            bitVector.toggle(i);
        }

        bitVector.reset();
        assert Arrays.equals(new byte[SIZE], bitVector.copy()) :
                "testReset: expected true";
    }

    private static void testFill() {
        final int SIZE = 32;

        BitVector bitVector = new BitVector(SIZE);
        byte[] expected = new byte[SIZE];

        Arrays.fill(expected, (byte) 0xFF);
        bitVector.fill();

        assert Arrays.equals(expected, bitVector.copy()) :
                "testFill: expected true";
    }

    private static void testCopy() {
        final int SIZE = 32;

        BitVector bitVector = new BitVector(SIZE);
        byte[] expected = new byte[SIZE];
        byte[] result;

        Arrays.fill(expected, (byte) 0xFF);
        bitVector.fill();

        result = bitVector.copy();

        assert Arrays.equals(expected, result) : "testCopy: expected true";
    }

    private static void testCountOnes() {
        final int SIZE = 32;

        BitVector bitVector = new BitVector(SIZE);
        byte[] expected = new byte[SIZE];
        int result;

        bitVector.fill();

        result = bitVector.countOnes();
        assert result == SIZE * Byte.SIZE :
                "testCountOnes: expected " + SIZE * Byte.SIZE + ", got " + result;

        bitVector.reset();
        result = bitVector.countOnes();
        assert result == 0 :
                "testCountOnes: expected " + 0 + ", got " + result;

        bitVector.set(SIZE / 2);
        result = bitVector.countOnes();
        assert result == 1 :
                "testCountOnes: expected " + 1 + ", got " + result;
    }

    private static void testCountZeros() {
        final int SIZE = 32;

        BitVector bitVector = new BitVector(SIZE);
        byte[] expected = new byte[SIZE];
        int result;

        bitVector.fill();

        result = bitVector.countZeros();
        assert result == 0 :
                "testCountZeros: expected " + 0 + ", got " + result;

        bitVector.reset();
        result = bitVector.countZeros();
        assert result == SIZE * Byte.SIZE :
                "testCountZeros: expected " + SIZE * Byte.SIZE + ", got " + result;

        bitVector.fill();
        bitVector.toggle(SIZE / 2);
        result = bitVector.countZeros();
        assert result == 1 :
                "testCountZeros: expected " + 1 + ", got " + result;
    }

    private static void testShiftLeft() {
        final int SIZE = 32;

        BitVector bitVector = new BitVector(SIZE);
        byte[] expected = new byte[SIZE];

        bitVector.shiftLeft();
        assert Arrays.equals(expected, bitVector.copy()) :
                "testShiftLeft: expected true";

        bitVector.set(0);
        bitVector.shiftLeft();
        expected[0] = 2;
        assert Arrays.equals(expected, bitVector.copy()) :
                "testShiftLeft: expected true";

        bitVector.reset();
        bitVector.set(0);
        for(int i = 0; i < SIZE * Byte.SIZE - 1; i++) {
            bitVector.shiftLeft();
        }
        expected[0] = 0;
        expected[SIZE - 1] = (byte) 0x80;
        assert Arrays.equals(expected, bitVector.copy()) :
                "testShiftLeft: expected true";
    }

    private static void testShiftRight() {
        final int SIZE = 32;

        BitVector bitVector = new BitVector(SIZE);
        byte[] expected = new byte[SIZE];

        bitVector.shiftLeft();
        assert Arrays.equals(expected, bitVector.copy()) :
                "testShiftRight: expected true";

        bitVector.set(0);
        bitVector.shiftRight();
        assert Arrays.equals(expected, bitVector.copy()) :
                "testShiftRight: expected true";

        bitVector.reset();
        bitVector.set(SIZE * Byte.SIZE - 1);
        expected[0] = 1;
        for(int i = 0; i < SIZE * Byte.SIZE - 1; i++) {
            bitVector.shiftRight();
        }
        assert Arrays.equals(expected, bitVector.copy()) :
                "testShiftRight: expected true";
    }
}
