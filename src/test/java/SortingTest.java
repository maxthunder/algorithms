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

    @Test
    public void RunAllSortingAlgorithms() {
        long seed = 17834589345L;
        int runSize = 200_000;
        final List<Integer> unsortedList = numberGen(seed, runSize);

        List<Integer> expected = new ArrayList<>(unsortedList);
        Collections.sort(expected);

        Map<Sorting.Type, SortingResult> algorithmsWithResults = new HashMap<>();
        algorithmsWithResults.put(Sorting.Type.QUICK_SORT, quickSort(unsortedList));

        algorithmsWithResults.forEach((key, value) -> {
            System.out.printf("%s sorted %,d integers in %,d ms.%n", key.getName(), runSize, value.runtime);
            assertThat("Quick sorted list did not match expected list.", value.resultList, is(expected));
        });
    }

    private SortingResult quickSort(List<Integer> unsortedList) {
        Instant start = Instant.now();
        List<Integer> sortedList = Sorting.quickSort(unsortedList);
        Instant finish = Instant.now();
        return new SortingResult(sortedList, Duration.between(start, finish).toMillis());
    }

    private List<Integer> numberGen(long seed, int runSize) {
        List<Integer> list;
        Random rand = new Random(seed);
        list = IntStream.range(0, runSize).mapToObj(i -> rand.nextInt() % 100).collect(Collectors.toList());
        return list;
    }

    @Data
    @AllArgsConstructor
    private class SortingResult {
        final List<Integer> resultList;
        final long runtime;
    }
}
