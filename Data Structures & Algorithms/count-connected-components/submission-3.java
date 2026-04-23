class Solution {
    public int countComponents(int n, int[][] edges) {
        if(edges == null || edges.length == 0) return n;

        final int[] parent = new int[n];
        final int[] size = new int[n];

        for(int i = 0; i < n; ++i) {
            parent[i] = i;
            size[i] = 1;
        }

        int components = n;
        for(int[] edge : edges) {
            boolean result = union(parent, size, edge[0], edge[1]);
            if(result) --components;
        }

        return components;
    }

    private int find(int[] parent, int c) {
        if(parent[c] != c) {
            parent[c] = find(parent, parent[c]);
        }

        return parent[c];
    }

    private boolean union(int[] parent, int[] size, int n1, int n2) {
        final int p1 = find(parent, n1);
        final int p2 = find(parent, n2);

        if(p1 == p2) return false;

        if(size[p1] >= size[p2]) {
            parent[p2] = p1;
            size[p1] += size[p2];
            return true;
        }

        //else
        parent[p1] = p2;
        size[p2] += size[p1];
        return true;
    }
}
