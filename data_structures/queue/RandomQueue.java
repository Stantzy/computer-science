package data_structures.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomQueue<T> implements Iterable<T>{
    private static final int DEFAULT_CAPACITY = 16;

    private final T[] queue;
    private int size;
    private int headIndex;
    private int tailIndex;

    public RandomQueue() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public RandomQueue(int initialCapacity) {
        if(initialCapacity < 1)
            initialCapacity = DEFAULT_CAPACITY;
        queue = (T[]) new Object[initialCapacity];
        size = 0;
        headIndex = 0;
        tailIndex = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == queue.length;
    }

    public int capacity() {
        return queue.length;
    }

    public T sample() {
        if(size == 0)
            return null;

        return queue[getRandomIndex()];
    }

    public boolean enqueue(T element) {
        if(size >= queue.length)
            return false;

        queue[tailIndex] = element;
        tailIndex = (tailIndex + 1) % queue.length;
        size++;

        return true;
    }

    public T dequeue() {
        if(size <= 0)
            return null;

        int randomIndex = getRandomIndex();
        swap(headIndex, randomIndex);
        T element = queue[headIndex];
        size--;
        headIndex++;

        if(headIndex >= queue.length)
            headIndex = 0;

        if(size == 0) {
            headIndex = 0;
            tailIndex = 0;
        }

        return element;
    }

    private int getRandomIndex() {
        if(size == 0)
            return headIndex;

        return (headIndex +  (int) (Math.random() * size)) % queue.length;
    }

    private void swap(int i, int j) {
        T tmp = queue[i];
        queue[i] = queue[j];
        queue[j] = tmp;
    }

    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int count = 0;
            private final int[] indexes;

            {
                indexes = new int[size];

                int pos = headIndex;
                for(int i = 0; i < size; i++) {
                    indexes[i] = pos;
                    pos = (pos + 1) % queue.length;
                }

                /* Fisher-Yates shuffle */
                Random random = new Random();
                for(int i = size - 1; i > 0; i--) {
                    int j = random.nextInt(i + 1);
                    int tmp = indexes[i];
                    indexes[i] = indexes[j];
                    indexes[j] = tmp;
                }
            }

            @Override
            public boolean hasNext() {
                return count < indexes.length;
            }

            @Override
            public T next() {
                if(!hasNext())
                    throw new NoSuchElementException();
                return queue[indexes[count++]];
            }
        };
    }
}
