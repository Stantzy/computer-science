package tests.data_structures.hash_table;

import data_structures.hash_table.DirectAddressTable;

public class DirectAddressTableTest {
    public static void main(String[] args) {
        testGet();
        testPut();
        testRemove();
        testSizeAndIsEmpty();
    }

    private static void testPut() {
        DirectAddressTable<Integer> table = new DirectAddressTable<>();

        table.put(0, 10);
        table.put(1, 20);
        table.put(2, 30);

        boolean exceptionThrown = false;
        try {
            table.put(-1, 1);
        } catch (RuntimeException e) {
            exceptionThrown = true;
            assert e.getMessage() != null : "Exception message must not be null";
        }

        assert exceptionThrown : "testPut: exception expected";

        exceptionThrown = false;
        try {
            table.put(3, null);
        } catch (RuntimeException e) {
            exceptionThrown = true;
            assert e.getMessage() != null : "Exception message must not be null";
        }

        assert exceptionThrown : "testPut: exception expected";

        exceptionThrown = false;
        try {
            table.put(1_000_000, null);
        } catch (RuntimeException e) {
            exceptionThrown = true;
            assert e.getMessage() != null :
                    "testPut: exception message must not be null";
        }

        assert exceptionThrown : "testPut: exception expected";
    }

    private static void testGet() {
        DirectAddressTable<Integer> table = new DirectAddressTable<>();

        table.put(0, 10);
        table.put(1, 20);
        table.put(2, 30);

        Integer value;

        value = table.get(0);
        assert value == 10 : "testGet: expected 10, got " + value;

        value = table.get(1);
        assert value == 20 : "testGet: expected 20, got " + value;

        value = table.get(2);
        assert value == 30 : "testGet: expected 30, got " + value;

        boolean exceptionThrown = false;
        try {
            table.get(-1);
        } catch (RuntimeException e) {
            exceptionThrown = true;
            assert e.getMessage() != null :
                    "testGet: Exception message must not be null";
        }

        assert exceptionThrown : "testGet: exception expected";
    }

    private static void testRemove() {
        DirectAddressTable<Integer> table = new DirectAddressTable<>();

        table.put(0, 10);
        table.put(1, 20);
        table.put(2, 30);

        Integer removedValue;

        removedValue = table.remove((Integer)10);
        assert removedValue == 10 && table.get(10) == null :
                "testRemove: expected removedValue=10, got " + removedValue;

        removedValue = table.remove((Integer)(-1));
        assert removedValue == null :
                "testRemove: expected removedValue=null, got " + removedValue;

        removedValue = table.remove((Integer)30);
        assert removedValue == 30 && table.get(30) == null :
                "testRemove: expected removedValue=30, got " + removedValue;

    }

    private static void testSizeAndIsEmpty() {
        DirectAddressTable<Integer> table = new DirectAddressTable<>();
        int value;
        boolean isEmpty;

        value = table.size();
        assert value == 0 :
                "testSizeAndIsEmpty: expected 0, got " + value;

        isEmpty = table.isEmpty();
        assert isEmpty : "testSizeAndIsEmpty: expected true, got " + isEmpty;

        table.put(0, 10);
        table.put(1, 20);
        table.put(2, 30);

        value = table.size();
        assert value == 3 :
                "testSizeAndIsEmpty: expected 3, got " + value;

        isEmpty = table.isEmpty();
        assert !isEmpty : "testSizeAndIsEmpty: expected false, got " + isEmpty;
    }
}
