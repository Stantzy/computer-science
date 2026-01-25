package algorithms.searching.binary_search;

import java.util.NoSuchElementException;

public class BinarySearch {
    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(array[mid] == target)
                return mid;
            else if(array[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }

        throw new NoSuchElementException(
            "Not found number in array: " + target
        );
    }
}