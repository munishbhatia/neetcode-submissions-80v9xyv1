/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    
    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
*/

class Solution {
    public Node construct(int[][] grid) {
        if(grid == null || grid.length == 0 || grid.length != grid[0].length) return null; //Bad input

        return constructQuadTree(grid, 0, 0, grid.length);
    }

    public Node constructQuadTree(int[][] grid, int rowId, int colId, int size) {
        // System.out.println(String.format("Constructing QT for rowId: %d, colId: %d, size: %d", rowId, colId, size));

        if(size == 1) {
            return new Node(grid[rowId][colId] == 1 ? true : false, true);
        }

        int val = grid[rowId][colId];
        boolean sameValue = true; //We could also name this "isLeaf"

        for(int r = rowId; r < rowId + size && sameValue; r++) {
            for(int c = colId; c < colId + size && sameValue; c++) {
                if(grid[r][c] != val) sameValue = false;
            }
        }

        final Node curr = new Node(val == 1 ? true : false, sameValue);
        if(sameValue) return curr; //This check could also have been if(curr.isLeaf) return curr;

        curr.topLeft = constructQuadTree(grid, rowId, colId, size/2);
        curr.topRight = constructQuadTree(grid, rowId, colId + size/2, size/2);
        curr.bottomLeft = constructQuadTree(grid, rowId + size/2, colId, size/2);
        curr.bottomRight = constructQuadTree(grid, rowId + size/2, colId + size/2, size/2);

        return curr;
    }
}