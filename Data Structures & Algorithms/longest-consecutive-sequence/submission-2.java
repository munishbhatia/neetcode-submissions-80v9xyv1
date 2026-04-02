class Solution {
    //Ideation 1 - Simple but TLE
    // public int longestConsecutive(int[] nums) {
    //     if(nums == null) return 0;

    //     int min = Integer.MIN_VALUE;
    //     int max = Integer.MAX_VALUE;
    //     int lcsLen = 1;

    //     final Map<Integer, int[]> unions = new HashMap<>();
    //     final Set<Integer> visited = new HashSet<>();

    //     for(int num : nums) {
    //         unions.put(num, new int[]{num, 1});
    //         min = Math.min(min, num);
    //         max = Math.max(max, num);
    //     }

    //     for(int i=max-1; i>= min; --i) {
    //         if(!unions.containsKey(i)) continue;

    //         if(unions.containsKey(i+1)) {
    //             unions.put(i, unions.get(i+1) + 1);
    //         }

    //         lcsLen = Math.max(lcsLen, unions.get(i));
    //     }

    //     return lcsLen;
    // }

    //Using Union-Find Idea - Saw hint just on the Data Structure
    public int longestConsecutive(int[] nums) {
        if(nums == null) return 0;

        if(nums.length <= 1) return nums.length;

        int lcsLen = 1;

        final Map<Integer, int[]> unions = new HashMap<>();
        final Set<Integer> visited = new HashSet<>();

        for(int num : nums) {
            unions.put(num, new int[]{num, 1});
        }

        for(int num : nums) {
            // System.out.println("Processing " + num);

            if(visited.contains(num)) continue;

            visited.add(num);
            // System.out.println("Added " + num);

            int left = num;
            int right = num;

            while(unions.containsKey(right+1)) {
                visited.add(right+1);
                merge(unions, right, right+1);
                ++right;
            }

            while(unions.containsKey(left-1)) {
                visited.add(left-1);
                merge(unions, left-1, left);
                --left;
            }

            lcsLen = Math.max(lcsLen, unions.get(getParent(unions, num))[1]);
        }

        return lcsLen;
    }

    private int getParent(Map<Integer, int[]> unions, int key) {
        int next = key;
        while(unions.get(next)[0] != next) {
            next = unions.get(next)[0];
        }

        // System.out.println(String.format("Parent of %d: %d ", key, unions.get(next)[0]));
        return unions.get(next)[0];
    }

    private void merge(Map<Integer, int[]> unions, int smallerKey, int largerKey) {
        // System.out.println("Merging " + smallerKey + " and " + largerKey);
        int parentOfLargerKey = getParent(unions, largerKey);
        int[] info = unions.get(parentOfLargerKey);

        // System.out.println("Parent's Info: " + Arrays.toString(info));

        // System.out.println("Setting Info: " + Arrays.toString(new int[]{smallerKey, info[1]}));
        unions.put(parentOfLargerKey, new int[]{smallerKey, info[1]});

        unions.get(getParent(unions, smallerKey))[1] += info[1];

        // incrementChainLengthToParent(unions, largerKey);
        // System.out.println("Updated Chain Len of " + getParent(unions, smallerKey) + " : " + unions.get(getParent(unions, smallerKey))[1]);
    }

//     private void incrementChainLengthToParent(Map<Integer, int[]> unions, int key) {
//         int incrBy = unions.get(key)[1];
//         int prev = unions.get(key)[0];
//         int curr = unions.get(key)[0]; //Start at the key's parent and incr the chain length at each node by the length of chain at key

//         do {
//             unions.get(curr)[1] = unions.get(curr)[1] + incrBy;
//             prev = curr;
//             curr = unions.get(curr)[0];
//         } while(prev != curr);
//     }
}
