import java.util.*;

/**
 * @author Maxwell Stark
 */
public class Sorting {
    /**
     * Quick Sort Implementation. List must be non-empty.
     * <link>https://en.wikipedia.org/wiki/Quicksort</link>
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
     *
     * The Bad:
     *  -not a stable sort (relative order of equal items is not preserved)
     *
     * @param list List to sort. Must be non-empty.
     * @return Sorted list
     *
     */
    public static List<Integer> quickSort(List<Integer> list) {
        List<Integer> inputList = new ArrayList<>(list);
        if (inputList == null || inputList.isEmpty())
            throw new IllegalStateException("Input list must be non-empty.");
        if (inputList.size() == 1)
            return inputList;
        if (inputList.size() == 2) {
            if (inputList.get(0) > inputList.get(1))
                Collections.reverse(inputList);
            return inputList;
        }

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
        List<Integer> completedList = (lessThanOrEqual.size() > 1) ? quickSort(lessThanOrEqual) : lessThanOrEqual;
        completedList.add(pivot);
        completedList.addAll((greaterThan.size() > 1) ? quickSort(greaterThan) : greaterThan);
        return completedList;
    }

    /**
     * Merge Sort Implementation. List must be non-empty.
     * <link>https://en.wikipedia.org/wiki/Merge_sort</link>
     *
     * TIME COMPLEXITY (Performance):
     *  Worst-case: O(n log n)
     *  Average-case: O(n log n)
     *  Best-case: O(n log n) [natural variant: O(n)]
     *
     * SPACE COMPLEXITY:
     *  Worst-case: О(n) total with O(n) auxiliary; O(1) auxiliary with linked lists (Skiena 2008)
     *
     * @param list List to sort. Must be non-empty.
     * @return Sorted list
     *
     */
    public static List<Integer> mergeSort(List<Integer> list) {
        List<Integer> inputList = new ArrayList<>(list);
        if (inputList == null || inputList.isEmpty())
            throw new IllegalStateException("Input list must be non-empty.");
        if (inputList.size() == 1)
            return inputList;
        if (inputList.size() == 2) {
            if (inputList.get(0) > inputList.get(1))
                Collections.reverse(inputList);
            return inputList;
        }

        int splitIndex = inputList.size() / 2;
        List<Integer> first = mergeSort(inputList.subList(0, splitIndex));
        List<Integer> second = mergeSort(inputList.subList(splitIndex, inputList.size()));
        return merge(first, second);
    }

    private static List<Integer> merge(List<Integer> listA, List<Integer> listB) {
        List<Integer> completeList = new ArrayList<>();
        int i = 0, j = 0;
        while (i <= listA.size()) {
            while (j <= listB.size()) {
                if (i == listA.size() || j == listB.size()) {
                    if (i < listA.size()) {
                        i = take(completeList, listA.get(i), i);
                    } else if (j < listB.size()) {
                        j = take(completeList, listB.get(j), j);
                    } else {
                        i++;
                        j++;
                    }
                } else {
                    int a = listA.get(i);
                    int b = listB.get(j);

                    if (a < b) {
                        i = take(completeList, a, i);
                    } else if (a == b) {
                        i = take(completeList, a, i);
                        j = take(completeList, b, j);
                    } else {// a > b
                        j = take(completeList, b, j);
                    }
                }

            }
        }
        return completeList;
    }

    private static int take(List<Integer> completeList, int item, int index) {
        completeList.add(item);
        return index + 1;
    }

    /**
     * Bubble Sort Implementation (using swaps, instead of comparisons). List must be non-empty.
     * <link>https://en.wikipedia.org/wiki/Bubble_sort</link>
     *
     * TIME COMPLEXITY (Performance):
     *  Worst-case: O(n^2) for swaps and comparisons
     *  Average-case: O(n^2) for swaps and comparisons
     *  Best-case: O(1) for swaps. O(n^2) for comparisons
     *
     * SPACE COMPLEXITY:
     *  Worst-case: О(1) auxiliary
     *
     * @param list List to sort. Must be non-empty.
     * @return Sorted list
     *
     */
    public static List<Integer> bubbleSort(final List<Integer> list) {
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

    /**
     * Selection Sort Implementation (using swaps, instead of comparisons). List must be non-empty.
     * <link>https://en.wikipedia.org/wiki/Selection_sort<link>
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
     *  - generally better than BubbleSort or GnomeSort
     *  - high simplicity
     *  - performs well when auxiliary memory is limited
     *
     * The Bad:
     *  - generally worst than InsertionSort
     *  - O(n^2) is
     *
     * @param list List to sort. Must be non-empty.
     * @return Sorted list
     *
     */
    public static List<Integer> selectionSort(final List<Integer> list) {
        if (list == null || list.isEmpty())
            throw new IllegalStateException("Input list must be non-empty.");

        List<Integer> inputList = new ArrayList<>(list);
        List<Integer> sortedList = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i++) {
            Integer current = inputList.get(i);
            Integer min = current;
            int minIndex = i;

            for (int j = i; j < inputList.size(); j++) {
                if (inputList.get(j) < min) {
                    min = inputList.get(j);
                    minIndex = j;
                }
            }

            sortedList.add(min);
            if (!min.equals(current)) {
                inputList.set(minIndex, current);// swap
            }
        }
        return sortedList;
    }

}
