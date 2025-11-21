package algorithms.sorting.insertion;

public class InsertionSort {
    public static int[] insertionSort(int[] numbers) {
        for(int i = 1; i < numbers.length; i++) {
            int sorted = i - 1;
            while(sorted >= 0 && numbers[sorted] > numbers[sorted + 1]) {
                int tmp = numbers[sorted + 1];
                numbers[sorted + 1] = numbers[sorted];
                numbers[sorted] = tmp;
                sorted--;
            }
        }
        return numbers;
    }
}
