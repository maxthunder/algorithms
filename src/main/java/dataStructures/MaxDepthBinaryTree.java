package dataStructures;

public class MaxDepthBinaryTree {

    /**
     * Recursive Depth-first Searching
     * Time complexity: O(n)
     * Memory storage: O(n), worst case of O(n) can occur if tree is unbalanced.
     * @param root
     * @return
     */
    public static int recursiveDFS(Node root) {
        if (root == null)
            return 0;// should be '-1' if counting edges, whereas right now it's counting nodes
        return 1 + Math.max(recursiveDFS(root.left), recursiveDFS(root.right));
    }
}
