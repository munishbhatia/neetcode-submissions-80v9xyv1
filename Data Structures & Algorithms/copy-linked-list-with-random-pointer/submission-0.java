/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        final Map<Node, Node> nodeMap = new HashMap<>();
        return copyListHelper(head, nodeMap);
    }

    private Node copyListHelper(Node curr, Map<Node, Node> nodeMap) {
        if(curr == null) {
            return null;
        }

        Node c = nodeMap.getOrDefault(curr, new Node(curr.val));
        nodeMap.put(curr, c);
        c.next = copyListHelper(curr.next, nodeMap);
        if(curr.random != null) {
            c.random = nodeMap.getOrDefault(curr.random, new Node(curr.random.val));
            nodeMap.put(curr.random, c.random);
        }
        return c;
    }
}


//Two iterations
//Create a mapping between old and new nodes
//Second iteration, set random node pointers

//Other approach, single iteration
//Create a mapping between old and new nodes
//Create the curr node copy
//If curr node/random node exists in the map, use it
//Otherwise create the node and store the mapping

//O(N) time, O(N) space