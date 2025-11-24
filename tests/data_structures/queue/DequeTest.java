package tests.data_structures.queue;

import data_structures.queue.Deque;
import data_structures.queue.Queue;

public class DequeTest {
    public static void main(String[] args) {
        testPushFrontAndPopFront();
        testPushBackAndPopBack();
        testPeek();
        testSize();
        testIsEmpty();
    }

    private static void testPushFrontAndPopFront() {
        Deque<Integer> deque = new Deque<>();
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
        element = deque.popFront();
        assert element == null : "testPushFrontAndPopFront: expected null, got " + element;
    }

    private static void testPushBackAndPopBack() {
        Deque<Integer> deque = new Deque<>();
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
        element = deque.peekBack();
        assert element == null : "testPushBackAndPopBack: expected null, got " + element;
    }

    private static void testPeek() {
        Deque<Integer> deque = new Deque<>();
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

        element = deque.peekBack();
        assert element == null : "testPeek: expected null, got " + element;
        element = deque.peekFront();
        assert element == null : "testPeek: expected null, got " + element;
    }

    private static void testSize() {
        Deque<Integer> deque = new Deque<>();
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
        deque.popBack();
    }

    private static void testIsEmpty() {
        Deque<Integer> deque = new Deque<>();

        assert deque.isEmpty() : "testIsEmpty: initialized queue must be empty";
        deque.pushFront(10);
        assert !deque.isEmpty() :
                "testIsEmpty: queue with elements can't be empty";
    }
}
