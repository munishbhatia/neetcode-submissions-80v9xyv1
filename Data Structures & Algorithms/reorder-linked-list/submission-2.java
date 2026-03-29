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

/*
* Solution using additional storage (stack) to reverse the complete list
* and then interweave the two
*/
// class Solution {
//     public void reorderList(ListNode head) {
//         if(head == null) {
//             return;
//         }

//         final Stack<ListNode> stack = new Stack<>();
//         ListNode curr = head;

//         while(curr != null) {
//             stack.push(curr);
//             curr = curr.next;
//         }

//         curr = head;
//         while(curr != stack.peek() && curr.next != stack.peek()) {
//             ListNode next = curr.next;
//             ListNode popped = stack.pop();

//             curr.next = popped;
//             popped.next = next;
//             if(!stack.isEmpty()) {
//                 stack.peek().next = null;
//             }
//             curr = next;
//         }
//     }
// }

/*
* Reverse second half of the list in place
* Interweave first and second halves
*/

class Solution {
    public void reorderList(ListNode head) {
        if(head == null) {
            return;
        }

        //Find second half of the list
        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //slow.next now points to the second half of the list
        ListNode later = reverse(slow.next);
        slow.next = null; //Break the link

        // System.out.println("First Half: ");
        // ListNode p = head;
        // while(p != null) {
        //     System.out.println(p.val);
        //     p = p.next;
        // }

        // System.out.println("Later Half: ");
        // p = later;
        // while(p != null) {
        //     System.out.println(p.val);
        //     p = p.next;
        // }

        //Interweave
        ListNode curr1 = head;
        ListNode curr2 = later;

        while(curr1 != null && curr2 != null) {
            ListNode next = curr1.next;
            curr1.next = curr2;
            curr2 = curr2.next;
            curr1.next.next = next;
            curr1 = next;
        }
    }

    private ListNode reverse(ListNode head) {
        if(head == null) {
            return head;
        }

        ListNode prev = head;
        ListNode curr = prev.next;

        while(curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        head.next = null;

        return prev;
    }
}

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
// curr.next != stack.peek() for even len list