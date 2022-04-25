import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Solution2 {
    public ArrayList<String> popularNToys(int numOfToys, int topToys, List<String> toys, int numQuotes, List<String> quotes) {
        Map<String, Integer> countByToy = new HashMap<>();
        for (String toy : toys) {
            countByToy.put(toy, 0);
        }

        for (Map.Entry<String, Integer> toyEntry : countByToy.entrySet()) {
            for (String quote : quotes) {
                if (quote.contains(toyEntry.getKey())) {
                    int currentCount = countByToy.get(toyEntry.getKey());
                    countByToy.put(toyEntry.getKey(), ++currentCount);
                }
            }
        }
        LinkedHashMap<String, Integer> sortedToysByValue = new LinkedHashMap<>();
        countByToy.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(entry -> sortedToysByValue.put(entry.getKey(), entry.getValue()));
        ArrayList<String> sortedToyNamesList = new ArrayList<>();
        int count = 0;
        for (Map.Entry<String, Integer> sortedEntry : sortedToysByValue.entrySet()) {
            if (count == topToys)
                break;
            sortedToyNamesList.add(sortedEntry.getKey());
            count++;
        }
        return sortedToyNamesList;
    }

    @Test
    public void tester() {
        Solution2 solution2 = new Solution2();
        ArrayList<String> strings = solution2.popularNToys(5, 2, Arrays.asList("anacell", "betacellular", "cetracellular",
//                "deltacellular", "eurocell"), 3, Arrays.asList("st adsfad asdfa anacell", "asdfasd dfdf asdf betacellular", "daf dfdaf anacell dsfadsf d"));
                "deltacellular", "eurocell"), 3, Arrays.asList("st adsfad asdfa anacell", "asdfasd dfdf asdf betacellular", "daf dfdaf betacellular dsfadsf d", "daf dfdaf anacell dsfadsf d"));

        System.out.println(strings);
    }

}
