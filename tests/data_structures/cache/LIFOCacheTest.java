package tests.data_structures.cache;

import data_structures.cache.LIFO.LIFOCache;

public class LIFOCacheTest {
    public static void main(String[] args) {
        testGet();
        testPut();
    }

    private static void testGet() {
        LIFOCache<Integer, String> cache = new LIFOCache<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        assert cache.get(1).equals("A");
        assert cache.get(2).equals("B");
        assert cache.get(3).equals("C");
        assert cache.get(4) == null;
    }

    private static void testPut() {
        LIFOCache<Integer, String> cache = new LIFOCache<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.put(4, "D");

        assert cache.get(3) == null;
        assert cache.get(4).equals("D");
    }
}
