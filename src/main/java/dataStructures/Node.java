package dataStructures;

import lombok.Data;

@Data
public class Node {
    Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    int value;
    Node left;
    Node right;

    @Override
    public String toString() {
        return "(" + value + ")";
    }
}
