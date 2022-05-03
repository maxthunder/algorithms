package dataStructures;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MaxDepthBinaryTreeTest {

    @AllArgsConstructor
    private class NodeTarget {
        Node example;
        int target;
    }

    List<NodeTarget> examples = Lists.newArrayList(example1(), example2(), example3());

    private NodeTarget example1() {
        return new NodeTarget(new Node(1), 1);
    }

    private NodeTarget example2() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        return new NodeTarget(root, 2);
    }

    private NodeTarget example3() {
        Node root = new Node(3);
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.right = new Node(5);
        root.right.left = new Node(4);
        root.right.right = new Node(6);
        root.right.right.right = new Node(7);
        return new NodeTarget(root, 4);
    }

    @Test
    public void recursiveDFSTest() {
        examples.forEach(ex ->
                assertThat(String.format("depth should be %d", ex.target),
                    MaxDepthBinaryTree.recursiveDFS(ex.example),
                        Is.is(ex.target)));
    }

//    @Test
//    public void iterativeBFSTest() {
//        examples.forEach(ex ->
//                assertThat(String.format("depth should be %d", ex.target),
//                        MaxDepthBinaryTree.iterativeBFS(ex.example),
//                            Is.is(ex.target)));
//    }

}
