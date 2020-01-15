import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Maxwell Stark
 */
public class SortingTest {



    private final static long seed = System.nanoTime();
    private final static int runSize = 40_000;
    private static List<Integer> unsortedList;
    private static List<Integer> expected;

    @Before
    public void setup() {
        unsortedList = Collections.unmodifiableList(numberGen(seed, runSize));
        expected = numberGen(seed, runSize);
        Collections.sort(expected);
    }

    private void printResults( Map<SortingAlgorithm, SortingResult> results) {
        results.forEach((key, value) -> {
            if (value.resultList.equals(expected))
                System.out.printf("\n%s sorted %,d integers in %,d ms.%n\n", key, runSize, value.runtime);
            assertThat("List sorted by <"+key.getName()+"> did not match expected list.", value.resultList, is(expected));
        });
    }

    @Test
    public void RunAllSortingAlgorithms_SingleThreaded() {
        Map<SortingAlgorithm, SortingResult> algorithmsWithResults = new LinkedHashMap<>();
        algorithmsWithResults.put(SortingAlgorithm.QUICK_SORT, quickSort(unsortedList));
        algorithmsWithResults.put(SortingAlgorithm.MERGE_SORT, mergeSort(unsortedList));
        algorithmsWithResults.put(SortingAlgorithm.BUBBLE_SORT, bubbleSort(unsortedList));
        algorithmsWithResults.put(SortingAlgorithm.SELECTION_SORT, selectionSort(unsortedList));
        algorithmsWithResults.put(SortingAlgorithm.INSERTION_SORT, insertionSort(unsortedList));
        printResults(algorithmsWithResults);
    }

    @Test
    public void RunAllSortingAlgorithms_MultiThreaded() throws InterruptedException, ExecutionException {
        Map<SortingAlgorithm, Callable<List<Integer>>> sortingAlgorithms = new LinkedHashMap<>();
        Map<SortingAlgorithm, List<Integer>> sortingResults = new LinkedHashMap<>();

        sortingAlgorithms.put(SortingAlgorithm.QUICK_SORT, new QuickSort(unsortedList));

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<List<Integer>>> futures = executorService.invokeAll(sortingAlgorithms.values());
        for (Future<List<Integer>> future : futures) {
            List<Integer> sortedList = future.get();
            assertThat("List attempted to be sorted did not match expected list.", sortedList, is(expected));
        }
    }

    @Test
    public void quickSortMultiThreaded() throws InterruptedException, ExecutionException {
        List<Callable<List<Integer>>> callables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            callables.add(new QuickSort(numberGen(System.nanoTime(), 20)));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<List<Integer>>> futures = executorService.invokeAll(callables);
        for (Future<List<Integer>> future : futures) {
            System.out.println(future.get());
        }
    }

    private SortingResult quickSort(List<Integer> unsortedList) {
        Instant start = Instant.now();
        QuickSort quickSort = new QuickSort(unsortedList);
        List<Integer> sortedList = quickSort.sort();
        Instant finish = Instant.now();
        return new SortingResult(sortedList, Duration.between(start, finish).toMillis());
    }

    private SortingResult mergeSort(List<Integer> unsortedList) {
        Instant start = Instant.now();
        MergeSort mergeSort = new MergeSort(unsortedList);
        List<Integer> sortedList = mergeSort.sort();
        Instant finish = Instant.now();
        return new SortingResult(sortedList, Duration.between(start, finish).toMillis());
    }

    private SortingResult bubbleSort(List<Integer> unsortedList) {
        Instant start = Instant.now();
        BubbleSort bubbleSort = new BubbleSort(unsortedList);
        List<Integer> sortedList = bubbleSort.sort();
        Instant finish = Instant.now();
        return new SortingResult(sortedList, Duration.between(start, finish).toMillis());
    }

    private SortingResult selectionSort(List<Integer> unsortedList) {
        Instant start = Instant.now();
        SelectionSort selectionSort = new SelectionSort(unsortedList);
        List<Integer> sortedList = selectionSort.sort();
        Instant finish = Instant.now();
        return new SortingResult(sortedList, Duration.between(start, finish).toMillis());
    }

    private SortingResult insertionSort(List<Integer> unsortedList) {
        Instant start = Instant.now();
        InsertionSort insertionSort = new InsertionSort(unsortedList);
        List<Integer> sortedList = insertionSort.sort();
        Instant finish = Instant.now();
        return new SortingResult(sortedList, Duration.between(start, finish).toMillis());
    }

    private List<Integer> numberGen(long seed, int runSize) {
        List<Integer> list;
        Random rand = new Random(seed);
        list = IntStream.range(0, runSize).mapToObj(i -> Math.abs(rand.nextInt() % 100)).collect(Collectors.toList());
        return list;
    }
}
