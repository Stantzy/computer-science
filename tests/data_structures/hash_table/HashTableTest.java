package tests.data_structures.hash_table;

import data_structures.hash_table.HashTable;

public class HashTableTest {
    public static void main(String[] args) {
        testPut();
        testGet();
        testRemove();
        testRehash();
        testContainsValue();
        testClear();
    }

    private static void testPut() {
        class BadKey {
            int v;
            BadKey(int v) { this.v = v; }
            @Override
            public int hashCode() { return 1; }
            @Override
            public boolean equals(Object o) {
                return o instanceof BadKey bk && (bk.v == v);
            }
        }

        HashTable<BadKey, Integer> hashTable = new HashTable<>(1, 0.75f);

        hashTable.put(new BadKey(1), 10);
        hashTable.put(new BadKey(2), 20);
        hashTable.put(new BadKey(3), 30);

        assert hashTable.size() == 3 : "testPut: expected size = 3";
        assert hashTable.get(new BadKey(1)).equals(10) : "testPut: expected 10";
        assert hashTable.get(new BadKey(2)).equals(20) : "testPut: expected 20";
        assert hashTable.get(new BadKey(3)).equals(30) : "testPut: expected 30";

        HashTable<Integer, Integer> hashTable2 = new HashTable<>(1, 0.75f);

        hashTable2.put(100, 100);
        hashTable2.put(200, 200);
        hashTable2.put(300, 300);

        assert hashTable2.size() == 3 : "testPut: expected size = 3";
        assert hashTable2.get(100) == 100 : "testPut: expected 100";
        assert hashTable2.get(200) == 200 : "testPut: expected 200";
        assert hashTable2.get(300) == 300 : "testPut: expected 300";
    }

    private static void testGet() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        Integer number;

        hashTable.put("A", 10);
        hashTable.put("B", 20);
        hashTable.put("C", 30);

        boolean exceptionThrown = false;
        try {
            number = hashTable.get(null);
        } catch(IllegalArgumentException e) {
            exceptionThrown = true;
        }

        assert exceptionThrown : "testGet: exception expected";

        number = hashTable.get("A");
        assert number == 10 : "testGet: expected 10, got " + number;
        number = hashTable.get("B");
        assert number == 20 : "testGet: expected 20, got " + number;
        number = hashTable.get("C");
        assert number == 30 : "testGet: expected 30, got " + number;
    }

    private static void testRemove() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        boolean hasRemoved = false;
        boolean exceptionThrown = false;

        hashTable.put("A", 10);
        hashTable.put("B", 20);
        hashTable.put("C", 30);

        try {
            hasRemoved = hashTable.remove(null);
        } catch(IllegalArgumentException e) {
            exceptionThrown = true;
        }

        assert exceptionThrown : "testGet: exception expected";

        hasRemoved = hashTable.remove("A");
        assert hasRemoved : "testRemove: bucket hasn't removed";
        assert hashTable.size() == 2 : "testRemove: expected size = 2";

        hasRemoved = hashTable.remove("B");
        assert hasRemoved : "testRemove: bucket hasn't removed";
        assert hashTable.size() == 1 : "testRemove: expected size = 1";

        hasRemoved = hashTable.remove("C");
        assert hasRemoved : "testRemove: bucket hasn't removed";
        assert hashTable.size() == 0 : "testRemove: expected size = 0";
    }

    private static void testRehash() {
        HashTable<String, Integer> hashTable = new HashTable<>(4, 0.75f);

        hashTable.put("A", 10);
        hashTable.put("B", 20);

        assert hashTable.get("A") == 10 : "testRehash: expected 10";
        assert hashTable.get("B") == 20 : "testRehash: expected 10";

        hashTable.put("C", 30);
        hashTable.put("D", 40);

        assert hashTable.get("A") == 10 : "testRehash: expected 10";
        assert hashTable.get("B") == 20 : "testRehash: expected 10";
    }

    private static void testContainsValue() {
        HashTable<String, Integer> hashTable = new HashTable<>(4, 0.75f);

        hashTable.put("A", 10);
        hashTable.put("B", 20);

        assert hashTable.containsValue(10) : "testContainsValue: expected true";
        assert hashTable.containsValue(20) : "testContainsValue: expected true";
        assert !hashTable.containsValue(-1) : "testContainsValue: expected false";
    }

    private static void testClear() {
        HashTable<Integer, Integer> hashTable = new HashTable<>(4, 0.75f);

        for (int i = 0; i < 100000; i++) {
            hashTable.put(i, i * i);
        }

        for (int i = 0; i < 100000; i++) {
            hashTable.put(i, i * i - i);
        }

        for (int i = 0; i < 100000; i++) {
            hashTable.put(i, i * i + i);
            assert hashTable.get(i) != null :
                    "testBigData: key must not be null";
        }

        assert hashTable.size() == 300000 :
                "testBigData: expected size = 300000";

        hashTable.clear();
        assert hashTable.size() == 0 : "testBigData: expected size = 0";
    }
}
