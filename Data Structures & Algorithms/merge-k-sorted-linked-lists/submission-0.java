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
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;

        if(lists == null || lists.length == 0) {
            return null;
        }

        final PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a.val, b.val));
        //add one element from each list to the heap
        for(int i=0; i<lists.length; ++i) {
            if(lists[i] != null) {
                minHeap.offer(lists[i]);
            }
        }

        while(!minHeap.isEmpty()) {
            ListNode curr = minHeap.poll();
            dummy.next = curr;
            dummy = curr;

            if(curr.next != null) {
                minHeap.offer(curr.next);
            }
        }
        return head.next;
    }
}

//Naive approach
//Run a loop over the k lists
//Get the smallest element and add it to the result list and increment the pointer on that list
//O(mk) time, O(1) space operation where m = length of the longest list

//Using minHeap
//Create a minHeap of size k
//Add k nodes/elements to the heap (node contains val and which list the node belongs to)
//Remove the top/min node from the heap, add it to the list and add the next element
//from the list to which the node belonged from to the heap
//O(mlgk) time, O(k) space
