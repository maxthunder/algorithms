package sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Selection Sort Implementation (using swaps, instead of comparisons). List must be non-empty.
 * <link>https://en.wikipedia.org/wiki/Selection_sort<link>
 *
 *  NOTE: I wrote this code.
 *
 * TIME COMPLEXITY (Performance):
 *  Worst-case: O(n^2)
 *  Average-case: O(n^2)
 *  Best-case: O(n^2)
 *
 * SPACE COMPLEXITY:
 *  Worst-case: О(1) auxiliary
 *
 * NOTES:
 *  The Good:
 *  - generally better than sorting.BubbleSort or GnomeSort
 *  - high simplicity
 *  - performs well when auxiliary memory is limited
 *
 * The Bad:
 *  - generally worst than sorting.InsertionSort
 *  - O(n^2) is
 *
 */
public class SelectionSort extends AbstractSorting {
    public SelectionSort(List<Integer> list) {
        super(SortingAlgorithm.SELECTION_SORT, Collections.unmodifiableList(list));

    }

    @Override
    public List<Integer> sort() {
        List<Integer> baseCases = getBaseCases(integerList);
        if (baseCases != null)
            return baseCases;

        List<Integer> inputList = new ArrayList<>(integerList);
        List<Integer> sortedList = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i++) {
            Integer current = inputList.get(i);

            // get minimum item and index
            Integer min = current;
            int minIndex = i;
            for (int j = i; j < inputList.size(); j++) {
                if (inputList.get(j) < min) {
                    min = inputList.get(j);
                    minIndex = j;
                }
            }

            sortedList.add(min);

            // swap current and min if !same
            if (!min.equals(current)) {
                inputList.set(minIndex, current);
            }
        }
        return sortedList;
    }
}
