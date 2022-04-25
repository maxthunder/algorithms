package dataStructures;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * MaxHeap
 *
 * I wrote this...
 *
 * WARNING:
 *  <ul>
 *      <li>Heap data structure is only currently setup to a maximum of 1 million items (This can be changed)</li>
 *  </ul>
 *
 * NOTE:
 *  <ul>
 *      <li>parent = heap[current / 2]</li>
 *      <li>leftChildIndex = heap[2 * current + 1]</li>
 *      <li>rightChildIndex = heap[2 * current + 2]</li>
 *  </ul>
 */
public class MaxHeap {
    private Integer[] heap;
    private int n;
    private static final int INITIAL_ARRAY_SIZE = 1_000_000;

    public MaxHeap(Integer[] heap) {
        this.heap = heap;
        n = Math.toIntExact(Arrays.stream(heap).filter(Objects::nonNull).count());
    }

    public MaxHeap() {
        this(new Integer[INITIAL_ARRAY_SIZE]);
    }

    public void addNode(Integer value) {
        if (n == 0) {
            heap[0] = value;
        } else {
            heap[n] = value;// put new item at end of heap
            int current = n;// start with last item
            while(heap[current] > heap[parent(current)]) {// if current > parent, then swap nodes
                swapNodes(current, parent(current));
                current /= 2;// set current to next parent
            }
        }
        n++;
    }

    public Integer popMaxNode() {
        if (n == 0)
            return null;
        swapNodes(0, n - 1);// swap first and last nodes (this puts maxNode at the end of heap for easy removal)
        Integer maxNode = heap[n - 1];
        heap[n - 1] = null;
        n--;
        floatDown();// then, we need to new root node downwards until it is less than its parent, but more than either child
        return maxNode;
    }

    private void floatDown() {
        int settlingNodeIndex = 0;// start with new root (that used to be at end of heap) as settling node, or node that is floating downwards.
        boolean isSettled = false;
        for(int i = 0; i < n && !isSettled; i++) {// swap the greater of the left and right children with settling node, if at least one is greater
            Integer leftChild = heap[leftChildIndex(i)];
            Integer rightChild = heap[rightChildIndex(i)];
            Integer comparingChildIndex = null;
            if (leftChild != null && rightChild != null)
                comparingChildIndex = leftChild >= rightChild ? leftChildIndex(i) : rightChildIndex(i);
            else if (leftChild != null)
                comparingChildIndex = leftChildIndex(i);

            if (comparingChildIndex != null && heap[comparingChildIndex] > heap[settlingNodeIndex]) {
                swapNodes(comparingChildIndex, settlingNodeIndex);
                settlingNodeIndex = comparingChildIndex;
            } else {
                isSettled = true;
            }
        }
    }

    private int parent(int index) {
        return index / 2;
    }

    private int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int rightChildIndex(int index) {
        return 2 * index + 2;
    }

    private void swapNodes(int firstIndex, int secondIndex) {
        int holder = heap[firstIndex];
        heap[firstIndex] = heap[secondIndex];
        heap[secondIndex] = holder;
    }

    public String asTreeString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int j = 0;
            int offset = (int) Math.pow(2, i);
            int numItemsInRow = (int) Math.pow(2,i);
            while (j < offset && j + offset <= n) {
                sb.append(heap[j+offset-1]).append(" ");
                if (numItemsInRow == j + 1)
                    sb.append("\n");// addNode newline after last item is row
                j++;
            }
        }
        // remove newline if full and complete tree
        if (sb.length() > 0 && "\n".equals(sb.substring(sb.length() - 1)))
            sb = new StringBuilder(sb.substring(0, sb.length() - 1));
        return sb.toString();
    }

    public Integer[] asArray() {
        return Arrays.stream(heap).filter(Objects::nonNull).collect(Collectors.toList()).toArray(new Integer[n]);
    }

    public List<Integer> asList() {
        return Arrays.stream(heap).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public int size() {
        return n;
    }

    @Override
    public String toString() {
        return asTreeString();
    }
}
