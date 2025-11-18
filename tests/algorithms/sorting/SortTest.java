package tests.algorithms.sorting;

public abstract class SortTest {
    protected static int[] numbers = { 5, 4, 0, 2, 3, 1, 6 };
    protected static final int[] expected = { 0, 1, 2, 3, 4, 5, 6 };

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
