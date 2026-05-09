class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n == 0) return; //nums1 already contains m valid elements
        if(m == 0) { //There are no elements in nums1, just copy nums2 to nums1 and finish
            System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        }

        int p1 = m-1; //m valid elements in nums1 will lie at indices 0 - (m-1)
        int p2 = n-1; //n valid elements in nums2 will lie at indices 0 - (n-1)
        int index = (m+n-1);

        while(p1 >= 0 && p2 >= 0) {
            if(nums1[p1] > nums2[p2]) {
                nums1[index--] = nums1[p1--];
            } else {
                nums1[index--] = nums2[p2--];
            }
        }

        while(p1 >= 0) {
            nums1[index--] = nums1[p1--];
        }

        while(p2 >= 0) {
            nums1[index--] = nums2[p2--];
        }
    }
}