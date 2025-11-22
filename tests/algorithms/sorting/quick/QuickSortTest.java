package tests.algorithms.sorting.quick;

import algorithms.sorting.quick.QuickSort;
import tests.algorithms.sorting.SortTest;

public class QuickSortTest extends SortTest {
    public static void main(String[] args) {
        testSort(QuickSort::quickSort);
    }
}
