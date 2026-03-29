class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        //Expand around inflection point to get a window size of k

        if(arr == null) return null;
        if(k >= arr.length) return Arrays.stream(arr).boxed().collect(Collectors.toList());

        int minDiff = Math.abs(arr[0] - x);
 
        //Find inflection point
        int inflectionPoint = 0;
        for(int i=1; i < arr.length; ++i) {
            if(Math.abs(arr[i] - x) < minDiff) {
                minDiff = Math.abs(arr[i] - x);
                inflectionPoint = i;
            }
        }

        //i-1 is the inflection point index
        //Expand window around it, prefer left expansion over right if the diff is the same
        int left = inflectionPoint;
        int right = left+1;
        final List<Integer> result = new LinkedList<>();
        result.addFirst(arr[left--]);

        while(result.size() < k) {
            if(left >= 0 && right < arr.length) {
                if(Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                    result.addFirst(arr[left]);
                    --left;
                    continue;
                }
                result.addLast(arr[right++]);
                continue;
            }
            if(left >= 0) {
                result.addFirst(arr[left--]);
                continue;
            }
            if(right < arr.length) {
                result.addLast(arr[right++]);
            }
        }

        return result;
    }
}