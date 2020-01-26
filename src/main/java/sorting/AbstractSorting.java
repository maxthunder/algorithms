package sorting;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public abstract class AbstractSorting implements ISorting, Callable<SortingResult> {
    SortingAlgorithm algorithmType;
    List<Integer> integerList;

    AbstractSorting(SortingAlgorithm algorithmType, List<Integer> integerList) {
        this.algorithmType = algorithmType;
        this.integerList = integerList;
    }

    @Override
    public SortingResult call() {
        Instant start = Instant.now();
        List<Integer> sortedList = sort();
        Instant finish = Instant.now();
        return new SortingResult(algorithmType, sortedList, Duration.between(start, finish).toMillis());
    }

    @Override
    public abstract List<Integer> sort();

    static List<Integer> getBaseCases(List<Integer> list) {
        if (list == null || list.isEmpty())
            throw new IllegalStateException("Input list must be non-null and non-empty.");
        List<Integer> inputList = new ArrayList<>(list);
        if (inputList.size() == 1)
            return inputList;
        if (inputList.size() == 2) {
            if (inputList.get(0) > inputList.get(1))
                Collections.reverse(inputList);
            return inputList;
        }
        return null;
    }
}
