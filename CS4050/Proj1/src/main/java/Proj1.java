import java.util.Scanner;

/**
 * @version 1.0.0
 * @author Maxwell Stark
 */
public class Proj1 {

    private static Scanner input = new Scanner(System.in);
    private static int originalSum;
    private static int permutationCount;

    private void divideAndConquer(int n, int max, String runningSum) {
        if (n == 0) {
            System.out.println(originalSum + " = " + runningSum);
            permutationCount++;
            return;
        }

        for (int i = (n > max ? max : n); i > 0; i--) {
            String appendage = (runningSum.length() == 0)
                    ? Integer.toString(i)
                    : i + " + " + runningSum;
            divideAndConquer(n - i, i, appendage);
        }
    }

    public Proj1() {
        do {
            long startTime = System.currentTimeMillis();
            permutationCount = 0;
            System.out.print("\nEnter n = ");
            originalSum = input.nextInt();
            System.out.println("Permutations of positive integers that sum to "
                    + originalSum + ":\n");
            divideAndConquer(originalSum, originalSum, "");
            Double elapsedTime
                    = (double) (System.currentTimeMillis() - startTime) / 1000;
            System.out.println("\n--- Total # of Permutations: "
                    + permutationCount + " ---");
            System.out.println("--- Completed in " + elapsedTime
                    + " seconds ---");
            System.out.print("\nEnter 'y' to execute a new operation "
                    + "or press any other key to exit.\n");
        } while (input.next().equalsIgnoreCase("y"));

        System.out.println("exiting...");
        System.exit(0);
    }

    public static void main(String[] args) {
        new Proj1();
    }
}