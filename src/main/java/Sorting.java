import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Maxwell Stark
 */
public class Sorting {

    public enum Type {
        QUICK_SORT("QuickSort");
        private String name;
        Type(String name) { this.name = name; }
        public String getName() { return name; }
    }

    /**
     * Quick Sort Implementation. List must be non-empty.
     *
     * PERFORMANCE:
     *  Worst-case: O(n^2)
     *  Average-case: O(n log n)
     *  Best-case: O(n log n)     *
     * @param inputList List to sort. Must be non-empty.
     * @return Sorted list
     *
     */
    public static List<Integer> quickSort(List<Integer> inputList) {
        if (inputList == null || inputList.isEmpty())
            throw new IllegalStateException("Input list must be non-empty.");
        if (inputList.size() == 1)
            return inputList;
        if (inputList.size() == 2) {
            if (inputList.get(0) > inputList.get(1))
                Collections.reverse(inputList);
            return inputList;
        }

        Integer pivot = inputList.get(inputList.size() - 1);
        List<Integer> lessThanOrEqual = new ArrayList<>();
        List<Integer> greaterThan = new ArrayList<>();
        for (int i = 0; i < inputList.size() - 1; i++) {// skip lastItem/pivot
            Integer item = inputList.get(i);
            if (item <= pivot)
                lessThanOrEqual.add(item);
            else
                greaterThan.add(item);
        }

        List<Integer> completedList = (lessThanOrEqual.size() > 1) ? quickSort(lessThanOrEqual) : lessThanOrEqual;
        completedList.add(pivot);
        completedList.addAll((greaterThan.size() > 1) ? quickSort(greaterThan) : greaterThan);

        return completedList;
    }
}
