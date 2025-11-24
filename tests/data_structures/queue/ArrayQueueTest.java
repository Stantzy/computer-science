package tests.data_structures.queue;

import data_structures.queue.ArrayQueue;

public class ArrayQueueTest {
    public static void main(String[] args) {
        testEnqueueAndDequeue();
        testPeek();
        testClear();
    }

    private static void testEnqueueAndDequeue() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        boolean exceptionThrown = false;
        int element;

        try {
            queue.dequeue();
        } catch (RuntimeException e) {
            exceptionThrown = true;
            assert e.getMessage() != null :
                    "The exception message must not be null";
        }

        assert exceptionThrown :
                "Expected RuntimeException when dequeue() from an empty queue";

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        element = queue.dequeue();
        assert element == 10 : "testEnqueueAndDequeue: expected 10, got " + element;

        element = queue.dequeue();
        assert element == 20 : "testEnqueueAndDequeue: expected 20, got " + element;

        element = queue.dequeue();
        assert element == 30 : "testEnqueueAndDequeue: expected 30, got " + element;

    }

    private static void testPeek() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        boolean exceptionThrown = false;
        int element;

        try {
            queue.peek();
        } catch (RuntimeException e) {
            exceptionThrown = true;
            assert e.getMessage() != null :
                    "The exception message must not be null";
        }

        assert exceptionThrown :
                "Expected RuntimeException when peek() from an empty queue";

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        element = queue.peek();
        assert element == 10 : "testEnqueueAndDequeue: expected 10, got " + element;
        queue.dequeue();

        element = queue.peek();
        assert element == 20 : "testEnqueueAndDequeue: expected 10, got " + element;
        queue.dequeue();

        element = queue.peek();
        assert element == 30 : "testEnqueueAndDequeue: expected 10, got " + element;
        queue.dequeue();
    }

    private static void testClear() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        boolean exceptionThrown = false;
        int element;

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        queue.clear();

        assert queue.isEmpty() : "testClear: queue must be empty after clear()";
    }
}
