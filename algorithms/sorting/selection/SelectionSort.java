package algorithms.sorting.selection;

public class SelectionSort {
    public static void selectionSort(int[] numbers) {
        for(int i = 0; i < numbers.length; i++) {
            int min = i;

            for(int j = i + 1; j < numbers.length; j++) {
                if(numbers[j] < numbers[min])
                    min = j;
            }

            if(min != i) {
                int tmp = numbers[min];
                numbers[min] = numbers[i];
                numbers[i] = tmp;
            }
        }
    }
}
