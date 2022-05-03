package array;

import com.google.common.collect.Lists;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TwoSumTest {

    @Test
    public void twoSumTest1() {
        int[] result = TwoSum.twoSum(new int[] {2,7,11,15}, 9);
        assertTrue(Lists.newArrayList(0, 1).contains(result[0]));
        assertTrue(Lists.newArrayList(0, 1).contains(result[1]));
        assertTrue(result[0] != result[1]);
    }

    @Test
    public void twoSumTest2() {
        int[] result = TwoSum.twoSum(new int[] {3,2,4}, 6);
        assertTrue(Lists.newArrayList(1, 2).contains(result[0]));
        assertTrue(Lists.newArrayList(1, 2).contains(result[1]));
        assertTrue(result[0] != result[1]);
    }

}
