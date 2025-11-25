package data_structures.array;

public class DynamicArray<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private Object[] array;
    private int capacity;
    private int size;

    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArray(int capacity) {
        array = new Object[capacity];
        this.capacity = capacity;
        size = 0;
    }

    public void add(T element) {
        if(isFull())
            resize();

        array[size] = element;
        size++;
    }

    public void insertAt(int index, T element) {
        checkIndex(index);
        size++;

        if(isFull())
            resize();

        for(int i = size; i > index; i--)
            array[i] = array[i - 1];

        array[index] = element;

    }

    public void setAt(int index, T element) {
        checkIndex(index);
        array[index] = element;
    }

    @SuppressWarnings("unchecked")
    public T getAt(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    @SuppressWarnings("unchecked")
    public T removeAt(int index) {
        checkIndex(index);

        if(index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index " + index + " out of bounds for size " + size
            );
        }

        T element = (T) array[index];
        size--;

        for(int i = index; i < size; i++) {
            array[i] = array[i + 1];
            array[i + 1] = null;
        }

        return element;
    }

    public int indexOf(T element) {
        for(int i = 0; i < size; i++) {
            if(array[i].equals(element))
                return i;
        }
        return -1;
    }

    public boolean contains(T element) {
        if(element == null)
            throw new IllegalArgumentException("Argument can't be null");
        return indexOf(element) != -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for(int i = 0; i < size; i++)
            array[i] = null;
        size = 0;
    }

    public void trimToSize() {
        int trimmedCapacity = size;
        Object[] trimmedArray = new Object[trimmedCapacity];

        for(int i = 0; i < size; i++)
            trimmedArray[i] = array[i];

        array = trimmedArray;
        capacity = trimmedCapacity;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    private void resize() {
        int newCapacity = capacity * 2;
        Object[] newArray = new Object[newCapacity];

        for(int i = 0; i < capacity; i++)
            newArray[i] = array[i];

        array = newArray;
        capacity = newCapacity;
    }

    private void checkIndex(int index) {
        if(index < 0 || index >= capacity) {
            throw new IndexOutOfBoundsException(
                    "Index " + index + " out of bounds for capacity " + capacity
            );
        }
    }

    private boolean isFull() {
        return size >= capacity;
    }
}
