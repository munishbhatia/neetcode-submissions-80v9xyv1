class Solution {
    private final int[][] neighbours = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;

        if(grid == null || grid.length == 0 || grid[0].length == 0) return perimeter; //0

        final int rows = grid.length;
        final int cols = grid[0].length;

        for(int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if(grid[i][j] == 0) continue;

                //Add 1 to the perimeter for graph boundary blocks
                if(i == 0) perimeter += 1;
                if(i == rows - 1) perimeter += 1;
                if(j == 0) perimeter += 1;
                if(j == cols - 1) perimeter +=1;

                //Traverse neighbours and add 1 to the perimeter for every boundary that touches water
                for(int[] neighbour : neighbours) {
                    int nr = i + neighbour[0];
                    int nc = j + neighbour[1];

                    if(nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue; //We moved out of the graph boundary

                    perimeter += (1 - grid[nr][nc]); //If neighbour is water, we will add 1 to the perimeter (1-0 = 1); if neighbour is land we will add 0 to perimeter (1-1 = 0)
                }
            }
        }

        return perimeter;
    }
}