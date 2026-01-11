package data_structures.cache.LIFO;

import java.util.HashMap;

public class LIFOCache<K, V> {
    private static final int DEFAULT_CAPACITY = 16;

    private final HashMap<K, V> map;
    private Node<K> top;
    private final int capacity;
    private int size;

    private static class Node<K> {
        K key;
        Node<K> next;

        Node(K key) {
            this.key = key;
            next = null;
        }
    }

    public LIFOCache() {
        this(DEFAULT_CAPACITY);
    }

    public LIFOCache(int capacity) {
        if(capacity < 1)
            capacity = DEFAULT_CAPACITY;

        map = new HashMap<>();
        top = null;
        this.capacity = capacity;
        size = 0;
    }

    public V get(K key) {
        return map.get(key);
    }

    public void put(K key, V value) {
        if(map.containsKey(key)) {
            map.put(key, value);
            return;
        }

        if(size >= capacity) {
            map.remove(top.key);
            removeFromTop();
            size--;
        }

        Node<K> node = new Node<>(key);
        pushOnTop(node);
        map.put(key, value);
        size++;
    }

    private void pushOnTop(Node<K> node) {
        node.next = top;
        top = node;
    }

    private void removeFromTop() {
        if(top == null)
            return;

        top = top.next;
    }
}
