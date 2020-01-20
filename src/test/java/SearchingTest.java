import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SearchingTest extends AlgorithmTesting {

    BinarySearchTree bst;

    @Before
    public void setup() {
        bst = new BinarySearchTree();
        bst.addNode(50);
        bst.addNode(42);
        bst.addNode(43);
        bst.addNode(76);
        bst.addNode(31);
        bst.addNode(55);
        bst.addNode(49);
    }

    @Test
    public void binarySearchTree_containsValue() {
        assertThat(bst.containsValue(76), is(true));
        assertThat(bst.containsValue(31), is(true));
        assertThat(bst.containsValue(41), is(false));
    }

    @Test
    public void binarySearchTree_addNode_treeSkewedLeft() {
        bst = new BinarySearchTree();
        bst.addNode(1);
        bst.addNode(2);
        bst.addNode(3);

        assertThat(bst.root.left, is(nullValue()));
        assertThat(bst.root.value, is(1));
        assertThat(bst.root.right.value, is(2));
        assertThat(bst.root.right.right.value, is(3));
    }

    @Test
    public void binarySearchTree_addNode_treeBalanced() {
        bst = new BinarySearchTree();
        bst.addNode(2);
        bst.addNode(1);
        bst.addNode(3);

        assertThat(bst.root.left.value, is(1));
        assertThat(bst.root.value, is(2));
        assertThat(bst.root.right.value, is(3));
    }

    @Test
    public void binarySearchTree_addNode_treeSkewedRight() {
        bst = new BinarySearchTree();
        bst.addNode(3);
        bst.addNode(2);
        bst.addNode(1);

        assertThat(bst.root.left.left.value, is(1));
        assertThat(bst.root.left.value, is(2));
        assertThat(bst.root.value, is(3));
        assertThat(bst.root.right, is(nullValue()));
    }
}
