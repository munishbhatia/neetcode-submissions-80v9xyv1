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
    public void reorderList(ListNode head) {
        if(head == null) {
            return;
        }

        final Stack<ListNode> stack = new Stack<>();
        ListNode curr = head;

        while(curr != null) {
            stack.push(curr);
            curr = curr.next;
        }

        curr = head;
        while(curr != stack.peek() && curr.next != stack.peek()) {
            ListNode next = curr.next;
            ListNode popped = stack.pop();

            curr.next = popped;
            popped.next = next;
            if(!stack.isEmpty()) {
                stack.peek().next = null;
            }
            curr = next;
        }
    }
}

// 2,4,6,8
// 2,8,4,6

// Traversal from tail would mean we'd have to keep doing O(N) iterations to get to the tail every single time making the algo O(N^2)
// Push it in stack
// traverse from start
// Save curr, next = curr.next
// popped = pop
// popped.next = next;
// curr.next = popped;
// stack.peek().next = null;
// curr = next;
// next = curr.next;
// O(N) time; O(N) space
// Stopping criteria --> stack.peek() == curr for odd len list
// curr = null for even len list