package tests.data_structures.cache;


import data_structures.cache.MRU.MRUCache;

public class MRUCacheTest {
    public static void main(String[] args) {
        testGet();
        testPut();
    }

    private static void testGet() {
        MRUCache<Integer, String> cache = new MRUCache<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        assert cache.get(1).equals("A");
        assert cache.get(2).equals("B");
        assert cache.get(3).equals("C");
        assert cache.get(4) == null;
    }

    private static void testPut() {
        MRUCache<Integer, String> cache = new MRUCache<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        cache.get(1);

        cache.put(4, "D");

        assert cache.get(1) == null;
        assert cache.get(4).equals("D");
    }
}
