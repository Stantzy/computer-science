package algorithms.sorting.merge;

public class MergeSort {
    public static int[] mergeSort(int[] numbers) {
        if(numbers.length <= 1)
            return numbers;

        int mid = numbers.length / 2;
        int[] left = new int[mid];
        int[] right = new int[numbers.length - mid];

        copyArray(numbers, 0, left, 0, mid);
        copyArray(numbers, mid, right, 0, numbers.length - mid);

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private static int[] merge(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];

        int firstPtr = 0;
        int secondPtr = 0;
        int resultPtr = 0;

        while(firstPtr < first.length && secondPtr < second.length) {
            if(first[firstPtr] < second[secondPtr]) {
                result[resultPtr] = first[firstPtr];
                firstPtr++;
            } else {
                result[resultPtr] = second[secondPtr];
                secondPtr++;
            }
            resultPtr++;
        }

        while(firstPtr < first.length) {
            result[resultPtr] = first[firstPtr];
            resultPtr++;
            firstPtr++;
        }

        while(secondPtr < second.length) {
            result[resultPtr] = second[secondPtr];
            resultPtr++;
            secondPtr++;
        }

        return result;
    }

    private static void copyArray
    (int[] src, int srcPos, int[] dst, int dstPos, int len) {
        for(int i = srcPos, j = dstPos; i < srcPos + len; i++, j++)
            dst[j] = src[i];
    }
}
