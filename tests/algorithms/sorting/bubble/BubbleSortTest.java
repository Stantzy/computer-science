package tests.algorithms.sorting.bubble;

import tests.algorithms.sorting.SortTest;

import java.util.Arrays;

import static algorithms.sorting.bubble.BubbleSort.bubbleSort;

public class BubbleSortTest extends SortTest {
    public static void main(String[] args) {


        System.out.println(Arrays.toString(numbers));
        bubbleSort(numbers);
        System.out.println(Arrays.toString(numbers));

        checkResult(numbers, expected);
    }


}
