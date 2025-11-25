package tests.data_structures.array;

import data_structures.array.CircularBuffer;

public class CircularBufferTest {
    public static void main(String[] args) {
        testAddAndRemove();
        testPeek();
        testToArray();
    }

    private static void testAddAndRemove() {
        CircularBuffer<Integer> buffer = new CircularBuffer<>(3);

        buffer.add(10);
        buffer.add(20);
        buffer.add(30);

        boolean exceptionThrown = false;

        try {
            buffer.add(-1);
        } catch (RuntimeException e) {
            exceptionThrown = true;
        }

        assert exceptionThrown :
                "testAddAndRemove: expected exception on add() to full buffer";

        int element;
        element = buffer.remove();
        assert element == 10 : "testAddAndRemove: expected 10, got " + element;
        element = buffer.remove();
        assert element == 20 : "testAddAndRemove: expected 20, got " + element;
        element = buffer.remove();
        assert element == 30 : "testAddAndRemove: expected 20, got " + element;

        exceptionThrown = false;
        try {
            buffer.remove();
        } catch (RuntimeException e) {
            exceptionThrown = true;
        }

        assert exceptionThrown : "testAddAndRemove: buffer must be empty";
    }

    private static void testPeek() {
        CircularBuffer<Integer> buffer = new CircularBuffer<>(3);

        boolean exceptionThrown = false;

        try {
            buffer.peek();
        } catch (RuntimeException e) {
            exceptionThrown = true;
        }

        assert exceptionThrown :
                "testPeek: expected exception on peek() to empty buffer";

        int element;
        buffer.add(10);
        element = buffer.peek();
        assert element == 10 : "testPeek: expected 10, got " + element;

        buffer.add(20);
        buffer.remove();
        element = buffer.peek();
        assert element == 20 : "testPeek: expected 10, got " + element;

        buffer.add(30);
        element = buffer.peek();
        assert element == 20 : "testPeek: expected 10, got " + element;
    }

    private static void testToArray() {
        final int CAPACITY = 10;
        CircularBuffer<Integer> buffer = new CircularBuffer<>(CAPACITY);
        Integer[] arr;

        for(int i = 0; i < CAPACITY; i++)
            buffer.add(i);

        buffer.remove();

        arr = buffer.toArray(new Integer[buffer.size()]);

        for(int i = 0; i < arr.length; i++) {
            int a = arr[i];
            int b = buffer.remove();
            assert a == b : "testToArray: a = " + a + " , b = " + b;
        }
    }
}
