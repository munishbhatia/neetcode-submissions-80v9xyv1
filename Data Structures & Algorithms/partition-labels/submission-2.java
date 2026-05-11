class Solution {
    public List<Integer> partitionLabels(String s) {
        final Map<Character, Integer> map = new HashMap<>();

        final int len = s.length();
        final int[] parent = new int[len];

        IntStream.range(0, len).forEach(i -> parent[i] = i);

        for(int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if(!map.containsKey(c)) {
                map.put(c, i);
                continue;
            }

            //We have seen this char before
            int mergeToIndex = map.get(c);
            int curr = i;
            while(curr > mergeToIndex && parent[curr] != parent[mergeToIndex]) {
                parent[curr--] = parent[mergeToIndex];
            }
            // Arrays.fill(parent, mergeToIndex, i+1, parent[mergeToIndex]);
        }

        final List<Integer> result = new LinkedList<>();
        int p = parent[0];
        
        int i = 0;
        for(i = 0; i < len; ++i) {
            if(parent[i] == p) continue;
            //else
            result.add(i - p);
            p = parent[i];
        }
        result.add(i - p);

        return result;
    }
}
