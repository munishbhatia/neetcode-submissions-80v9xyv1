class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        final int nodes = edges.length;
        final int[] parent = new int[nodes + 1];
        final int[] size = new int[nodes + 1];

        for(int i = 0; i <= nodes; ++i) {
            parent[i] = i; 
            size[i] = 1;
        }

        int components = nodes;
        for(int[] edge : edges) {
            if(components == 1) return edge;
            if(!union(parent, size, edge[0], edge[1])) return edge;

            --components; //we unioned successfully above
        }

        return new int[0]; //Should never reach here for a valid problem
    }

    private int find(int[] parent, int node) {
        if(parent[node] != node) {
            parent[node] = find(parent, parent[node]);
        }

        return parent[node];
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
