package tests.data_structures.queue;

import data_structures.queue.Queue;

public class QueueTest {
    public static void main(String[] args) {
        testEnqueueAndDequeue();
        testPeek();
        testSize();
        testIsEmpty();
    }

    private static void testEnqueueAndDequeue() {
        Queue<Integer> queue = new Queue<>();
        int element;

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
        Queue<Integer> queue = new Queue<>();
        Integer element;

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        element = queue.peek();
        assert element == 10 : "testPeek: expected 10, got " + element;
        queue.dequeue();
        element = queue.peek();
        assert element == 20 : "testPeek: expected 20, got " + element;
        queue.dequeue();
        element = queue.peek();
        assert element == 30 : "testPeek: expected 30, got " + element;
        queue.dequeue();
        element = queue.peek();
        assert element == null : "testPeek: expected null, got " + element;
    }

    private static void testSize() {
        Queue<Integer> queue = new Queue<>();
        int size;

        size = queue.size();
        assert size == 0 : "testSize: expected 0, got " + size;

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        size = queue.size();
        assert size == 3 : "testSize: expected 3, got " + size;
        queue.dequeue();
        size = queue.size();
        assert size == 2 : "testSize: expected 2, got " + size;
        queue.dequeue();
        size = queue.size();
        assert size == 1 : "testSize: expected 1, got " + size;
        queue.dequeue();
        size = queue.size();
        assert size == 0 : "testSize: expected 0, got " + size;
        queue.dequeue();
    }

    private static void testIsEmpty() {
        Queue<Integer> queue = new Queue<>();

        assert queue.isEmpty() : "testIsEmpty: initialized queue must be empty";
        queue.enqueue(10);
        assert !queue.isEmpty() :
                "testIsEmpty: queue with elements can't be empty";
    }
}
