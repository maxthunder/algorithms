
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sorting {

    // TODO: Big O info

    /**
     * Quick Sort. List must be non-empty.
     *
     * PERFORMANCE:
     *  Worst-case: O(n^2)
     *  Average-case: O(n log n)
     *  Best-case: O(n log n)
     *
     * @param inputList List to sort. Must be non-empty
     * @return Sorted list.
     *
     */
    public List<Integer> quickSort(List<Integer> inputList) {
        if (inputList.size() == 1)
            return inputList;

        Integer pivot = inputList.get(0);
        for (int i = 0; i < inputList.size() - 1; i++) {// skip lastItem/pivot
            
        }


        List<Integer> greaterThanPivot = inputList.stream()
                .parallel()
                .filter(i -> i > pivot)
                .collect(Collectors.toList());

        return null;
    }

}
