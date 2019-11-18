import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Maxwell Stark
 */
public class SortingTest {

    public enum SortingAlgorithm {
        QUICK_SORT("QuickSort"),
        MERGE_SORT("MergeSort (Top-Down)"),
        BUBBLE_SORT("BubbleSort (Swaps)"),
        SELECTION_SORT("SelectionSort (Swaps)");
        private String name;
        SortingAlgorithm(String name) { this.name = name; }
        public String getName() { return name; }
    }

    @Data
    @AllArgsConstructor
    private class SortingResult {
        final List<Integer> resultList;
        final long runtime;
    }

    @Test
    public void RunAllSortingAlgorithms() {
        long seed = 17834589345L;
        int runSize = 20000;
        final List<Integer> unsortedList = numberGen(seed, runSize);

        List<Integer> expected = new ArrayList<>(unsortedList);
        Collections.sort(expected);

        Map<SortingAlgorithm, SortingResult> algorithmsWithResults = new LinkedHashMap<>();
        algorithmsWithResults.put(SortingAlgorithm.QUICK_SORT, quickSort(unsortedList));
        algorithmsWithResults.put(SortingAlgorithm.MERGE_SORT, mergeSort(unsortedList));
        algorithmsWithResults.put(SortingAlgorithm.BUBBLE_SORT, bubbleSort(unsortedList));
        algorithmsWithResults.put(SortingAlgorithm.SELECTION_SORT, selectionSort(unsortedList));

        System.out.println();
        algorithmsWithResults.forEach((key, value) -> {
            if (value.resultList.equals(expected))
                System.out.printf("%s sorted %,d integers in %,d ms.%n", key.getName(), runSize, value.runtime);
            assertThat("List sorted by <"+key.getName()+"> did not match expected list.", value.resultList, is(expected));
        });
    }

    private SortingResult quickSort(List<Integer> unsortedList) {
        Instant start = Instant.now();
        List<Integer> sortedList = Sorting.quickSort(unsortedList);
        Instant finish = Instant.now();
        return new SortingResult(sortedList, Duration.between(start, finish).toMillis());
    }

    private SortingResult mergeSort(List<Integer> unsortedList) {
        Instant start = Instant.now();
        List<Integer> sortedList = Sorting.mergeSort(unsortedList);
        Instant finish = Instant.now();
        return new SortingResult(sortedList, Duration.between(start, finish).toMillis());
    }

    private SortingResult bubbleSort(List<Integer> unsortedList) {
        Instant start = Instant.now();
        List<Integer> sortedList = Sorting.bubbleSort(unsortedList);
        Instant finish = Instant.now();
        return new SortingResult(sortedList, Duration.between(start, finish).toMillis());
    }

    private SortingResult selectionSort(List<Integer> unsortedList) {
        Instant start = Instant.now();
        List<Integer> sortedList = Sorting.selectionSort(unsortedList);
        Instant finish = Instant.now();
        return new SortingResult(sortedList, Duration.between(start, finish).toMillis());
    }

    private List<Integer> numberGen(long seed, int runSize) {
        List<Integer> list;
        Random rand = new Random(seed);
        list = IntStream.range(0, runSize).mapToObj(i -> rand.nextInt() % 100).collect(Collectors.toList());
        return Collections.unmodifiableList(list);
    }
}
