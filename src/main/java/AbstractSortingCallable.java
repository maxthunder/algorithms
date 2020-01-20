import org.apache.commons.collections4.CollectionUtils;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public abstract class AbstractSortingCallable implements ISorting, Callable<SortingResult> {
    SortingAlgorithm algorithmType;
    List<Integer> integerList;

    AbstractSortingCallable(SortingAlgorithm algorithmType, List<Integer> integerList) {
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
        List<Integer> inputList = new ArrayList<>(list);
        if (inputList.isEmpty())
            throw new IllegalStateException("Input list must be non-empty.");
        if (inputList.size() == 1)
            return inputList;
        if (inputList.size() == 2) {
            if (inputList.get(0) > inputList.get(1))
                Collections.reverse(inputList);
            return inputList;
        }
        return null;
    }

    void validate() {
        if (CollectionUtils.isEmpty(integerList))
            throw new IllegalStateException("list cannot be empty. Set list in constructor");
    }
}
