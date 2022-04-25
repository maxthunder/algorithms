package dataStructures;

import javax.swing.tree.TreeNode;

/**
 * Binary Search Tree
 *
 * I wrote this...
 */
public class BinarySearchTree {
    Node root;

    public class Node {
        int value;
        Node left;
        Node right;
        Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return "(" + value + ")";
        }
    }

    public void addNode(int value) {
        root = add(new Node(value), root);
    }

    private Node add(Node newNode, Node current) {
        if (current == null)
            return newNode;
        if (newNode.value < current.value)
            current.left = add(newNode, current.left);
        if (newNode.value > current.value)
            current.right = add(newNode, current.right);
        return current;
    }

    public boolean containsValue(int value) {
        return containsNode(value, root);
    }

    private boolean containsNode(int value, Node current) {
        if (current == null)
            return false;
        if (current.value == value)
            return true;
        if (value < current.value)
            return containsNode(value, current.left);
        return containsNode(value, current.right);
    }

    public static void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.println(node.value + " ");
            printInOrder(node.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        for (int i = 0; i < 100; i++) {

        }

        printInOrder(bst.root);
    }
}
