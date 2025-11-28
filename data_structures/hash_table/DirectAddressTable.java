package data_structures.hash_table;

public class DirectAddressTable<V> {
    private static final int DEFAULT_CAPACITY = 32;

    private final Object[] table;
    private int size;

    public DirectAddressTable() {
        this(DEFAULT_CAPACITY);
    }

    public DirectAddressTable(int capacity) {
        this.table = new Object[capacity];
        this.size = 0;
    }

    public void put(int key, V value) {
        if(value == null)
            throw new IllegalArgumentException("Value can't be null");

        checkKey(key);

        if(table[key] == null)
            size++;

        table[key] = value;
    }

    @SuppressWarnings("unchecked")
    public V get(int key) {
        checkKey(key);
        return (V) table[key];
    }

    @SuppressWarnings("unchecked")
    public V remove(int key) {
        checkKey(key);

        V value = (V) table[key];

        if(value != null) {
            table[key] = null;
            size--;
        }

        return value;
    }

    public V remove(V value) {
        if(value == null)
            return null;

        for (int i = 0; i < table.length; i++) {
            if(value.equals(table[i])) {
                table[i] = null;
                size--;
                return value;
            }
        }

        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] toArray() {
        return table.clone();
    }

    private void checkKey(int key) {
        if(key >= table.length || key < 0) {
            throw new IndexOutOfBoundsException(
                    "The key = " + key + " is out of bounds of the table" +
                    " with capacity = " + table.length
            );
        }
    }
}
