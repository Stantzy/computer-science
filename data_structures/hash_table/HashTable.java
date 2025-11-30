package data_structures.hash_table;

import java.util.Arrays;
import java.util.LinkedList;

public class HashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 32;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private LinkedList<Bucket<K, V>>[] buckets;
    private int size;
    private int capacity;
    private final float loadFactor;
    private int threshold;

    private static class Bucket<K, V> {
        private final K key;
        private V value;

        Bucket(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashTable() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public HashTable(int startCapacity, float loadFactor) {
        this.buckets = new LinkedList[startCapacity];
        this.size = 0;
        this.capacity = startCapacity;
        this.loadFactor = loadFactor;
        this.threshold = (int)(capacity * loadFactor) + 1;
    }

    public void put(K key, V value) {
        int index = hash(key);

        Bucket<K, V> bucket = new Bucket<>(key, value);

        buckets[index] = insert(buckets[index], bucket);
        size++;

        if(size >= threshold)
            rehash();
    }

    public V get(K key) {
        int index = hash(key);
        LinkedList<Bucket<K, V>> list = buckets[index];

        if(list == null)
            return null;

        for(Bucket<K, V> bucket : list) {
            if(bucket != null && bucket.key.equals(key))
                return bucket.value;
        }

        return null;
    }

    public boolean remove(K key) {
        int index = hash(key);
        boolean result = false;
        LinkedList<Bucket<K, V>> list = buckets[index];

        if(list == null)
            return result;

        for(Bucket<K, V> bucket : list) {
            if(bucket != null && bucket.key.equals(key)) {
                result = list.remove(bucket);
                size--;
                break;
            }
        }

        if(list.isEmpty())
            buckets[index] = null;

        return result;
    }

    public void update(K key, V value) {
        int index = hash(key);
        LinkedList<Bucket<K, V>> list = buckets[index];

        if(list == null)
            return;

        for(Bucket<K, V> bucket : list) {
            if(bucket != null && bucket.key.equals(key)) {
                bucket.value = value;
                break;
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean containsValue(V value) {
        if(value == null)
            return false;

        for(LinkedList<Bucket<K, V>> bucketList : buckets) {
            if(bucketList != null) {
                for(Bucket<K, V> bucket : bucketList) {
                    if (bucket != null && bucket.value.equals(value))
                        return true;
                }
            }
        }

        return false;
    }

    public void clear() {
        Arrays.fill(buckets, null);
        size = 0;
        threshold = 0;
    }

    private int hash(K key) {
        if(key == null)
            throw new IllegalArgumentException("Key can't be null");
        int hash = key.hashCode();
        return (hash < 0 ? -hash : hash) % buckets.length;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        int newCapacity = capacity * 2;
        LinkedList<Bucket<K, V>>[] newBuckets = new LinkedList[newCapacity];

        for(LinkedList<Bucket<K, V>> oldList : buckets) {
            if(oldList == null)
                continue;

            for(Bucket<K, V> bucket : oldList) {
                if(bucket == null)
                    continue;

                int newHash = bucket.key.hashCode();
                int index =
                        (newHash < 0 ? -newHash : newHash) % newBuckets.length;

                newBuckets[index] = insert(newBuckets[index], bucket);
            }
        }

        buckets = newBuckets;
        capacity = newCapacity;
        threshold = (int) (newCapacity * loadFactor);
    }

    private LinkedList<Bucket<K,V>> insert(
            LinkedList<Bucket<K, V>> bucketList,
            Bucket<K, V> bucket
    ) {
        if(bucketList == null) {
            LinkedList<Bucket<K, V>> newBucketList = new LinkedList<>();
            newBucketList.add(bucket);
            return newBucketList;
        } else {
            bucketList.add(bucket);
            return bucketList;
        }
    }
}