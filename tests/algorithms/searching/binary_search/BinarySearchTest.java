package tests.algorithms.searching.binary_search;

import java.util.NoSuchElementException;

import static algorithms.searching.binary_search.BinarySearch.binarySearch;

public class BinarySearchTest {
    public static void main(String[] args) {
        runTest1();
        runTest2();
        runTest3();
        runTest4();
    }

    private static void runTest1() {
        int[] array = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };

        for (int i = 0; i < array.length; i++) {
            int result = binarySearch(array, i);
            assert result == i;
        }
    }

    private static void runTest2() {
        int[] array = new int[] { 0, 1 };

        for (int i = 0; i < array.length; i++) {
            int result = binarySearch(array, i);
            assert result == i;
        }
    }

    private static void runTest3() {
        int[] array = new int[] {};

        boolean exceptionThrown = false;
        try {
            binarySearch(array, 1);
        } catch(NoSuchElementException e) {
            exceptionThrown = true;
        }

        assert exceptionThrown;
    }

    private static void runTest4() {
        int[] array = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };

        boolean exceptionThrown = false;
        try {
            binarySearch(array, 8);
        } catch(NoSuchElementException e) {
            exceptionThrown = true;
        }

        assert exceptionThrown;
    }
}
