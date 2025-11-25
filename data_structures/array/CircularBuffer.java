package data_structures.array;

public class CircularBuffer<T> {
    private static final int DEFAULT_CAPACITY = 64;

    private final Object[] buffer;
    private final int capacity;
    private int size;
    private int head;
    private int tail;

    public CircularBuffer() {
        this(DEFAULT_CAPACITY);
    }

    public CircularBuffer(int capacity) {
        buffer = new Object[capacity];
        this.capacity = capacity;
        size = 0;
        head = 0;
        tail = 0;
    }

    public void add(T element) {
        if(isFull())
            throw new RuntimeException("CircularBuffer is full");
        buffer[tail] = element;
        tail = (tail + 1) % capacity;
        size = (size == capacity) ? size : size + 1;
    }

    @SuppressWarnings("unchecked")
    public T remove() {
        if(isEmpty())
            throw new RuntimeException("CircularBuffer is empty");

        T element = (T) buffer[head];
        buffer[head] = null;
        head = (head + 1) % capacity;
        size--;

        return element;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if(isEmpty())
            throw new RuntimeException("CircularBuffer is empty");
        return (T) buffer[head];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size >= capacity;
    }

    public void clear() {
        for(int i = 0; i < capacity; i++)
            buffer[i] = null;
        size = 0;
        head = 0;
        tail = 0;
    }

    public int capacity() {
        return capacity;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray(T[] arr) {
        for(int i = 0; i < size && i < arr.length; i++)
            arr[i] = (T) buffer[(head + i) % capacity];

        return arr;
    }
}
