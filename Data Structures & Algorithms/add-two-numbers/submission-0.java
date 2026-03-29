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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }

        ListNode head = null;
        ListNode curr = null;
        
        int carry = 0;
        while(l1 != null && l2 != null) {
            int val = l1.val + l2.val + carry;
            ListNode c = new ListNode(val%10);
            carry = val/10;

            l1 = l1.next;
            l2 = l2.next;

            if(head == null) {
                head = c;
                curr = head;
                continue;
            }
            curr.next = c;
            curr = c;
        }

        while(l1 != null) {
            if(carry == 0) {
                curr.next = l1;
                break;
            }

            int val = l1.val + carry;
            ListNode c = new ListNode(val%10);
            carry = val/10;

            l1 = l1.next;
            curr.next = c;
            curr = c;
        }

        while(l2 != null) {
            if(carry == 0) {
                curr.next = l2;
                break;
            }

            int val = l2.val + carry;
            ListNode c = new ListNode(val%10);
            carry = val/10;

            l2 = l2.next;
            curr.next = c;
            curr = c;
        }

        if(carry != 0) {
            ListNode c = new ListNode(carry);
            curr.next = c;
            curr = c;
        }

        return head;
    }
}


//Take the two digits, store the sum + maintain the carry over
//If carry over remains, store it at the end in the list
//If one of the two lists remains, add the carry over and the list elements