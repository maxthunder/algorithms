
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractSorting implements ISorting {
    public static List<Integer> integerList;
    public static SortingAlgorithm algorithmType;

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

//    static SortingResult sortingResult(SortingAlgorithm algorithmType, List<Integer> sorted) {
//
//    }


    public static void setIntegerList(List<Integer> integerList) {
        AbstractSorting.integerList = integerList;
    }

    public static SortingAlgorithm getAlgorithmType() {
        return algorithmType;
    }
}
