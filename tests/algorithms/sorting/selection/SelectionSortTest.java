package tests.algorithms.sorting.selection;

import tests.algorithms.sorting.SortTest;

import java.util.Arrays;

import static algorithms.sorting.selection.SelectionSort.selectionSort;

public class SelectionSortTest extends SortTest {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(numbers));
        selectionSort(numbers);
        System.out.println(Arrays.toString(numbers));

        checkResult(numbers, expected);
    }
}
