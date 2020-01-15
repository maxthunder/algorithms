import java.util.ArrayList;
import java.util.List;

/**
 * Bubble Sort Implementation (using swaps, instead of comparisons). List must be non-empty.
 * <link>https://en.wikipedia.org/wiki/Bubble_sort</link>
 *
 *  NOTE: I wrote this code..
 *
 * TIME COMPLEXITY (Performance):
 *  Worst-case: O(n^2) for swaps and comparisons
 *  Average-case: O(n^2) for swaps and comparisons
 *  Best-case: O(1) for swaps. O(n^2) for comparisons
 *
 * SPACE COMPLEXITY:
 *  Worst-case: О(1) auxiliary
 *
 */
public class BubbleSort extends AbstractSorting {
    @Override
    public List<Integer> sort(final List<Integer> list) {
        if (list == null || list.isEmpty())
            throw new IllegalStateException("Input list must be non-empty.");
        if (list.size() == 1)
            return list;

        List<Integer> inputList = new ArrayList<>(list);
        boolean swapped;
        do {
            swapped = bubblePass(inputList);
        } while (swapped);
        return inputList;
    }

    private static void swap(List<Integer> inputList, Integer firstIndex) {
        Integer save = inputList.get(firstIndex);
        inputList.set(firstIndex, inputList.get(firstIndex + 1));
        inputList.set(firstIndex + 1, save);
    }

    private static boolean bubblePass(List<Integer> list) {
        boolean swapped = false;
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1 && list.get(i) > list.get(i + 1)) {
                swap(list, i);
                swapped = true;
            }
        }
        return swapped;
    }
}
