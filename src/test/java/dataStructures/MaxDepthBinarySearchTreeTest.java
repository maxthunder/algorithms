package dataStructures;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaxDepthBinarySearchTreeTest {

    @Test
    public void singleNodeTest() {
        Node node = new Node(1);

        int depth = MaxDepthBinaryTree.recursiveDFS(node);
        assertThat("depth should be 1", depth, Is.is(1));
    }
    @Test
    public void balancedThreeNodeTest() {
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);

        int depth = MaxDepthBinaryTree.recursiveDFS(node);

        final int target = 2;
        assertThat(String.format("depth should be %d", target), depth, Is.is(target));
    }

}
