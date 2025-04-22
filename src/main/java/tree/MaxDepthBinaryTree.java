package tree;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author maxwell.stark
 * @since 4/22/25
 */
public class MaxDepthBinaryTree {
        static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    static class Runner{
        public static void main(String[] args) {
            TreeNode root = new TreeNode(3);
            root.left = new TreeNode(9);
            root.right = new TreeNode(20);
            root.left.left = new TreeNode(15);
            root.right.right = new TreeNode(7);

            assert bfs(root) == 3;
            assert dfs(root) == 3;
            System.out.println("Correct");
        }
    }

    static int dfs(TreeNode root) {
        if (root == null) return 0;

        int left = 1 + dfs(root.left);
        int right = 1 + dfs(root.right);
        return Math.max(left, right);
    }

    static int bfs(TreeNode root) {
        int level = 1;

        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while (queue.size() > 1) {// size > 1 since we always add the root AND a null value
            TreeNode node = queue.poll();
            if (node == null) {// newline
                level++;
                System.out.println();
                queue.add(null);
            } else {
                System.out.print(node.val + " ");
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }

        return level;
    }
}
