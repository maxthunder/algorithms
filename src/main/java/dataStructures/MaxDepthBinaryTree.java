package dataStructures;

import lombok.Data;

public class MaxDepthBinaryTree {
    public static int maxDepth(Node root) {
        if (root == null)
            return 0;// should be '-1' if counting edges, whereas right now it's counting nodes
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
