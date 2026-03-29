class Solution {
    public int maxTurbulenceSize(int[] arr) {
        if(null == arr) return 0;

        final int len = arr.length;
        if(len == 1) return 1;

        Boolean prev = null;
        int max = 1;
        int curr = 1;
        int index = 1;

        while(index < len && arr[index] == arr[index-1]) ++index;

        if(index == len) return 1;

        // prev = arr[index] > arr[index-1];

        for(; index < len; ++index) {
            if(arr[index] == arr[index - 1]) {
                curr = 1;
                prev = null;
                continue;
            }
            
            Boolean comparison = arr[index] > arr[index - 1];
            if(comparison != prev) {
                ++curr;
                max = Math.max(max, curr);
                prev = comparison;
                // System.out.println("Ending at: " + index + "; max: " + max);
                continue;
            }

            //Else - LTS broke; Restart calculation
            curr = 2;
            prev = comparison;
        }

        return max;
    }
}