package algorithms.sorting.quick;

import java.util.Arrays;

public class QuickSort {
    public static int[] quickSort(int[] numbers) {
        return quickSort(numbers, 0, numbers.length - 1);
    }

    private static int[] quickSort(int[] numbers, int low, int high) {
        if(low < high) {
            int pivotIndex = partition(numbers, low, high);
            quickSort(numbers, low, pivotIndex - 1);
            quickSort(numbers, pivotIndex + 1, high);
        }
        return numbers;
    }

    private static int partition(int[] numbers, int low, int high) {
        int mid = (low + high) / 2;
        int pivotIndex = getMedianOfThreeIndex(numbers, low, mid, high);
        int pivot = numbers[pivotIndex];
        int i = low - 1;

        swap(numbers, pivotIndex, high);

        for(int j = low; j < high; j++) {
            if(numbers[j] <= pivot) {
                i++;
                swap(numbers, i, j);
            }
        }

        swap(numbers, i + 1, high);

        return i + 1;
    }

    private static int getMedianOfThreeIndex(int[] numbers, int i, int j, int k) {
        int a = numbers[i];
        int b = numbers[j];
        int c = numbers[k];

        if(a >= b && a <= c || a >= c && a <= b)
            return i;
        else if(b <= a && b >= c || b >= a && b <= c)
            return j;
        else
            return k;
    }

    private static void swap(int[] numbers, int i, int j) {
        int tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp;
    }
}
