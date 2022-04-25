// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GCD
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public int generalizedGCD(int num, int[] arr)
    {
        Set<Integer> gcds = new HashSet<>();
        for (int i = 0; i < num; i++) {// i = potential gcd element
            for (int j = 0; j < num; j++) {
                if (arr[i] != arr[j]) {
                    if (arr[j] % arr[i] != 0)
                        break;
                    gcds.add(arr[i]);
                }
            }
        }
        if (gcds.isEmpty()) {// there was no gcd found between values...
            Set<Integer> collect = Arrays.stream(arr).boxed().collect(Collectors.toSet());
            if (collect.size() == 1){// case when all elements are the same.
                return arr[0];
            }
            return 1;
        }
        List<Integer> sortedGcds = gcds.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        return sortedGcds.get(0);
    }
    // METHOD SIGNATURE ENDS

    @Test
    public void tester() {
        GCD gcd = new GCD();
        int result = gcd.generalizedGCD(5, new int[]{2, 3, 4, 5, 6});
        assertThat(result, is(1));
    }
}
