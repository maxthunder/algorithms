import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Insertion Sort Implementation. List must be non-empty.
 * <link>https://en.wikipedia.org/wiki/Insertion_sort<link>
 *
 * NOTE: I wrote this code, but I found several implementations that
 * mirror this one. This is classical way of insertion sorting and
 * therefore, I do not claim that I am the original writer of this implementation
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
    public InsertionSort(List<Integer> list) {
        integerList = Collections.unmodifiableList(list);
    }

    @Override
    public List<Integer> sort() {
        if (integerList == null || integerList.isEmpty())
            throw new IllegalStateException("Input list must be non-empty.");
        int[] array = integerList.stream().mapToInt(i -> i).toArray();
        for (int n = 1; n < array.length; n++) {
            int value = array[n];
            int i = n - 1;
            while (i >= 0 && array[i] > value) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = value;
        }
        return Arrays.stream(array).boxed().collect(Collectors.toList());
    }
}
