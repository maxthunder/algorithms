package dataStructures;

public class SubArray {
    public static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currSum = 0;
        for(int i = 0; i < nums.length; i++) {
            // reset currSum if negative
            if (currSum < 0)
                currSum = 0;
            currSum += nums[i];
            maxSum = Math.max(currSum, maxSum);
        }
        return maxSum;
    }
}
