package tests.data_structures.cache;

import data_structures.cache.FIFO.FIFOCache;

public class FIFOCacheTest {
    public static void main(String[] args) {
        testGet();
        testPut();
    }

    private static void testGet() {
        FIFOCache<Integer, String> cache = new FIFOCache<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        assert cache.get(1).equals("A");
        assert cache.get(2).equals("B");
        assert cache.get(3).equals("C");
        assert cache.get(4) == null;
    }

    private static void testPut() {
        FIFOCache<Integer, String> cache = new FIFOCache<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.put(4, "D");

        assert cache.get(1) == null;
        assert cache.get(4).equals("D");
    }
}
