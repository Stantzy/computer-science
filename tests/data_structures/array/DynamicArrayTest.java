package tests.data_structures.array;

import data_structures.array.DynamicArray;

public class DynamicArrayTest {
    public static void main(String[] args) {
        testAddAndSet();
        testInsertAt();
        testRemoveAt();
        testTrimToSize();
        testResize();
    }

    private static void testAddAndSet() {
        DynamicArray<Integer> array = new DynamicArray<>();

        boolean exceptionThrown = false;
        try {
            array.setAt(-1, 1);
        } catch (RuntimeException e) {
            exceptionThrown = true;
            assert e.getMessage() != null :
                    "testAddAndSet: Exception message must not be empty";
        }

        assert exceptionThrown :
                "testAddAndSet: expected exception to be thrown";

        fillArray(array);

        int element;

        element = array.getAt(0);
        assert element == 10 : "testAdd: expected 10, got " + element;

        array.setAt(1, 100);
        element = array.getAt(1);
        assert element == 100 : "testAdd: expected 100, got " + element;

        element = array.getAt(2);
        assert element == 30 : "testAdd: expected 30, got " + element;
    }

    private static void testInsertAt() {
        DynamicArray<Integer> array = new DynamicArray<>();

        boolean exceptionThrown = false;
        try {
            array.insertAt(-1, 1);
        } catch (RuntimeException e) {
            exceptionThrown = true;
            assert e.getMessage() != null :
                    "testInsertAt: Exception message must not be empty";
        }

        assert exceptionThrown :
                "testInsertAt: expected exception to be thrown";

        fillArray(array);

        int element;
        array.insertAt(1, 100);
        element = array.getAt(1);
        assert element == 100 : "testInsertAt: expected 100, got " + element;
        element = array.getAt(2);
        assert element == 20 : "testInsertAt: expected 20, got " + element;
    }

    private static void testRemoveAt() {
        DynamicArray<Integer> array = new DynamicArray<>();

        boolean exceptionThrown = false;
        try {
            array.removeAt(-1);
        } catch (RuntimeException e) {
            exceptionThrown = true;
            assert e.getMessage() != null :
                    "testRemoveAt: Exception message must not be empty";
        }

        assert exceptionThrown :
                "testRemoveAt: expected exception to be thrown";

        fillArray(array);

        exceptionThrown = false;
        try {
            array.removeAt(4);
        } catch (RuntimeException e) {
            exceptionThrown = true;
            assert e.getMessage() != null :
                    "testRemoveAt: Exception message must not be empty";
        }

        assert exceptionThrown :
                "testRemoveAt: expected exception to be thrown";

        int element;
        element = array.removeAt(0);
        assert element == 10 : "testInsertAt: expected 10, got " + element;

        element = array.removeAt(0);
        assert element == 20 : "testInsertAt: expected 20, got " + element;

        element = array.removeAt(0);
        assert element == 30 : "testInsertAt: expected 30, got " + element;
    }

    private static void testTrimToSize() {
        DynamicArray<Integer> array = new DynamicArray<>(10);

        fillArray(array);

        int capacity = array.getCapacity();
        assert capacity == 10 :
                "testTrimToSize: expected capacity = 10, got capacity = "
                + capacity;

        array.trimToSize();
        capacity = array.getCapacity();
        assert capacity == 3 :
                "testTrimToSize: expected capacity = 3, got capacity = "
                        + capacity;
    }

    private static void testResize() {
        DynamicArray<Integer> array = new DynamicArray<>(2);

        int capacity = array.getCapacity();
        assert capacity == 2 :
                "testResize: expected capacity = 2, got capacity = "
                        + capacity;

        fillArray(array);

        capacity = array.getCapacity();
        assert capacity == 4 :
                "testResize: expected capacity = 4, got capacity = "
                        + capacity;
    }

    private static void fillArray(DynamicArray<Integer> arr) {
        arr.add(10);
        arr.add(20);
        arr.add(30);
    }
}
