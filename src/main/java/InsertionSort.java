import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Insertion Sort Implementation. List must be non-empty.
 * <link>https://en.wikipedia.org/wiki/Insertion_sort<link>
 *
 * NOTE: I did NOT write this code. This is a classic algorithm
 * whose pseudocode I've seen in several books and online sources,
 * so I'm not sure whom credit should be given. I wrote this based
 * on that pseudocode, so it is certainly not original.
 *
 * TIME COMPLEXITY (Performance):
 *  Worst-case: O(n^2) for swaps and comparisons.
 *  Average-case: O(n^2) for swaps and comparisons.
 *  Best-case: O(n) for swaps. O(1) for comparison.
 *
 * SPACE COMPLEXITY:
 *  Worst-case: O(n) total. O(1) auxiliary.
 *
 * NOTES:
 *  The Good:
 *   Good for small datasets or almost sorted collections
 *   Lean amount of code could be welcomed in micro instances
 *
 * The Bad:
 *  Worst case is reverse ordered collection
 *  Bad in average-cases!!
 *
 */
public class InsertionSort extends AbstractSorting {
    @Override
    public List<Integer> sort(final List<Integer> list) {
        if (list == null || list.isEmpty())
            throw new IllegalStateException("Input list must be non-empty.");
        int[] array = list.stream().mapToInt(i -> i).toArray();
        for (int j = 1; j < array.length; j++) {
            int value = array[j];
            int i = j - 1;
            while (i >= 0 && array[i] > value) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = value;
        }
        return Arrays.stream(array).boxed().collect(Collectors.toList());
    }
}
