class Solution {
    public List<Integer> partitionLabels(String s) {
        final Map<Character, Integer> map = new HashMap<>();

        final int len = s.length();
        final int[] parent = new int[len];
        final int[] size = new int[len];

        IntStream.range(0, len).forEach(i -> parent[i] = i);
        Arrays.fill(size, 1);

        for(int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if(!map.containsKey(c)) {
                map.put(c, i);
                continue;
            }

            //We have seen this char before
            int mergeToIndex = map.get(c);
            for(int j = i; j > mergeToIndex; --j) {
                if(map.containsKey(s.charAt(j))) {
                    union(parent, size, j, mergeToIndex);
                }
            }
        }

        // System.out.println(Arrays.toString(parent));
        // System.out.println(Arrays.toString(size));
        return IntStream.range(0, len).filter(i -> parent[i] == i).map(i -> size[i]).boxed().toList();
    }

    private int find(int[] parent, int node) {
        if(parent[node] != node) {
            parent[node] = find(parent, parent[node]);
        }

        return parent[node];
    }

    private void union(int[] parent, int[] size, int node1, int node2) {
        final int p1 = find(parent, node1);
        final int p2 = find(parent, node2);

        if(p1 == p2) return;

        if(size[p1] >= size[p2]) {
            parent[p2] = p1;
            size[p1] += size[p2];
            return;
        }

        parent[p1] = p2;
        size[p2] += size[p1];
    }
}
