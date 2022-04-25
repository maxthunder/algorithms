import java.util.*;

/**
 * Project 4 - First-Last Number Game
 * @author Maxwell Stark
 * @author Ali Hamodi
 */

public class ProjectFour {

    private int n; // number of rows and columns
    private List<Integer> numList;
    private List<Integer> originalNumList;
    private List<Integer> userNums;
    private List<Integer> computerNums;
    private Cell[][] chart;
    private Scanner in;

    private class Cell {
        int first;
        int second;

        Cell(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public ProjectFour() {
        buildListFromUser();
        buildChart();
        playGame();
    }

    private void printLists(boolean printRemainingList) {
        System.out.println();

        System.out.print("User's list: { ");
        userNums.stream()
                .filter(Objects::nonNull)
                .forEach(num-> System.out.print(num + " "));
        Integer sum = 0;
        for (Integer num : userNums) {
            sum += num;
        }
        System.out.println("} = " + sum);

        System.out.print("Computer's list: { ");
        computerNums.stream()
                .filter(Objects::nonNull)
                .forEach(num-> System.out.print(num + " "));
        sum = 0;
        for (Integer num : computerNums) {
            sum += num;
        }
        System.out.println("} = " + sum);

        if (printRemainingList) {
            System.out.print("Remaining list: { ");
            numList.stream()
                    .filter(Objects::nonNull)
                    .forEach(num-> System.out.print(num + " "));
            System.out.println("}");
        }

        System.out.println();
    }

    private void buildListFromUser() {
        in = new Scanner(System.in);
        System.out.println("\nHow many numbers are in the game?");
        try {
            n = in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entry must be an integer.");
            System.exit(1);
        }

        System.out.println("Enter the list of numbers from first to last, " +
                "delimited by a space or newline");
        originalNumList = new ArrayList<>();

        try {
            for (int i = 0; i < n; i++) {
                Integer val = in.nextInt();
                originalNumList.add(val);
            }
        } catch (InputMismatchException e) {
            System.out.println("Integers must be used for values.");
            System.exit(1);
        }
        in.nextLine();
    }

    private void buildChart() {
        chart = new Cell[n][n];
        // Build objects in all cells to prevent NPEs
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int firstVal;
                if (i == j) {
                    firstVal = originalNumList.get(i);
                } else {
                    firstVal = 0;
                }
                chart[i][j] = new Cell(firstVal, 0);
            }
        }

        for(int k = 2; k <= n; k++){
            for(int i = 0; i <= n - k; i++){
                int j = i + k - 1;

                Cell currentCell = chart[i][j];
                Cell opponentFirst = chart[i+1][j];
                Cell opponentLast = chart[i][j-1];

                if(originalNumList.get(i) + opponentFirst.second > opponentLast.second + originalNumList.get(j)) {
                    currentCell.first = originalNumList.get(i) + opponentFirst.second;
                    currentCell.second = opponentFirst.first;
                } else {
                    currentCell.first = originalNumList.get(j) + opponentLast.second;
                    currentCell.second = opponentLast.first;
                }
            }
        }
    }

    private void playGame() {
        int i = 0;
        int j = n-1;

        numList = new ArrayList<>(originalNumList);
        userNums = new ArrayList<>();
        computerNums = new ArrayList<>();

        System.out.print("\nThe numbers for the game: { ");
        numList.forEach(num-> System.out.print(num + " "));
        System.out.println("}\n");// newlines for aesthetics

        while (!numList.isEmpty()) {
            boolean userChoseFirst = userMakesChoice();
            Integer numberPickedIndex;
            if (userChoseFirst) {
                numberPickedIndex = 0;
                i++;
            } else {
                numberPickedIndex = numList.size() - 1;
                j--;
            }

            userNums.add(numList.get(numberPickedIndex));
            numList.remove(numList.get(numberPickedIndex));

            printLists(true);

            if (numList.size() == 1) {
                computerNums.add(numList.get(0));
                numList.remove(numList.get(0));
            } else if (computerMakesChoice(i, j)) {// computer chose first number
                i++;
            } else {
                j--;
            }

            printLists(true);
        }

        System.out.println("\n\n------- Game Completed --------");

        int userTotal = 0;
        for (Integer num : userNums) {
            userTotal += num;
        }

        int computerTotal = 0;
        for (Integer num : computerNums) {
            computerTotal += num;
        }

        printLists(false);
        System.out.println("User's total: " + userTotal);
        System.out.println("Computer's total: " + computerTotal + "\n");

        if (userTotal > computerTotal) {
            System.out.println("You have won!");
        } else if (userTotal < computerTotal) {
            System.out.println("The Computer has beat you.");
        } else {
            System.out.println("A Tie has occurred.");
        }
        System.out.println("\nExiting...");
        System.exit(0);
    }

    private boolean userMakesChoice() {
        System.out.println("Type either 'F' or 'L' to the choose the " +
                "first or last number, respectfully.");
        String userChoice = in.nextLine();
        boolean userChooseFirst;

        if (userChoice.equalsIgnoreCase("F")) {
            userChooseFirst = true;
        } else if (userChoice.equalsIgnoreCase("L")) {
            userChooseFirst = false;
        } else {
            System.out.println(userChoice + " is not an option!!!" +
                    "Choosing first number by default.\n\n");
            userChooseFirst = true;
        }
        return userChooseFirst;
    }

    private boolean computerMakesChoice(int i, int j) {
        Cell firstNumberCell = chart[i][j-1];
        Cell lastNumberCell = chart[i+1][j];
        Integer numberPickedIndex;
        boolean computerChoseFirstNumber;

        if (firstNumberCell.first >= lastNumberCell.first) {
            numberPickedIndex = 0;
            computerChoseFirstNumber = true;
        } else {
            numberPickedIndex = numList.size() - 1;
            computerChoseFirstNumber = false;
        }

        Integer numberPicked = numList.get(numberPickedIndex);
        System.out.println("Computer has chosen " + numberPicked);
        computerNums.add(numberPicked);
        numList.remove(numberPicked);

        return computerChoseFirstNumber;
    }

    public static void main(String[] args) {
        new ProjectFour();
    }
}
