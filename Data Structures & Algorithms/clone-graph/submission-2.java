/*
Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return null;

        // final Map<Integer, Node> valCloneNodeMap = new HashMap<>();
        // final Queue<Node> queue = new LinkedList<>();
        // queue.offer(node);

        // Node cloneEntry = null;

        // while(!queue.isEmpty()) {
        //     Node original = queue.poll();
        //     valCloneNodeMap.putIfAbsent(original.val, new Node(original.val));

        //     Node clone = valCloneNodeMap.get(original.val);
        //     if(cloneEntry == null) cloneEntry = clone;

        //     for(Node neighbour : original.neighbors) {
        //         if(!valCloneNodeMap.containsKey(neighbour.val)) { //We haven't seen this node so far
        //             queue.offer(neighbour);
        //         }

        //         valCloneNodeMap.putIfAbsent(neighbour.val, new Node(neighbour.val));
        //         clone.neighbors.add(valCloneNodeMap.get(neighbour.val));
        //     }
        // }

        // return cloneEntry;

        return graphCloneHelper(node, new HashMap<>());
    }

    private Node graphCloneHelper(Node node, Map<Integer, Node> valCloneNodeMap) {
        if(node == null) return null;

        if(valCloneNodeMap.containsKey(node.val)) return valCloneNodeMap.get(node.val);

        valCloneNodeMap.put(node.val, new Node(node.val));
        final Node clone = valCloneNodeMap.get(node.val);

        for(Node n : node.neighbors) {
            Node nclone = valCloneNodeMap.get(n.val);
            if(nclone == null) {
                nclone = graphCloneHelper(n, valCloneNodeMap);
            }

            clone.neighbors.add(nclone);
        }
        return clone;
    }
}