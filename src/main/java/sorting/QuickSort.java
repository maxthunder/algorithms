package sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Quick Sort Implementation. List must be non-empty.
 * <link>https://en.wikipedia.org/wiki/Quicksort</link>
 *
 *  NOTE: I wrote this code...
 *
 * TIME COMPLEXITY (Performance):
 *  Worst-case: O(n^2) - caused by n-1 partitioning routine when item is largest/smallest
 *  Average-case: O(n log n)
 *  Best-case: O(n log n)
 *
 * SPACE COMPLEXITY:
 *  O(n) auxiliary (naive); O(log n) auxiliary (Sedgewick 1978)
 *
 * NOTES:
 *  -comparison sort
 *
 * The Good:
 *  -O(n^2) are rare
 *  -solid choice for average-case scenarios
 *
 * The Bad:
 *  -not a stable sort (relative order of equal items is not preserved)
 *
 */
public class QuickSort extends AbstractSorting {
    public QuickSort(List<Integer> list) {
        super(SortingAlgorithm.QUICK_SORT, Collections.unmodifiableList(list));
    }

    @Override
    public List<Integer> sort() {
        return quicksort(integerList);
    }

    private List<Integer> quicksort(List<Integer> list) {
        List<Integer> baseCases = getBaseCases(list);
        if (baseCases != null)
            return baseCases;
        List<Integer> inputList = new ArrayList<>(list);

        int pivot = inputList.get(inputList.size() - 1);
        List<Integer> lessThanOrEqual = new ArrayList<>();
        List<Integer> greaterThan = new ArrayList<>();
        for (int i = 0; i < inputList.size() - 1; i++) {// skip lastItem/pivot
            int item = inputList.get(i);
            if (item <= pivot)
                lessThanOrEqual.add(item);
            else
                greaterThan.add(item);
        }

        // only quick sort only if the list is not a singleton
        List<Integer> completedList = (lessThanOrEqual.size() > 1) ? quicksort(lessThanOrEqual) : lessThanOrEqual;
        completedList.add(pivot);
        completedList.addAll((greaterThan.size() > 1) ? quicksort(greaterThan) : greaterThan);
        return completedList;
    }
}
