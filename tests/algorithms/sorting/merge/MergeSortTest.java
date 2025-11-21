package tests.algorithms.sorting.merge;

import tests.algorithms.sorting.SortTest;

import java.util.Arrays;

import static algorithms.sorting.merge.MergeSort.mergeSort;

public class MergeSortTest extends SortTest {
    public static void main(String[] args) {
        int[] result = new int[numbers.length];
        System.out.println(Arrays.toString(numbers));
        result = mergeSort(numbers);
        System.out.println(Arrays.toString(result));

        checkResult(result, expected);
    }
}
