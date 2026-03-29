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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) {
            return head;
        }

        ListNode hare = head;
        ListNode tortoise = head;

        while(n-- > 0 && hare != null) {
            hare = hare.next;
        }

        if(n > 0) {
            throw new RuntimeException("Insufficient list length");
        }

        while(hare != null && hare.next != null) {
            hare = hare.next;
            tortoise = tortoise.next;
        }

        if(hare == null) {
            return head.next;
        }
        tortoise.next = tortoise.next.next;
        return head;
    }
}

//Hare and tortoise pattern
//Lead the hare by n+1
//Then follow tortoise till hare reaches the last node of the list
//Remove the tortoise node.next
