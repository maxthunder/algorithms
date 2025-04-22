package tree;

import dataStructures.Node;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author maxwell.stark
 * @since 4/16/25
 */
public class PrintTreeRows {
    public static void main(String[] args) {
        Node head = new Node(1,
            new Node(2, new Node(4), null), // left
            new Node(3, new Node(5), new Node(6)) // right
        );
        printLevelOrder(head);


        //      5
        //     / \
        //   12   13
        //   /  \    \
        //  7    14    2
        // /  \   /  \  / \
        //17  23 27  3  8  11
        Node root = new Node(5);
        root.left = new Node(12);
        root.right = new Node(13);

        root.left.left = new Node(7);
        root.left.right = new Node(14);

        root.right.right = new Node(2);

        root.left.left.left = new Node(17);
        root.left.left.right = new Node(23);

        root.left.right.left = new Node(27);
        root.left.right.right = new Node(3);

        root.right.right.left = new Node(8);
        root.right.right.right = new Node(11);

        System.out.println();
        printLevelOrder(root);
    }

    public static void printLevelOrder(Node head) {
        if (head != null) {
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            queue.add(null);
            while (queue.size() > 1) {
                Node node = queue.poll();

                if (node == null) {// newline
                    System.out.println();
                    queue.add(null);
                } else {
                    System.out.print(node.value + " ");
                    if (node.left != null)
                        queue.add(node.left);
                    if (node.right != null)
                        queue.add(node.right);
                }
            }
        }
    }

    /**
     * This used to indicate a new line (using maxValue since queue cannot contain null values)
     * @param node Target node
     * @return if target node is equal to new line flag value.
     */
    private static boolean isNewLine(Node node) {
        return Integer.MAX_VALUE == node.value;
    }

    private static void addNewLineFlag(Queue<Node> queue) {
        queue.add(new Node(Integer.MAX_VALUE));
    }
}
