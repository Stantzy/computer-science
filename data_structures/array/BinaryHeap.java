package data_structures.array;

public class BinaryHeap<T extends Comparable<T>> {
    private static final int DEFAULT_CAPACITY = 32;
    private static final int ROOT_INDEX = 0;

    private final T[] heap;
    private int size;

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public BinaryHeap(int capacity) {
        int heapCapacity = (capacity < 1) ? DEFAULT_CAPACITY : capacity;
        heap = (T[]) new Comparable[heapCapacity];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public BinaryHeap(T[] array) {
        heap = (T[]) new Comparable[array.length];
        System.arraycopy(array, 0, heap, 0, array.length);
        size = array.length;
        buildHeap();
    }

    public int capacity() {
        return heap.length;
    }

    public boolean insert(T element) {
        if(size >= heap.length)
            return false;

        heap[size] = element;
        heapifyUp(size);
        size++;

        return true;
    }

    public T extractMax() {
        if(size < 1)
            return null;

        T max = heap[ROOT_INDEX];
        heap[ROOT_INDEX] = heap[size - 1];
        size--;

        if(size > 0)
            heapifyDown(ROOT_INDEX);

        return max;
    }

    public T peek() {
        return (size == 0) ? null : heap[ROOT_INDEX];
    }

    private void buildHeap() {
        for(int i = size / 2 - 1; i >= 0; i--)
            heapifyDown(i);
    }

    private void heapifyUp(int index) {
        while(index > 0) {
            int parentIndex = getIndexOfParent(index);

            if(heap[index].compareTo(heap[parentIndex]) <= 0)
                break;

            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void heapifyDown(int index) {
        while(true) {
            int leftIndex = getIndexOfLeftChild(index);
            int rightIndex = getIndexOfRightChild(index);
            int largest = index;

            if(
                leftIndex < size &&
                heap[leftIndex].compareTo(heap[largest]) > 0
            ) {
                largest = leftIndex;
            }

            if(
                rightIndex < size &&
                heap[rightIndex].compareTo(heap[largest]) > 0
            ) {
                largest = rightIndex;
            }

            if(largest == index)
                break;

            swap(index, largest);
            index = largest;
        }
    }

    private int getIndexOfParent(int elementIndex) {
        return (elementIndex - 1) / 2;
    }

    private int getIndexOfLeftChild(int elementIndex) {
        return 2 * elementIndex + 1;
    }

    private int getIndexOfRightChild(int elementIndex) {
        return 2 * elementIndex + 2;
    }

    private void swap(int i, int j) {
        T tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
}
