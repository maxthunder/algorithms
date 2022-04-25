// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM

import org.junit.Test;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public List<Integer> cellCompete(int[] states, int days)
    {
        if (days == 0)
            return Arrays.stream(states).boxed().collect(Collectors.toList());
        int[][] newStates = new int[days][8];
        for (int day = 0; day < days; day++) {
            for (int current = 0; current < 8; current++) {
                if (current == 0) {// first cell
                    if (states[current + 1] == 0)
                        newStates[day][current] = 0;
                    else {
                        newStates[day][current] = 1;
                    }
                } else if (current == 7) {// last cell
                    if (states[current - 1] == 0)
                        newStates[day][current] = 0;
                    else {
                        newStates[day][current] = 1;
                    }
                } else {// middle cells
                    if (states[current - 1] == states[current + 1])
                        newStates[day][current] = 0;
                    else
                        newStates[day][current] = 1;
                }
            }
        }
        return Arrays.stream(newStates[days - 1]).boxed().collect(Collectors.toList());
    }
    // METHOD SIGNATURE ENDS

    @Test
    public void tester() {
        Solution solution = new Solution();
//        System.out.println(solution.cellCompete(new int[]{1,0,0,0,0,1,0,0}, 1));
        System.out.println(solution.cellCompete(new int[]{1,1,1,0,1,1,1,1}, 2));
    }

    @Test
    public void largeArrayTest() {
        int i = Integer.MAX_VALUE / 100;
        int[] ints = new int[i];
    }
}
