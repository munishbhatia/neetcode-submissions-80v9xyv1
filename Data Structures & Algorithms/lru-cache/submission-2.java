class LRUCache {

    private LinkedList<Node> list;
    private Map<Integer, Node> map;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        list = new LinkedList<>();
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        list.remove(node);
        list.add(0, node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            list.remove(map.get(key));
        }
        Node node = new Node(key, value);
        map.put(key, node);
        list.add(0, node);

        if(list.size() > capacity) {
            Node rem = list.removeLast();
            map.remove(rem.key);
        }
    }

    class Node {
        int val;
        int key;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}

//Map<Integer, LLNode> is the cache
//get --> if(map.containsKey(key)) then:
    // 1. Move the LLNode to the front of the queue/list
    // 2. Return LLNode.val
//put --> Create a node with the val
    // 1. Add the node to the front of the list
    // 2. Add the node entry to the map
    // 3. If capacity is full, remove the entry for the last node of the list
    // 4. If map already contains the key, first remove the corresponding node in the list then add the new node to the map and front of the list
