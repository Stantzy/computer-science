package data_structures.queue;

public class ArrayDeque<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private Object[] buffer;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public ArrayDeque() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayDeque(int capacity) {
        this.capacity = capacity;
        buffer = new Object[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    public void pushFront(T element) {
        if(size == capacity)
            resize();

        buffer[front] = element;
        front = (front + capacity - 1) % capacity;

        if(size == 0)
            rear = (rear + 1) % capacity;

        size++;
    }

    @SuppressWarnings("unchecked")
    public T popFront() {
        if(size == 0)
            throw new RuntimeException("Deque is empty");

        front = (front + 1) % capacity;
        T element = (T) buffer[front];
        size--;

        return element;
    }

    @SuppressWarnings("unchecked")
    public T peekFront() {
        if(size == 0)
            throw new RuntimeException("Deque is empty");
        return (T) buffer[(front + 1) % capacity];
    }

    public void pushBack(T element) {
        if(size == capacity)
            resize();

        buffer[rear] = element;
        rear = (rear + 1) % capacity;

        if(size == 0)
            front = (front + capacity - 1) % capacity;

        size++;
    }

    @SuppressWarnings("unchecked")
    public T popBack() {
        if(size == 0)
            throw new RuntimeException("Deque is empty");

        rear = (rear + capacity - 1) % capacity;
        T element = (T) buffer[rear];
        size--;

        return element;
    }

    @SuppressWarnings("unchecked")
    public T peekBack() {
        if(size == 0)
            throw new RuntimeException("Deque is empty");
        return (T) buffer[(rear + capacity - 1) % capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        for(int i = 0; i < capacity; i++)
            buffer[i] = null;

        size = 0;
        front = 0;
        rear = 0;
    }

    private void resize() {
        int newCapacity = capacity * 2;
        Object[] newBuffer = new Object[newCapacity];

        for(int i = 0; i < size; i++)
            newBuffer[i] = buffer[(front + i) % capacity];

        front = capacity - 1;
        rear = size;
        capacity = newCapacity;
        buffer = newBuffer;
    }
}
