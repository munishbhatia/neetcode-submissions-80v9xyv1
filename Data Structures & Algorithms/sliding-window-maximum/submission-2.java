class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        final Deque<Integer> dq = new LinkedList<>();
        final int[] result = new int[nums.length - k + 1];
        int idx = 0;

        for(int i=0; i<k; ++i) {
            addToDeque(dq, nums[i]);
        }
        result[idx++] = dq.peekFirst();

        int left = 0;
        for(int right = k; right < nums.length; ++right) {
            removeFromDeque(dq, nums[left++]);
            addToDeque(dq, nums[right]);
            result[idx++] = dq.peekFirst();
        }
        return result;
    }

    /*
    We remove all the elements smaller than the new element (val) from the end of the queue
    We add the new element (val) to the end of the queue
    */
    private void addToDeque(Deque<Integer> dq, int val) {
        if(dq.isEmpty()) {
            dq.offerFirst(val);
            return;
        }
        while(!dq.isEmpty() && dq.peekLast() < val) {
            dq.pollLast();
        }
        dq.offerLast(val);
    }

    /*
    3. When an element goes out of the window:
    3.1 If the element is at the front of the queue, we remove it
    3.2 Otherwise continue - the element was already removed for adding a greater element
    */
    private void removeFromDeque(Deque<Integer> dq, int val) {
        if(dq.isEmpty()) {
            return;
        }

        if(dq.peekFirst() == val) {
            dq.pollFirst();
            return;
        }
    }
}

// 1
// 2,1
// 2,1,1
// 4,1,0
// 4,2
// 6,4,2

// 1
// 2
// 2,1
// 1,0,-1


// 1. Take a double ended queue (Deque)
// 2. Put elements in the queue such that:
//     2.1 When we see an element larger than the front of the queue, we put the new element
//         at the start of the queue
//     2.2 If we see an element smaller than the front of the queue, we remove all the elements
//         smaller than the current element from the rear end of the queue and push the new
//         element to the rear of the queue
// 3. When an element goes out of the window:
//     3.1 If the element is at the front of the queue, we remove it
//     3.2 Otherwise remove all the elements from the rear end of the queue including the 
//         element going out of the window
