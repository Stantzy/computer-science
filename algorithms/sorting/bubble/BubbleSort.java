package algorithms.sorting.bubble;

import java.util.Arrays;

public class BubbleSort {
    public static void bubbleSort(int[] numbers) {
        for(;;) {
            boolean done = true;

            for(int i = numbers.length - 2; i >= 0; i--) {
                if(numbers[i] > numbers[i + 1]) {
                    int tmp = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = tmp;
                    done = false;
                }
            }

            if(done)
                break;
        }
    }

    public static void bubbleSortSimple(int[] numbers) {
        for(int i = 0; i < numbers.length - 1; i++) {
            for(int j = 0; j < numbers.length - 1; j++) {
                if(numbers[j] > numbers[j + 1]) {
                    int tmp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = tmp;
                }
            }
        }
    }
}
