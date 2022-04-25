package dataStructures;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaxDepthBinaryTreeTest {

    @Test
    public void singleNodeTest() {
        MaxDepthBinaryTree.Node node = new MaxDepthBinaryTree.Node(1);

        int depth = MaxDepthBinaryTree.maxDepth(node);
        assertThat("depth should be 1", depth, Is.is(1));
    }
    @Test
    public void balancedThreeNodeTest() {
        MaxDepthBinaryTree.Node node = new MaxDepthBinaryTree.Node(1);
        node.left = new MaxDepthBinaryTree.Node(2);
        node.right = new MaxDepthBinaryTree.Node(3);

        int depth = MaxDepthBinaryTree.maxDepth(node);

        final int target = 2;
        assertThat(String.format("depth should be %d", target), depth, Is.is(target));
    }

}
