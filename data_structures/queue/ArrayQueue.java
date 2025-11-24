package data_structures.queue;

public class ArrayQueue<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private Object[] buffer;
    private int size;
    private int front;
    private int rear;
    private int capacity;

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayQueue(int arraySize) {
        buffer = new Object[arraySize];
        size = 0;
        front = 0;
        rear = 0;
        capacity = buffer.length;
    }

    public void enqueue(T element) {
        if(size == capacity)
            resize();
        buffer[rear] = element;
        rear = (rear + 1) % capacity;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if(size == 0)
            throw new RuntimeException("Queue is empty");
        T element = (T) buffer[front];
        buffer[front] = null;
        size--;
        front = (front + 1) % capacity;
        return element;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if(size == 0)
            throw new RuntimeException("Queue is empty");
        return (T) buffer[front];

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
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

        buffer = newBuffer;
        front = 0;
        rear = size;
        capacity = newCapacity;
    }
}
