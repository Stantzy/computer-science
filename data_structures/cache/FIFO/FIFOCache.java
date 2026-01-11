package data_structures.cache.FIFO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class FIFOCache<K ,V> {
    private static final int DEFAULT_CAPACITY = 16;

    private final int capacity;
    private final HashMap<K, V> map;
    private final List<K> queue;
    private int size;

    public FIFOCache() { this(DEFAULT_CAPACITY); }

    public FIFOCache(int capacity) {
        if(capacity < 1)
            capacity = DEFAULT_CAPACITY;

        this.capacity = capacity;
        map = new HashMap<>();
        queue = new LinkedList<>();
        size = 0;
    }

    public V get(K key) {
        return map.get(key);
    }

    public void put(K key, V value) {
        V val = map.get(key);
        if(val != null) {
            map.put(key, value);
            return;
        }

        if(size >= capacity) {
            K keyToRemove = queue.removeFirst();
            map.remove(keyToRemove);
            size--;
        }

        map.put(key, value);
        queue.addLast(key);
        size++;
    }
}
