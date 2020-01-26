import org.junit.Test;
import sorting.*;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Maxwell Stark
 */
public class SortingTest extends AlgorithmTesting {
    private void assertResults(Map<SortingAlgorithm, SortingResult> results) {
//        System.out.println("[worstCase, averageCase, bestCase]");
        System.out.println("SORTING RESULTS...\n");
        results.forEach((sortingAlgorithm, result) -> {
            String algorithm = sortingAlgorithm.getName();
            if (result.getResultList().equals(expected))
                System.out.printf("%s sorted %,d integers in %,d ms.%n", algorithm, runSize, result.getRuntime());
            assertThat("List sorted by <"+algorithm+"> did not match expected list.", result.getResultList(), is(expected));
        });
    }

    @Test
    public void RunAllSortingAlgorithms_MultiThreaded() throws InterruptedException, ExecutionException {
        Map<SortingAlgorithm, AbstractSorting> callables = new LinkedHashMap<>();
        List<SortingResult> resultList = new ArrayList<>();

        callables.put(SortingAlgorithm.QUICK_SORT, new QuickSort(unsortedList));
        callables.put(SortingAlgorithm.MERGE_SORT, new MergeSort(unsortedList));
        callables.put(SortingAlgorithm.BUBBLE_SORT, new BubbleSort(unsortedList));
        callables.put(SortingAlgorithm.SELECTION_SORT, new SelectionSort(unsortedList));
        callables.put(SortingAlgorithm.INSERTION_SORT, new InsertionSort(unsortedList));

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<SortingResult>> futures = executorService.invokeAll(callables.values());
        for (Future<SortingResult> future : futures) {
            resultList.add(future.get());
        }
        assertResults(resultList.stream().collect(Collectors.toMap(SortingResult::getSortingAlgorithm, v -> v)));
    }

    @Test
    public void RunAllSortingAlgorithms_SingleThreaded() {
        Map<SortingAlgorithm, SortingResult> algorithmsWithResults = new LinkedHashMap<>();
        algorithmsWithResults.put(SortingAlgorithm.QUICK_SORT, quickSort(unsortedList));
        algorithmsWithResults.put(SortingAlgorithm.MERGE_SORT, mergeSort(unsortedList));
        algorithmsWithResults.put(SortingAlgorithm.BUBBLE_SORT, bubbleSort(unsortedList));
        algorithmsWithResults.put(SortingAlgorithm.SELECTION_SORT, selectionSort(unsortedList));
        algorithmsWithResults.put(SortingAlgorithm.INSERTION_SORT, insertionSort(unsortedList));
        assertResults(algorithmsWithResults);
    }

    private SortingResult quickSort(List<Integer> unsortedList) {
        Instant start = Instant.now();
        QuickSort quickSort = new QuickSort(unsortedList);
        List<Integer> sortedList = quickSort.sort();
        Instant finish = Instant.now();
        return new SortingResult(SortingAlgorithm.QUICK_SORT, sortedList, Duration.between(start, finish).toMillis());
    }

    private SortingResult mergeSort(List<Integer> unsortedList) {
        Instant start = Instant.now();
        MergeSort mergeSort = new MergeSort(unsortedList);
        List<Integer> sortedList = mergeSort.sort();
        Instant finish = Instant.now();
        return new SortingResult(SortingAlgorithm.MERGE_SORT, sortedList, Duration.between(start, finish).toMillis());
    }

    private SortingResult bubbleSort(List<Integer> unsortedList) {
        Instant start = Instant.now();
        BubbleSort bubbleSort = new BubbleSort(unsortedList);
        List<Integer> sortedList = bubbleSort.sort();
        Instant finish = Instant.now();
        return new SortingResult(SortingAlgorithm.BUBBLE_SORT, sortedList, Duration.between(start, finish).toMillis());
    }

    private SortingResult selectionSort(List<Integer> unsortedList) {
        Instant start = Instant.now();
        SelectionSort selectionSort = new SelectionSort(unsortedList);
        List<Integer> sortedList = selectionSort.sort();
        Instant finish = Instant.now();
        return new SortingResult(SortingAlgorithm.SELECTION_SORT, sortedList, Duration.between(start, finish).toMillis());
    }

    private SortingResult insertionSort(List<Integer> unsortedList) {
        Instant start = Instant.now();
        InsertionSort insertionSort = new InsertionSort(unsortedList);
        List<Integer> sortedList = insertionSort.sort();
        Instant finish = Instant.now();
        return new SortingResult(SortingAlgorithm.INSERTION_SORT, sortedList, Duration.between(start, finish).toMillis());
    }
}
