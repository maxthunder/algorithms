package dataStructures;

import org.junit.Test;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

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

    @Test
    public void removeMaxNode() {
        MaxHeap heap = new MaxHeap();
        heap.addNode(1);
        heap.addNode(2);
        heap.addNode(3);
        heap.addNode(4);
        assertThat("Heap is setup incorrectly: heap size only " + heap.size(),
                heap.size(), is(4));
        assertThat("Heap is setup incorrectly: heap array doesn't match expected array.",
                heap.asArray(), is(new Integer[]{4, 3, 1, 2}));

        System.out.println("Before removal:\n" + heap);
        int maxNode = heap.popMaxNode();
        System.out.println("After removal:\n" + heap);
        assertThat(maxNode, is(4));
        assertThat("Heap array doesn't match expected array.", heap.asArray(), is(new Integer[]{3, 2, 1}));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////

        heap = new MaxHeap();
        for (int i = 0; i < 20; i++) {
            heap.addNode(i);
        }
        assertThat("Heap is setup incorrectly: heap size only " + heap.size(),
                heap.size(), is(20));
//        assertThat("Heap is setup incorrectly: heap array doesn't match expected array.",
//                heap.asArray(), is(new Integer[]{4, 3, 1, 2}));

        System.out.println("Before removal:\n" + heap);
        maxNode = heap.popMaxNode();
        System.out.println("After removal:\n" + heap);
        assertThat(maxNode, is(4));
        assertThat("Heap array doesn't match expected array.", heap.asArray(), is(new Integer[]{3, 2, 1}));
    }

    @Test
    public void removeMe1() {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        list.add(5);
        list.add(1);
        List<Integer> newList = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        List<Integer> newList2 = list.stream().sorted().collect(Collectors.toList());
        System.out.println(list);
        System.out.println(newList);
        System.out.println(newList2);
    }

    @Test
    public void removeMe2() {
        Calendar older = new GregorianCalendar(2015, Calendar.FEBRUARY, 14);
        Calendar newer = new GregorianCalendar(2018, Calendar.FEBRUARY, 15);
        older.add(Calendar.YEAR, 3);
        System.out.println("older: " + older.getTime());
        System.out.println("newer: " + newer.getTime());
//        System.out.println(older.after(newer));
        System.out.println(older.compareTo(newer));
        //older.compareTo(newer) >= 0 means older is sameDay or +1 if older is NEWER than newer

//        long daysBetween = Duration.between(older.toInstant(), newer.toInstant()).toDays();
//        System.out.println("daysBetween: " + daysBetween);
//        System.out.println("daysBetween/365.25: " + daysBetween/365.25);
//        long yearsBetween = newer.get(Calendar.YEAR) - older.get(Calendar.YEAR);
//        for (int i = (int) yearsBetween; i > 0; i--) {
//
//        }
//        System.out.println("yearsBetween: " + daysBetween/365.25);
    }

}
