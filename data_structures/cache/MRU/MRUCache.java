package data_structures.cache.MRU;

import java.util.HashMap;

// Most Recently Used Cache
public class MRUCache<K, V> {
    private static final int DEFAULT_CAPACITY = 16;

    private final HashMap<K, Node<K, V>> map;
    private final int capacity;
    private Node<K, V> head;
    private Node<K, V> tail;
    private int size;

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        Node<K, V> prev;
        Node(K k, V v) { this.key = k; this.value = v; }
    }

    public MRUCache(int capacity) {
        if(capacity < 1)
            capacity = DEFAULT_CAPACITY;

        map = new HashMap<>();
        this.capacity = capacity;
        head = null;
        tail = null;
        size = 0;
    }

    public V get(K key) {
        Node<K, V> node = map.get(key);
        removeNode(node);
        setHead(node);
        return (node == null) ? null : node.value;
    }

    public void put(K key, V value) {
        Node<K, V> node;
        if(map.containsKey(key)) {
            node = map.get(key);
            node.value = value;
            removeNode(node);
            setHead(node);
            map.put(key, node);
            return;
        }

        if(size >= capacity) {
            map.remove(head.key);
            removeMostRecentlyUsed();
            size--;
        }

        node = new Node<>(key, value);
        setHead(node);
        map.put(key, node);
        size++;
    }

    private void setHead(Node<K, V> node) {
        if(node == null)
            return;

        if(head != null)
            head.next = node;

        node.prev = head;
        node.next = null;
        head = node;

        if(tail == null)
            tail = head;
    }

    private void removeMostRecentlyUsed() {
        removeNode(head);
    }

    private void removeNode(Node<K, V> node) {
        if(node == null)
            return;

        if(node.prev != null)
            node.prev.next = node.next;
        else
            tail = node.next;

        if(node.next != null)
            node.next.prev = node.prev;
        else
            head = node.prev;

        node.next = null;
        node.prev = null;
    }
}
