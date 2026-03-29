class KthLargest {
    final PriorityQueue<Integer> pq;
    final int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>(k);
        
        for(int num : nums) {
            this.add(num);
        }
    }
    
    public int add(int val) {
        if(pq.size() < k) {
            pq.offer(val);
            return pq.peek();
        }
        //pq.size() == k
        if(val < pq.peek()) {
            return pq.peek();
        }
        //val >= pq.peek()
        pq.poll();
        pq.offer(val);
        return pq.peek();
    }
}


//Approach 1:
//Store all the elements in a list (doubly linked list)
//Keep track of the list tail
//Kth largest element will be kth element from the list tail
//We will always need to keep the list sorted
//Adding a new element will be O(N) time complexity - we need to keep the list sorted
//Getting Kth largest will be O(k) time complexity - linear traversal from the tail
//O(N) space for the solution
//With stream data, list can grow very large

//Approach 2:
//Since we care about only the kth largest, perhaps we can store at most k elements
//1,0,2,3
//Use min heap - default PQ in Java
//On add, if the incoming element is less than heap top, ignore the add and return top as the kth largest element
//On add, if incoming element is greater than/equal to heap root, remove heap root, add the new element, and return heap top as the kth largest element
//If min heap size is less than K, simply add the element
//If min heap size is equals to k, follow the process defined in steps above (On add,...)
//Time complexity:
//Add operation - O(log k) for a total of O(Nlogk)
//Get kth largest - O(1)
//Space Complexity - O(k)