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
    public boolean hasCycle(ListNode head) {
        if(null == head || head.next == null) {
            return false;
        }

        ListNode tortoise = head;
        ListNode hare = head.next;

        while(tortoise != null && hare != null && hare != tortoise) {
            tortoise = tortoise.next;
            hare = hare.next;

            if(hare != null) {
                hare = hare.next;
            }
        }

        return hare == tortoise;
    }
}

//Hare and tortoise pattern
