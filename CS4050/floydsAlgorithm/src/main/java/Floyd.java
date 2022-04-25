import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Project 5 - Floyd's Algorithm
 * @author Maxwell Stark
 * @author Ali Hamodi
 */

public class Floyd {

    private int n; // number of rows and columns
    private Cell[][] chart;
    Scanner in;

    private class Cell {
        private int weight;
        private int nextNode;

        public Cell(int weight) {
            this.weight = weight;
            this.nextNode = 0;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public void setNextNode(int nextNode) {
            this.nextNode = nextNode;
        }
    }

    private void displayChart(String message) {
        System.out.println("\nChart: " + message);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (chart[i][j].getWeight() == -1) {
                    System.out.print("-");
                } else {
                    System.out.print(chart[i][j].getWeight()
                            + "(" + chart[i][j].nextNode + ")");
                }
                System.out.print("\t\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Floyd floyd = new Floyd();
        floyd.buildChartFromUser();
    }

    private void buildChartFromUser() {
        in = new Scanner(System.in);
        System.out.println("\nHow many rows/columns?");
        try {
            n = in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entry must be an integer.");
            System.exit(1);
        }

        System.out.println("For each row, enter a weight for each cell," +
                " delimited by a space.");
        chart = new Cell[n][n];
        try {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    chart[i][j] = new Cell(in.nextInt());
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Integers must be used for values.");
            System.exit(1);
        }

        displayChart("D0 ->");
        performAlgorithm();
    }


    private void performAlgorithm() {
        for (int specialNum = 0; specialNum < n; specialNum++) {
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    Cell currentCell = chart[r][c];

                    int currentWeight = getWeight(currentCell);
                    int specialRowWeight = getWeight(chart[specialNum][c]);
                    int specialColumnWeight = getWeight(chart[r][specialNum]);
                    int newSum = specialRowWeight + specialColumnWeight;

                    if (currentWeight != 0
                            && specialRowWeight != Integer.MAX_VALUE
                            && specialColumnWeight != Integer.MAX_VALUE
                            && r != specialNum
                            && c != specialNum
                            && currentWeight > newSum) {

                        // update chart with new sum
                        currentCell.setWeight(newSum);
                        currentCell.setNextNode(specialNum+1);
                    }
                }
            }
            displayChart("D" + (specialNum+1) + " ->");
        }
    }

    private int getWeight(Cell cell) {
        return cell.getWeight() == -1
                ? Integer.MAX_VALUE
                : cell.getWeight();
    }
}
