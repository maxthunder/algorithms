import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Merge Sort Implementation. List must be non-empty.
 * <link>https://en.wikipedia.org/wiki/Merge_sort</link>
 *
 *  NOTE: I wrote this code...
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
public class MergeSort extends AbstractSorting implements Callable<List<Integer>> {
    public MergeSort(List<Integer> list) {
        algorithmType = SortingAlgorithm.MERGE_SORT;
        integerList = list;
    }

    @Override
    public List<Integer> call() {
        return sort();
    }

    @Override
    public List<Integer> sort() {
        return mergesort(integerList);
    }

    private List<Integer> mergesort(List<Integer> list) {
        List<Integer> baseCases = getBaseCases(list);
        if (baseCases != null)
            return baseCases;

        List<Integer> inputList = new ArrayList<>(list);

        int splitIndex = inputList.size() / 2;
        List<Integer> first = mergesort(inputList.subList(0, splitIndex));
        List<Integer> second = mergesort(inputList.subList(splitIndex, inputList.size()));
        return merge(first, second);
    }

    private int take(List<Integer> completeList, int item, int index) {
        completeList.add(item);
        return index + 1;
    }

    private List<Integer> merge(List<Integer> listA, List<Integer> listB) {
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
}
