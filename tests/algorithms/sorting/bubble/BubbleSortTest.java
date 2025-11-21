package tests.algorithms.sorting.bubble;

import algorithms.sorting.bubble.BubbleSort;
import tests.algorithms.sorting.SortTest;

public class BubbleSortTest extends SortTest {
    public static void main(String[] args) {
        testSort(BubbleSort::bubbleSort);
        testSort(BubbleSort::bubbleSortSimple);
    }
}
