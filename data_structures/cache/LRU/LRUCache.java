package data_structures.cache.LRU;

import java.util.HashMap;

// Least Recently Used Cache
public class LRUCache<K, V> {
    private static final int DEFAULT_CAPACITY = 16;

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        Node<K, V> prev;

        Node(K key, V value, Node<K, V> next, Node<K, V> prev) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    private final HashMap<K, Node<K, V>> map;
    private Node<K, V> head;
    private Node<K, V> tail;
    private final int capacity;
    private int size;

    public LRUCache() {
        this(DEFAULT_CAPACITY);
    }

    public LRUCache(int capacity) {
        map = new HashMap<>();
        size = 0;
        this.capacity = capacity;
    }

    public V get(K key) {
        Node<K, V> node = map.get(key);

        removeNode(node);

        if(node != null)
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
            K keyToRemove = tail.key;
            removeTail();
            map.remove(keyToRemove);
            size--;
        }

        node = new Node<>(key, value, null, null);
        setHead(node);
        map.put(key, node);
        size++;
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

        node.prev = null;
        node.next = null;
    }

    private void setHead(Node<K, V> newHead) {
        if(head != null)
            head.next = newHead;

        newHead.prev = head;
        newHead.next = null;
        head = newHead;

        if(tail == null)
            tail = head;
    }

    private void removeTail() {
        if(tail == null)
            return;

        tail = tail.next;
        if(tail != null)
            tail.prev = null;
        else
            head = null;
    }
}
