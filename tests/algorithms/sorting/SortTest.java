package tests.algorithms.sorting;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;

public abstract class SortTest {
    protected static final int[][] numbersCases = {
            IntStream.range(0, 10).toArray(),
            IntStream.iterate(10, i -> i - 1).limit(10).toArray(),
            IntStream.range(0, 100).toArray(),
            IntStream.iterate(99, i -> i - 1).limit(100).toArray(),
            IntStream.range(0, 50).map(i -> (int)(Math.random()*100)).toArray(),
            IntStream.generate(() -> 0).limit(20).toArray(),
            IntStream.range(-10000, 10000).toArray(),
            IntStream.iterate(10000, i -> i - 1).limit(20000).toArray()
    };

    protected static int[][] expectedCases = Arrays.stream(numbersCases)
            .map(arr -> IntStream.of(arr).sorted().toArray())
            .toArray(int[][]::new);

    protected static void testSort(Function<int[], int[]> sorter) {
        long startTime = System.nanoTime();

        for(int i = 0; i < numbersCases.length; i++) {
            int[] result = sorter.apply(numbersCases[i]);
            checkResult(result, expectedCases[i]);
        }

        long endTime = System.nanoTime();
        double duration = (double) (endTime - startTime) / 1_000_000_000.0;
        System.out.println(duration);
    }

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
