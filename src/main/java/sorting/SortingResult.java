package sorting;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SortingResult {
    final SortingAlgorithm sortingAlgorithm;
    final List<Integer> resultList;
    final long runtime;
}
