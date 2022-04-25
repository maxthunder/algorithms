package dataStructures;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubArrayTest {

    @Test
    public void singleLengthArray() {
        final int target = 1;
        int[] nums = {1};
        runMaxSubArrayTest(nums, target);
    }

    @Test
    public void contiguousArray1() {
        final int target = 6;
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        runMaxSubArrayTest(nums, target);
    }

    @Test
    public void contiguousArray2() {
        final int target = 23;
        int[] nums = {5,4,-1,7,8};
        runMaxSubArrayTest(nums, target);
    }

    @Test(expected = AssertionError.class)
    public void sadPath() {
        final int target = 5;// correct result would be 6
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        runMaxSubArrayTest(nums, target);
    }

    private void runMaxSubArrayTest(int[] nums, int target) {
        int result = SubArray.maxSubArray(nums);
        assertThat(String.format("Max Sub-array should be %d, but was %d.", target, result), result, Is.is(target));

    }

}
