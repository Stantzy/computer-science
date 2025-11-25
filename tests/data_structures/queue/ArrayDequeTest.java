package tests.data_structures.queue;

import data_structures.queue.ArrayDeque;

public class ArrayDequeTest {
    public static void main(String[] args) {
        testPushFrontAndPopFront();
        testPushBackAndPopBack();
        testPeek();
        testSize();
        testIsEmpty();
    }

    private static void testPushFrontAndPopFront() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        Integer element;

        deque.pushBack(10);
        deque.pushBack(20);
        deque.pushBack(30);

        element = deque.popFront();
        assert element == 10 : "testPushFrontAndPopFront: expected 10, got " + element;
        element = deque.popFront();
        assert element == 20 : "testPushFrontAndPopFront: expected 20, got " + element;
        element = deque.popFront();
        assert element == 30 : "testPushFrontAndPopFront: expected 30, got " + element;

        boolean exceptionThrown = false;
        try {
            element = deque.popFront();
        } catch (RuntimeException e) {
            exceptionThrown = true;
            assert e.getMessage() != null :
                    "testPushFrontAndPopFront: The exception message must not be null";
        }

        assert exceptionThrown :
                "testPushFrontAndPopFront: expected null, got " + element;
    }

    private static void testPushBackAndPopBack() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        Integer element;

        deque.pushBack(10);
        deque.pushBack(20);
        deque.pushBack(30);

        element = deque.popBack();
        assert element == 30 : "testPushBackAndPopBack: expected 10, got " + element;
        element = deque.popBack();
        assert element == 20 : "testPushBackAndPopBack: expected 20, got " + element;
        element = deque.popBack();
        assert element == 10 : "testPushBackAndPopBack: expected 30, got " + element;
    }

    private static void testPeek() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        Integer element;

        deque.pushFront(10);
        deque.pushFront(20);
        deque.pushFront(30);

        element = deque.peekFront();
        assert element == 30 : "testPeek: expected 30, got " + element;
        element = deque.peekBack();
        assert element == 10 : "testPeek: expected 10, got " + element;
        deque.popBack();

        element = deque.peekBack();
        assert element == 20 : "testPeek: expected 20, got " + element;
        element = deque.peekFront();
        assert element == 30 : "testPeek: expected 30, got " + element;
        deque.popBack();

        element = deque.peekBack();
        assert element == 30 : "testPeek: expected 30, got " + element;
        element = deque.peekFront();
        assert element == 30 : "testPeek: expected 30, got " + element;
        element = deque.popFront();

        boolean exceptionThrown = false;
        try {
            element = deque.peekBack();
        } catch (RuntimeException e) {
            exceptionThrown = true;
            assert e.getMessage() != null :
                    "testPeek: The exception message must not be null";
        }
        assert exceptionThrown :
                "testPeek: expected null, got " + element;

        exceptionThrown = false;
        try {
            element = deque.peekFront();
        } catch (RuntimeException e) {
            exceptionThrown = true;
            assert e.getMessage() != null :
                    "testPeek: The exception message must not be null";
        }
        assert exceptionThrown :
                "testPeek: expected null, got " + element;
    }

    private static void testSize() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int size;

        size = deque.size();
        assert size == 0 : "testSize: expected 0, got " + size;

        deque.pushFront(10);
        deque.pushFront(20);
        deque.pushFront(30);

        size = deque.size();
        assert size == 3 : "testSize: expected 3, got " + size;
        deque.popFront();
        size = deque.size();
        assert size == 2 : "testSize: expected 2, got " + size;
        deque.popBack();
        size = deque.size();
        assert size == 1 : "testSize: expected 1, got " + size;
        deque.popFront();
        size = deque.size();
        assert size == 0 : "testSize: expected 0, got " + size;


        boolean exceptionThrown = false;
        try {
            deque.popBack();
        } catch (RuntimeException e) {
            exceptionThrown = true;
            assert e.getMessage() != null :
                    "testSize: The exception message must not be null";
        }
        assert exceptionThrown :
                "testSize: expected null";
    }

    private static void testIsEmpty() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        assert deque.isEmpty() : "testIsEmpty: initialized queue must be empty";
        deque.pushFront(10);
        assert !deque.isEmpty() :
                "testIsEmpty: queue with elements can't be empty";
    }
}
