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
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = curr.next;

        while(curr != null) {
            curr.next = prev;
            prev = curr;
            curr = next;
            if(next != null) {
                next = next.next;
            }
        }
        return prev;
    }
}

//One way to approach this is to flip all the next pointers
//Make the tail as the new head
//Return the new head

// Doing this recursively would be an O(N) time, O(N) space solution
// Doing this iteratively will be O(N) time, O(1) space solution

// Iterative
// prev = null; curr = head; next = curr.next;
// curr.next = prev;
// prev = curr;
// curr = next;
// next = next.next;
// when curr == null, return prev;
