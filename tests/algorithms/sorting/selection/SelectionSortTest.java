package tests.algorithms.sorting.selection;

import algorithms.sorting.selection.SelectionSort;
import tests.algorithms.sorting.SortTest;

public class SelectionSortTest extends SortTest {
    public static void main(String[] args) {
        testSort(SelectionSort::selectionSort);
    }
}
