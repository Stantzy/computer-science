package tests.algorithms.sorting.bubble;

import tests.algorithms.sorting.SortTest;

import java.util.Arrays;

import static algorithms.sorting.bubble.BubbleSort.bubbleSort;

public class BubbleSortTest extends SortTest {
    public static void main(String[] args) {
        int[] numbers = { 5, 4, 0, 2, 3, 1, 6 };
        int[] expected = { 0, 1, 2, 3, 4, 5, 6 };

        System.out.println(Arrays.toString(numbers));
        bubbleSort(numbers);
        System.out.println(Arrays.toString(numbers));

        checkResult(numbers, expected);
    }


}
