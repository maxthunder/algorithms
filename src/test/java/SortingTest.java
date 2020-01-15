import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Maxwell Stark
 */
public class SortingTest {

    public enum SortingAlgorithm {
        QUICK_SORT("QuickSort", "Θ(n^2)", "Θ(n log n)", "Θ(n log n)"),
        MERGE_SORT("MergeSort (Top-Down)", "Θ(n log n)", "Θ(n log n)", "Θ(n log n)"),
        BUBBLE_SORT("BubbleSort (Swaps)", "Θ(n^2)", "Θ(n^2)", "Θ(1)"),
        SELECTION_SORT("SelectionSort (Swaps)", "Θ(n^2)", "Θ(n^2)", "Θ(n^2)"),
        INSERTION_SORT("InsertionSort", "Θ(n^2)", "Θ(n^2)", "Θ(n)");
        private String name;
        private String worstCase;
        private String avgCase;
        private String bestCase;
        SortingAlgorithm(String name, String worstCase, String avgCase, String bestCase) {
            this.name = name;
            this.worstCase = worstCase;
            this.avgCase = avgCase;
            this.bestCase = bestCase;
        }
        public String getName() { return name; }
        public String getWorstCase() {
            return worstCase;
        }
        public String getAvgCase() {
            return avgCase;
        }
        public String getBestCase() {
            return bestCase;
        }
        public String toString() {
            return name.toUpperCase() + ": " +
                   "[Worst case: " + worstCase + ", " +
                   "Average case: " + avgCase + ", " +
                   "Best case: " + bestCase + "]\n";
        }
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
        int runSize = 40_000;
        final List<Integer> unsortedList = numberGen(seed, runSize);

        List<Integer> expected = new ArrayList<>(unsortedList);
        Collections.sort(expected);

        Map<SortingAlgorithm, SortingResult> algorithmsWithResults = new LinkedHashMap<>();
        algorithmsWithResults.put(SortingAlgorithm.QUICK_SORT, quickSort(unsortedList));
        algorithmsWithResults.put(SortingAlgorithm.MERGE_SORT, mergeSort(unsortedList));
        algorithmsWithResults.put(SortingAlgorithm.BUBBLE_SORT, bubbleSort(unsortedList));
        algorithmsWithResults.put(SortingAlgorithm.SELECTION_SORT, selectionSort(unsortedList));
        algorithmsWithResults.put(SortingAlgorithm.INSERTION_SORT, insertionSort(unsortedList));

        System.out.println();
        algorithmsWithResults.forEach((key, value) -> {
            if (value.resultList.equals(expected))
                System.out.printf("%s sorted %,d integers in %,d ms.%n\n", key, runSize, value.runtime);
            assertThat("List sorted by <"+key.getName()+"> did not match expected list.", value.resultList, is(expected));
        });
    }

    private SortingResult quickSort(List<Integer> unsortedList) {
        Instant start = Instant.now();
        QuickSort quickSort = new QuickSort();
        List<Integer> sortedList = quickSort.sort(unsortedList);
        Instant finish = Instant.now();
        return new SortingResult(sortedList, Duration.between(start, finish).toMillis());
    }

    private SortingResult mergeSort(List<Integer> unsortedList) {
        Instant start = Instant.now();
        MergeSort mergeSort = new MergeSort();
        List<Integer> sortedList = mergeSort.sort(unsortedList);
        Instant finish = Instant.now();
        return new SortingResult(sortedList, Duration.between(start, finish).toMillis());
    }

    private SortingResult bubbleSort(List<Integer> unsortedList) {
        Instant start = Instant.now();
        BubbleSort bubbleSort = new BubbleSort();
        List<Integer> sortedList = bubbleSort.sort(unsortedList);
        Instant finish = Instant.now();
        return new SortingResult(sortedList, Duration.between(start, finish).toMillis());
    }

    private SortingResult selectionSort(List<Integer> unsortedList) {
        Instant start = Instant.now();
        SelectionSort selectionSort = new SelectionSort();
        List<Integer> sortedList = selectionSort.sort(unsortedList);
        Instant finish = Instant.now();
        return new SortingResult(sortedList, Duration.between(start, finish).toMillis());
    }

    private SortingResult insertionSort(List<Integer> unsortedList) {
        Instant start = Instant.now();
        InsertionSort insertionSort = new InsertionSort();
        List<Integer> sortedList = insertionSort.sort(unsortedList);
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
