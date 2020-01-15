
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractSorting implements ISorting {
    @Override
    public abstract List<Integer> sort(List<Integer> unsortedList);

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
}
