import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SortingResult {
    final List<Integer> resultList;
    final long runtime;
}
