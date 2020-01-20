import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.hamcrest.core.Is.is;

/**
 * @author Maxwell Stark
 */
public class SortingTest extends AlgorithmTesting {
    private void printResults(Map<SortingAlgorithm, SortingResult> results) {
        results.forEach((key, value) -> {
            if (value.resultList.equals(expected))
                System.out.printf("\n%s sorted %,d integers in %,d ms.%n\n", key, runSize, value.runtime);
            Assert.assertThat("List sorted by <"+key.getName()+"> did not match expected list.", value.resultList, is(expected));
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

        sortingAlgorithms.put(SortingAlgorithm.QUICK_SORT, new QuickSort(unsortedList));
        sortingAlgorithms.put(SortingAlgorithm.MERGE_SORT, new MergeSort(unsortedList));
        // ...

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Instant start = Instant.now();
        List<Future<List<Integer>>> futures = executorService.invokeAll(sortingAlgorithms.values());
        for (Future<List<Integer>> future : futures) {
            List<Integer> sortedList = future.get();
            Assert.assertThat("List attempted to be sorted did not match expected list.", sortedList, is(expected));
        }
        Instant finish = Instant.now();
        System.out.printf("\nsorted using ALL algorithms %,d integers EACH in %,d ms.%n\n", runSize,
                Duration.between(start, finish).toMillis());
    }

    @Test
    public void quickSortMultiThreaded() throws InterruptedException, ExecutionException {
        List<Callable<List<Integer>>> callables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            callables.add(new QuickSort(RandomGen.numberGen(System.nanoTime(), 20)));
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
}
