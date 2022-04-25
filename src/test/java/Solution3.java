// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution3
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    int minimumDays(int rows, int columns, List<List<Integer> > grid)
    {
        Integer[][] arrayGrid = new Integer[grid.size()][];
        for (int i = 0; i < grid.size(); i++) {
            arrayGrid[i] = grid.get(i).toArray(new Integer[grid.get(i).size()]);
        }
        int numServers = rows * rows;
        int numUpdated = 0;
        int dayCount = 0;
        while(numUpdated < numServers) {
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    if (arrayGrid[row][col] == 1) {
                        int updated = updateAdjacent(row, col, arrayGrid, rows, columns);
                        numUpdated += updated;
                    }
                }
            }
            dayCount++;
        }

        if (numUpdated == 0)
            return -1;

        return dayCount;
    }
    // METHOD SIGNATURE ENDS

    private int updateAdjacent(int row,
                               int col,
                               Integer[][] arrayGrid,
                               int numRows,
                               int numColumns) {
        int numUpdated = 0;
        if (row - 1 >= 0 && arrayGrid[row - 1][col] == 0) {// neighbor above
            numUpdated++;
        } if (row + 1 < numRows && arrayGrid[row + 1][col] == 0) {// neighbor below
            numUpdated++;
        } if (col - 1 >= 0 && arrayGrid[row][col - 1] == 0) {// neighbor left
            numUpdated++;
        } if (col + 1 < numColumns && arrayGrid[row][col + 1] == 0) {// neighbor right
            numUpdated++;
        }
        return numUpdated;
    }

    @Test
    public void tester() {
        Solution3 solution3 = new Solution3();
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> row1 = Lists.newArrayList(1,0,0,0,0);
        List<Integer> row2 = Lists.newArrayList(0,1,0,0,0);
        List<Integer> row3 = Lists.newArrayList(0,0,1,0,0);
        List<Integer> row4 = Lists.newArrayList(0,0,0,1,0);
        List<Integer> row5 = Lists.newArrayList(0,0,0,0,1);
        lists.add(row1);
        lists.add(row2);
        lists.add(row3);
        lists.add(row4);
        lists.add(row5);
        System.out.println(lists);
        int i = solution3.minimumDays(5, 5, lists);
        System.out.println(i);
    }
}
