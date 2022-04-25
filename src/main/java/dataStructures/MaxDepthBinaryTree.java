package dataStructures;

public class MaxDepthBinaryTree {
    public static int recursiveDFS(Node root) {
        if (root == null)
            return 0;// should be '-1' if counting edges, whereas right now it's counting nodes
        return 1 + Math.max(recursiveDFS(root.left), recursiveDFS(root.right));
    }
}
