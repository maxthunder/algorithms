import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AmazonPractice {

    class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    @Test
    public void buildTreeFromOrderings() {
        int[] preorder = {10, 5, 3, 8, 20, 15, 25};
        int[] inorder = {3, 5, 8, 10, 15, 20, 25};

        // in-order
        assertNode(buildBinaryTree(preorder, inorder));
    }

    private void assertNode(Node node) {
        assertThat(node.value, is(10));
        assertThat(node.left.value, is(5));
        assertThat(node.left.left.value, is(3));
        assertThat(node.left.right.value, is(8));
        assertThat(node.right.value, is(20));
        assertThat(node.right.left.value, is(15));
        assertThat(node.right.right.value, is(25));
    }

    private Node buildBinaryTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }

    private Node helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        // return null if at end of either list.
        if (preStart > preorder.length - 1 || inStart > inEnd)
            return null;

        // For in-order array I and pre-order array P,
        // find x to satisfy I[x] = P[0]
        Node root = new Node(preorder[preStart]);
        int inRootIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.value)
                inRootIndex = i;
        }

        root.left = helper(preStart + 1, inStart, inRootIndex - 1, preorder, inorder);
        root.right = helper(preStart + inRootIndex - inStart + 1, inRootIndex + 1, inEnd, preorder, inorder);


        return root;
    }
}
