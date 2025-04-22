package dataStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author maxwell.stark
 * @since 4/17/25
 */
public class LinkedListReversal {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode prev = null;
        ListNode curr = head;
        ListNode next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public static void main(String[] args) {
//        ListNode root =
//                new ListNode(1,
//                new ListNode(2,
//                new ListNode(3,
//                new ListNode(4,
//                new ListNode(5)))));
//
//        ListNode result = reverseList(root);

        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};

        int[][] result = mergeIntervals(intervals);
        System.out.println(result);
    }

    public static int[][] mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> merged = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];
            int[] prev = intervals[i - 1];

            if (prev[1] > curr[0] && prev[1] <= curr[1]) {// overlaps
                merged.add(new int[]{prev[0], curr[1]});
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

