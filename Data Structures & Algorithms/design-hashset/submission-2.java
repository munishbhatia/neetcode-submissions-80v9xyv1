class MyHashSet {
    private List<List<Integer>> buckets;
    private final int NUM_BUCKETS = 1000;

    public MyHashSet() {
        buckets = new ArrayList<>(NUM_BUCKETS);

        int i = 0;
        while(i++ < NUM_BUCKETS) {
            buckets.add(new ArrayList<>());
        }
    }
    
    public void add(int key) {
        if(contains(key)) return;
        
        int bucketId = getBucketId(key);
        List<Integer> bucket = buckets.get(bucketId);
        int index = Collections.binarySearch(bucket, key);
        bucket.add(-(index+1), key);
    }
    
    public void remove(int key) {
        if(!contains(key)) return; //Key does not exist, nothing to do, return
        
        List<Integer> bucket = buckets.get(getBucketId(key));
        int index = Collections.binarySearch(bucket, key);
        bucket.remove(index);
    }
    
    public boolean contains(int key) {
        final int bucketId = getBucketId(key);
        final List<Integer> bucket = buckets.get(bucketId);
        if(bucket.isEmpty()) return false;
        
        return Collections.binarySearch(bucket, key) >= 0;
    }

    private int getHashCode(int key) {
        return Integer.hashCode(key);
    }

    private int getBucketId(int key) {
        return getHashCode(key) % NUM_BUCKETS;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */