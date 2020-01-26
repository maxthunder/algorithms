package dataStructures;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MaxHeapTest {

    @Test
    public void treeToString() {
        // normal case
        MaxHeap heap = new MaxHeap(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});
        heap.asTreeString();
        String expected = "1 \n" +
                "2 3 \n" +
                "4 5 6 7 \n" +
                "8 9 10 11 12 13 14 15 \n" +
                "16 ";
        assertThat(heap.asTreeString(), is(expected));

        // full and complete tree
        heap = new MaxHeap(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        heap.asTreeString();
        expected = "1 \n" +
                "2 3 \n" +
                "4 5 6 7 \n" +
                "8 9 10 11 12 13 14 15 ";
        assertThat("newline at end of string should have been removed.", heap.asTreeString(), is(expected));
    }

    @Test
    public void addNode() {
        MaxHeap heap = new MaxHeap();
        assertThat(heap.size(), is(0));

        heap.addNode(1);
        assertThat(heap.size(), is(1));
        assertThat(heap.asArray(), is(new Integer[]{1}));

        heap.addNode(2);
        assertThat(heap.size(), is(2));
        assertThat(heap.asArray(), is(new Integer[]{2, 1}));

        heap.addNode(3);
        assertThat(heap.size(), is(3));
        assertThat(heap.asArray(), is(new Integer[]{3, 2, 1}));

        heap.addNode(4);
        assertThat(heap.size(), is(4));
        assertThat(heap.asArray(), is(new Integer[]{4, 3, 1, 2}));
    }

//    @Test
//    public void removeMaxNode() {
//        MaxHeap heap = new MaxHeap();
//        heap.addNode(1);
//        heap.addNode(2);
//        heap.addNode(3);
//        heap.addNode(4);
//        assertThat("Heap is setup incorrectly: heap size only " + heap.size(),
//                heap.size(), is(4));
//        assertThat("Heap is setup incorrectly: heap array doesn't match expected array.",
//                heap.asArray(), is(new Integer[]{4, 3, 1, 2}));
//
//        System.out.println("Before\n" + heap);
//        int maxNode = heap.removeMaxNode();
//        System.out.println("After\n" + heap);
//        assertThat(maxNode, is(4));
//        assertThat("Heap array doesn't match expected array.", heap.asArray(), is(new Integer[]{3, 2, 1}));
//    }

}
