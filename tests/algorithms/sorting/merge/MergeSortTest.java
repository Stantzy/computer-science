package tests.algorithms.sorting.merge;

import algorithms.sorting.merge.MergeSort;
import tests.algorithms.sorting.SortTest;

public class MergeSortTest extends SortTest {
    public static void main(String[] args) {
        testSort(MergeSort::mergeSort);
    }
}
