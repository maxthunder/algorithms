package dataStructures;

import com.google.common.collect.Lists;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxDepthBinaryTree {

    /**
     * Recursive Depth-first Searching
     * Time complexity: O(n)
     * Memory complexity: O(n), worst case of O(n) can occur if tree is unbalanced.
     * @param root Root node.
     * @return Depth of tree (child-less root node would result in depth of 1)
     */
    public static int recursiveDFS(Node root) {
        if (root == null)
            return 0;// should be '-1' if counting edges, whereas right now it's counting nodes
        return 1 + Math.max(recursiveDFS(root.left), recursiveDFS(root.right));
    }

//    public static int iterativeBFS(Node root) {
//        if (root == null)
//            return 0;
//
//        Deque<Node> deque = new ArrayDeque<>(Lists.newArrayList(root));
//        int level = 0;
//
//        while (!deque.isEmpty()) {
//            for (int i = 0; i < deque.size(); i++) {
//                Node node = deque.removeFirst();
//                if (node.left != null)
//                    deque.addLast(node.left);
//                if (node.right != null)
//                    deque.addLast(node.right);
//            }
//            level += 1;
//        }
//        return level;
//    }
}
