package dataStructures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MaxDepthBinaryTree {
    @Data
    static class Node {
        Node(int value) {
            this.value = value;
        }

        int value;
        Node left;
        Node right;
    }

    public static int maxDepth(Node root) {
        if (root == null)
            return 0;// should be '-1' if counting edges, whereas right now it's counting nodes
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
