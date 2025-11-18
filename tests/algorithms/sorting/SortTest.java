package tests.algorithms.sorting;

public abstract class SortTest {
    protected static void checkResult(int[] numbers, int[] expected) {
        for(int i = 0; i < numbers.length; i++) {
            if(numbers[i] != expected[i]) {
                System.out.println(
                    "TEST FAILED: expected " + expected[i] +
                    " got " + numbers[i] + " at position " + i
                );
            }
        }
    }
}
