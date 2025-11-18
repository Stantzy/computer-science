package tests.algorithms.sorting.insertion;

import tests.algorithms.sorting.SortTest;

import java.util.Arrays;

import static algorithms.sorting.insertion.InsertionSort.insertionSort;

public class InsertionSortTest extends SortTest {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(numbers));
        insertionSort(numbers);
        System.out.println(Arrays.toString(numbers));

        checkResult(numbers, expected);
    }
}
