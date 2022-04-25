import java.util.*;
import java.util.stream.Collectors;

/**
 * @author maxwell.stark
 * @author ali.hamodi
 * @author donald.conyac
 */

public class Proj6 {

    private int capacity;
    private List<Item> itemList;
    private int numOfItems;
    private static int uniqueId = 0;

    private static final String EXPLORING_NODES = "\nExploring Node: ";
    private static final String LEFT_CHILD = "\tLeft Child: ";
    private static final String RIGHT_CHILD = "\tRight Child: ";
    private static final String EXPLORE_FURTHER = "\t\texplore further";
    private static final String PRUNED_TOO_HEAVY = "\t\tpruned -- too heavy";
    private static final String HIT_CAPACITY = "\t\thit capacity exactly so don’t explore further";
    private static final String NOTE_PROFIT = "\n\t\tnote achievable profit of ";

    public Proj6() {
        buildItemChartFromUser();
        printItemChart();

        run();
    }

    private class Item {
        protected final int id;
        protected final double profit;
        protected final double weight;

        public Item(int id, double profit, double weight) {
            this.id = id;
            this.profit = profit;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "id=" + id +
                    ", profit=" + profit +
                    ", weight=" + weight +
                    '}';
        }
    }

    private class Node implements Comparable<Node> {
        protected int id;
        protected double profit;
        protected double weight;
        protected int level;
        protected double bound;
        protected List<Item> itemsTaken;

        public Node() {
            this(null);
        }

        public Node(Node parentNode) {
            this.id = uniqueId++;

            if (parentNode != null) {
                this.profit = parentNode.profit;
                this.weight = parentNode.weight;
                this.level = 1 + parentNode.level;
                this.bound = parentNode.bound;
                this.itemsTaken = new ArrayList<>(parentNode.itemsTaken);
            } else {
                this.itemsTaken = new ArrayList<>();
            }
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.bound, other.bound);
        }

        public void determineBound() {
            int index = level;
            double w = weight;
            bound = profit;
            Item item = null;
            if (index < itemList.size()) {
                item = itemList.get(index);
                w += item.weight;
                bound += item.profit;
                index++;
            }
            while (index < itemList.size()) {
                if (index < itemList.size()) {
                    item = itemList.get(index);
                    if (w + item.weight > capacity)
                        break;
                    w += item.weight;
                    bound += item.profit;
                    index++;
                }
            }
            if (w < capacity && item != null) {
                bound += (capacity - w) * (item.profit / item.weight);
            }
        }

        @Override
        public String toString() {
            String taken = itemsTaken.stream()
                    .map(item -> String.valueOf(item.id))
                    .collect(Collectors.joining(","));
            return "Node{" +
                    "id=" + id +
                    ", profit=" + profit +
                    ", weight=" + weight +
                    ", level=" + level +
                    ", itemsTaken=[" + taken +
                    "], bound=" + bound +
                    "}";
        }
    }

    private class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return Double.compare(o2.bound, o1.bound);
        }
    }

    private void buildItemChartFromUser() {
        Scanner in = new Scanner(System.in);

        System.out.println("\nEnter capacity of knapsack");
        try {
            capacity = in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entry must be an integer.");
            System.exit(1);
        }

        System.out.println("\nHow many items?");
        try {
            numOfItems = in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entry must be an integer.");
            System.exit(1);
        }

        System.out.println("For each row, enter each profit followed by its weight " +
                "in order from descending profit margin , delimited by a space.");

        itemList = new ArrayList<>(numOfItems);
        for (int i = 0; i < numOfItems; i++) {
            itemList.add(new Item(i+1, in.nextDouble(), in.nextDouble()));
        }
    }

    private void run() {
        PriorityQueue<Node> unexploredNodes = new PriorityQueue<>(new NodeComparator());
        Node bestNode = new Node();

        Node rootNode = new Node();
        rootNode.determineBound();
        unexploredNodes.offer(rootNode);


        while (!unexploredNodes.isEmpty()) {
            Node nextBestNode = unexploredNodes.poll();
            System.out.println(EXPLORING_NODES + nextBestNode);
            StringBuilder leftChildString = new StringBuilder();
            StringBuilder rightChildString = new StringBuilder();

            Node excludingNode = new Node(nextBestNode);
            Node includingNode = new Node(nextBestNode);

            if (nextBestNode.bound > bestNode.profit) {
                Item currentItem = itemList.get(nextBestNode.level);
                includingNode.weight += currentItem.weight;
                includingNode.itemsTaken.add(currentItem);
                includingNode.profit += currentItem.profit;
                includingNode.determineBound();

                rightChildString.append(RIGHT_CHILD).append(includingNode).append("\n");

                if (includingNode.weight <= capacity) {
                    rightChildString.append(EXPLORE_FURTHER);


                    if (includingNode.profit > bestNode.profit) {
                        bestNode = includingNode;
                    }

                    if (includingNode.bound > bestNode.profit) {
                        rightChildString.append(NOTE_PROFIT).append(includingNode.profit).append("\n");
                        unexploredNodes.offer(includingNode);
                    }
                } else {
                    rightChildString.append(PRUNED_TOO_HEAVY);
                }

                leftChildString.append(LEFT_CHILD).append(excludingNode).append("\n");
                excludingNode.determineBound();

                leftChildString.append(EXPLORE_FURTHER);
                if (excludingNode.bound > bestNode.profit) {
                    unexploredNodes.offer(excludingNode);
                }
            } else {
                leftChildString.append(HIT_CAPACITY).append("\n");
            }

            System.out.println(leftChildString);
            System.out.println(rightChildString);
        }
        System.out.println("\n\nBEST NODE-> " + bestNode.toString());

    }

    private void printItemChart() {
        System.out.println("Item Chart:\n");
        for (int i = 0; i < numOfItems; i++) {
            System.out.println(itemList.get(i));
        }
    }

    public static void main(String[] args) {
        new Proj6();
    }
}