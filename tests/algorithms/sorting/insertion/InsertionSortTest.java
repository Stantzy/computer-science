package tests.algorithms.sorting.insertion;

import algorithms.sorting.insertion.InsertionSort;
import tests.algorithms.sorting.SortTest;

public class InsertionSortTest extends SortTest {
    public static void main(String[] args) {
        testSort(InsertionSort::insertionSort);
    }
}
