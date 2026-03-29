class MyHashSet {
    private List<Integer> store;

    public MyHashSet() {
        store = new ArrayList<>();
    }
    
    public void add(int key) {
        int index = Collections.binarySearch(store, key);
        if(index >= 0) return; //Key already exists in the set
        // System.out.println()
        store.add(-(index+1), key);
    }
    
    public void remove(int key) {
        if(store.isEmpty()) return;
        
        int index = Collections.binarySearch(store, key);
        if(index < 0) return; //Key does not exist in the set
        store.remove(index);
    }
    
    public boolean contains(int key) {
        if(store.isEmpty()) return false;
        return Collections.binarySearch(store, key) >= 0;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */