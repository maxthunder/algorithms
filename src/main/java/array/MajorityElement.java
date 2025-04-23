package array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maxwell.stark
 * @since 4/22/25
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 */
public class MajorityElement {

    public static void main(String[] args) {
        int[] array = new int[]{6, 5, 5};
        int result = new Solution().majorityElement(array);
        int expected = 5;

        if (result == expected) {
            System.out.println("Success.");
        } else {
            System.out.printf("Error: %d found instead of %d", result, expected);
        }
    }

    static class Solution {
        public int majorityElement(int[] nums) {
            if (nums.length == 1) return nums[0];

            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int val = nums[i];

                if (map.containsKey(val)) {
                    map.put(val, map.get(val) + 1);
                } else {
                    map.put(val, 1);
                }

                if (map.get(val) > nums.length / 2) {
                    return val;
                }
            }

            return 0;
        }
    }
}
