package dataStructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal {

    /**
     * BFS
     * Time complexity: O(n)
     * Memory complexity: O(n)
     * @param root
     * @return
     */
    public List<List<Integer>> bfs(Node root) {
        List<List<Integer>> traversalList = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int qSize = queue.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < qSize; i++) {
                root = queue.remove();
                if (root != null) {
                    subList.add(root.value);
                    if (root.left != null)
                        queue.add(root.left);
                    if (root.right != null)
                        queue.add(root.right);
                }
            }
            if (!subList.isEmpty())
                traversalList.add(subList);
        }
        return traversalList;
    }
}
