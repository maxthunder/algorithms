package search;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author maxwell.stark
 * @since 4/16/25
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] array = { 2, 3, 4, 10, 40 };

        int foundIndex = binarySearch(array, 0, array.length - 1, 40);
    }

    static int binarySearch(int[] array, int low, int high, int target) {
        return 0;
    }
}
