class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        //FIXED SLIDING WINDOW BASED APPROACH
        //Window size = k

        if(arr == null) return null;
        if(k >= arr.length) return Arrays.stream(arr).boxed().collect(Collectors.toList());

        int minDiff = Math.abs(arr[0] - x);
        // int resLeft = 0;
        // int resRight = 0;

        // while(right < k) { //Window still <= k
        //     // maxDiff = Math.max(maxDiff, Math.abs(arr[right] - x));
        //     // resRight = right;
        //     ++right;
        // }
        
        //Keep sliding the window one element at a time to maintain k sized window
        // for(; right < arr.length; ++right) {
        //     int currDiff = Math.abs(arr[right] - x);

        //     if(currDiff <= maxDiff) {
        //         ++left;
        //         maxDiff = Math.max(arr[left], arr[right]);
        //     }
        // }

        //Find inflection point
        int i = 1;
        int inflectionPoint = 0;
        while(i < arr.length) {
            if(Math.abs(arr[i] - x) < minDiff) {
                minDiff = Math.abs(arr[i] - x);
                inflectionPoint = i;
            }
            ++i;
        }

        System.out.println("Inflection point: " + (i-1));

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