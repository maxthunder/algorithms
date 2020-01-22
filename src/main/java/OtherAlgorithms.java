import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * These solutions are in response to problem from codingbat.com...
 */
public class OtherAlgorithms {

    private OtherAlgorithms() {
        throw new AssertionError();// Used to prevent constructor instantiation in utility class.
    }

    public static int countClumps(int[] nums) {
        int clumpCount = 0;
        Integer last = null;
        for(int i = 0; i < nums.length - 1; i++) {
            if(nums[i] == nums[i + 1]) {
                if ((last == null || last != nums[i])) {
                    clumpCount++;
                    last = nums[i];
                }
            } else {
                last = null;
            }
        }
        return clumpCount;
    }

    public static int[] seriesUp(int n) {
        List<Integer> nums = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            nums.addAll(Arrays.asList(countUp(i+1)));
        }
        return nums.stream().mapToInt(i->i).toArray();
    }

    private static Integer[] countUp(int n) {
        Integer[] holder = new Integer[n];
        for(int i = 0; i < n; i++) {
            holder[i] = i + 1;
        }
        return holder;
    }
}
