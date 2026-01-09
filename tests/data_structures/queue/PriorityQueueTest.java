package tests.data_structures.queue;

import data_structures.queue.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {
        testBaseFunctionality();
        testBoundaryPriorityValues();
        testAging();
        testOverflow();
        testMaximum();
        testEmptyPriorityQueue();
    }

    private static void testBaseFunctionality() {
        PriorityQueue<String> pq = new PriorityQueue<>(10, false);
        pq.insert("A", 5);
        pq.insert("B", 9);
        pq.insert("C", 1);

        assert pq.extractMax().equals("B");
        assert pq.extractMax().equals("A");
        assert pq.extractMax().equals("C");
    }

    private static void testBoundaryPriorityValues() {
        PriorityQueue<String> pq = new PriorityQueue<>(10, false);
        pq.insert("Min", -100);
        pq.insert("Max", 100);
        pq.insert("Normal", 5);

        assert pq.extractMax().equals("Max");
        assert pq.extractMax().equals("Normal");
        assert pq.extractMax().equals("Min");
    }

    private static void testAging() {
        PriorityQueue<String> pq = new PriorityQueue<>(10, true);
        pq.insert("Low", 0);

        for(int i = 0; i < 8; i++) {
            pq.insert("High" + i, 9);
            pq.extractMax();
        }

        pq.insert("Item", 0);

        assert pq.extractMax().equals("Low");
    }

    private static void testOverflow() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(3, false);
        assert pq.insert(0, 0) == true;
        assert pq.insert(1, 1) == true;
        assert pq.insert(2, 2) == true;
        assert pq.insert(3, 3) == false;

        pq.extractMax();

        assert pq.insert(3, 3) == true;
    }

    private static void testMaximum() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(5, false);
        pq.insert(1, 1);
        pq.insert(5, 5);
        pq.insert(100, 9);

        assert pq.maximum() == 100;
        assert pq.maximum() == 100;

        pq.extractMax();

        assert pq.maximum() == 5;
    }

    private static void testEmptyPriorityQueue() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        assert pq.maximum() == null;
        assert pq.extractMax() == null;
    }
}
