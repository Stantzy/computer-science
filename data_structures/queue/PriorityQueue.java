package data_structures.queue;

public class PriorityQueue<T> {
    private static final int DEFAULT_CAPACITY = 32;
    private static final int AGING_INTERVAL = 8;

    private Item<T>[] heap;
    private int size;
    private boolean enableAging;
    private int extractsSinceLastAging;

    private static class Item<T> {
        private final static int MIN_PRIORITY = 0;
        private final static int MAX_PRIORITY = 9;
        private final static int DEFAULT_PRIORITY = 4;

        T element;
        int priority;

        Item(T element) {
            this(element, DEFAULT_PRIORITY);
        }

        Item(T element, int priority) {
            if(priority < MIN_PRIORITY)
                priority = MIN_PRIORITY;
            else if(priority > MAX_PRIORITY)
                priority = MAX_PRIORITY;
            this.element = element;
            this.priority = priority;
        }

        public boolean increasePriority() {
            if(this.priority < MAX_PRIORITY) {
                this.priority++;
                return true;
            }
            return false;
        }
    }

    public PriorityQueue() {
        this(DEFAULT_CAPACITY, false);
    }

    public PriorityQueue(int capacity, boolean enableAging) {
        if(capacity < 1)
            capacity = 1;
        heap = (Item<T>[]) new Item[capacity];
        size = 0;
        this.enableAging = enableAging;
        extractsSinceLastAging = 0;
    }

    public boolean insert(T element, int priority) {
        if(size >= heap.length)
            return false;

        heap[size] = new Item<>(element, priority);
        heapifyUp(size);
        size++;

        return true;
    }

    public T extractMax() {
        if(size <= 0)
            return null;

        T elem = heap[0].element;
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;

        if(size > 0)
            heapifyDown(0);

        if(enableAging)
            extractsSinceLastAging++;

        if(enableAging && extractsSinceLastAging >= AGING_INTERVAL) {
            increasePriorities();
            extractsSinceLastAging = 0;
        }

        return elem;
    }

    public T maximum() {
        return (size == 0) ? null : heap[0].element;
    }

    private void heapifyUp(int index) {
        while(index > 0) {
            int parentIndex = getParentIndex(index);

            if(heap[index].priority < heap[parentIndex].priority)
                break;

            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void heapifyDown(int index) {
        while(true) {
            int leftIndex = getLeftChildIndex(index);
            int rightIndex = getRightChildIndex(index);
            int largest = index;

            if(
                leftIndex < size &&
                heap[largest].priority < heap[leftIndex].priority
            ) {
                largest = leftIndex;
            }

            if(
                rightIndex < size &&
                    heap[largest].priority < heap[rightIndex].priority
            ) {
                largest = rightIndex;
            }

            if(largest == index)
                break;

            swap(index, largest);
            index = largest;
        }
    }

    private void increasePriorities() {
        for(int i = 0; i < size; i++)
            heap[i].increasePriority();
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getLeftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int getRightChildIndex(int index) {
        return index * 2 + 2;
    }

    private void swap(int i, int j) {
        Item<T> tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
}
