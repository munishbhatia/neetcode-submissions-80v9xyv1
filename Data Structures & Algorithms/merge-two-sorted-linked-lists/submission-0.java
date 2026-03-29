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

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) {
            return list2;
        }
        if(list2 == null) {
            return list1;
        }
        ListNode head = null;
        ListNode prev = null;
        ListNode curr = null;
        ListNode p1 = list1;
        ListNode p2 = list2;

        while(p1 != null && p2 != null) {
            if(p1.val < p2.val) {
                curr = new ListNode(p1.val);
                if(prev != null) {
                    prev.next = curr;
                }
                prev = curr;
                if(head == null) {
                    head = curr;
                }
                p1 = p1.next;
                continue;
            }

            curr = new ListNode(p2.val);
            if(prev != null) {
                prev.next = curr;
            }
            prev = curr;
            if(head == null) {
                head = curr;
            }
            p2 = p2.next;
        }

        while (p1 != null) {
            curr = new ListNode(p1.val);
            prev.next = curr;
            prev = curr;
            p1 = p1.next;
        }

        while (p2 != null) {
            curr = new ListNode(p2.val);
            prev.next = curr;
            prev = curr;
            p2 = p2.next;
        }

        return head;
    }
}