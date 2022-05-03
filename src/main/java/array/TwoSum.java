package array;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexByValue = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int rem = target - nums[i];// find remainder
            if (indexByValue.containsKey(rem)) {// if rem is in map, then we have our pair to return
                return new int[] {i, indexByValue.get(rem)};
            }
            indexByValue.put(nums[i], i);// else, add the value/index to map
        }
        return new int[] {};// return in case there are no pairs
    }
}
