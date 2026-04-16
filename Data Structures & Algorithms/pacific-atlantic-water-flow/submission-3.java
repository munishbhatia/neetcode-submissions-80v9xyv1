class Solution {
    private final int[][] directions = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};
    
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if(heights == null) return new ArrayList<List<Integer>>();

        final int rows = heights.length;
        final int cols = heights[0].length;

        if(rows == 0 || cols == 0) return new ArrayList<List<Integer>>();

        final int[][] reach = new int[rows][cols];

        for(int[] row : reach) {
            Arrays.fill(row, 0);
        }

        final List<List<Integer>> result = new ArrayList<>();

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; ++j) {
                // if(
                //     (hasDecrementingPathToTop(heights, reach, new HashSet<>(), rows, cols, i, j) || 
                //         hasDecrementingPathToLeft(heights, reach, new HashSet<>(), rows, cols, i, j)) &&
                //     (hasDecrementingPathToBottom(heights, reach, new HashSet<>(), rows, cols, i, j) || 
                //         hasDecrementingPathToRight(heights, reach, new HashSet<>(), rows, cols, i, j))
                // ) {
                //     result.add(Arrays.asList(new Integer[]{i, j}));
                // }

                if(hasDecrementingPathToTopLeft(heights, reach, new HashSet<>(), rows, cols, i, j)) {
                    reach[i][j] += 1;
                }

                if(hasDecrementingPathToBottomRight(heights, reach, new HashSet<>(), rows, cols, i, j)) {
                    reach[i][j] += 2;
                }

                if(reach[i][j] == 3) result.add(Arrays.asList(new Integer[]{i, j}));

                // dfs(heights, reach, new HashSet<>(), rows, cols, i, j);
            }
        }

        // for(List<Integer> r : result) System.out.println(r);

        // final List<List<Integer>> result = new ArrayList<>();
        // for(int i = 0; i < rows; ++i) {
        //     for(int j = 0; j < cols; ++j) {
        //         if(reach[i][j] == 3) {
        //             result.add(Arrays.asList(new Integer[]{i,j}));
        //         }
        //     }
        // }

        return result;
    }

    // private void dfs(int[][] heights, int[][] reach, Set<Integer> visited, int rows, int cols, int r, int c) {
    //     if(reach[r][c] != -1) return; //We have processed this already
        
    //     System.out.println("DFS: " + r + "," + c);
        
    //     visited.add(cellId(cols, r, c));

    //     boolean canReachPacific = canReachPacific(new int[]{r, c});
    //     boolean canReachAtlantic = canReachAtlantic(new int[]{r, c}, rows, cols);

    //     for(int[] dir : directions) {
    //         int nr = r + dir[0];
    //         int nc = c + dir[1];

    //         if(!withinBounds(rows, cols, nr, nc) || heights[nr][nc] > heights[r][c]) continue;

    //         System.out.println("\tNeighbour: " + nr + "," + nc);
    //         if(!visited.contains(cellId(cols, nr, nc))) {
    //             System.out.println("\tCalling: " + nr + "," + nc);
    //             dfs(heights, reach, visited, rows, cols, nr, nc);
    //         }

    //         canReachPacific |= (reach[nr][nc] == 1 || reach[nr][nc] == 3);
    //         canReachAtlantic |= (reach[nr][nc] == 2 || reach[nr][nc] == 3);
    //     }

    //     System.out.println(String.format("Outcome for %d,%d: %b and %b", r, c, canReachPacific, canReachAtlantic));
    //     reach[r][c] = 0;
    //     if(canReachPacific) reach[r][c] = 1;
    //     if(canReachAtlantic) reach[r][c] = 2;
    //     if(canReachPacific && canReachAtlantic) reach[r][c] = 3;
    // }

    private boolean hasDecrementingPathToTop(int[][] heights, int[][] reach, Set<Integer> visited, int rows, int cols, int r, int c) {
        if(r == 0) return true;

        boolean result = false;
        visited.add(cellId(cols, r, c));
        
        for(int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if(!withinBounds(rows, cols, nr, nc) || heights[nr][nc] > heights[r][c]) continue;

            if(!visited.contains(cellId(cols, nr, nc))) {
                result |= hasDecrementingPathToTop(heights, reach, visited, rows, cols, nr, nc);
            }

            if(result) break;
        }

        return result;
    }

    private boolean hasDecrementingPathToLeft(int[][] heights, int[][] reach, Set<Integer> visited, int rows, int cols, int r, int c) {
        if(c == 0) return true;

        boolean result = false;
        visited.add(cellId(cols, r, c));
        
        for(int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if(!withinBounds(rows, cols, nr, nc) || heights[nr][nc] > heights[r][c]) continue;

            if(!visited.contains(cellId(cols, nr, nc))) {
                result |= hasDecrementingPathToLeft(heights, reach, visited, rows, cols, nr, nc);
            }

            if(result) break;
        }

        return result;
    }

    private boolean hasDecrementingPathToBottom(int[][] heights, int[][] reach, Set<Integer> visited, int rows, int cols, int r, int c) {
        if(r == (rows - 1)) return true;

        boolean result = false;
        visited.add(cellId(cols, r, c));
        
        for(int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if(!withinBounds(rows, cols, nr, nc) || heights[nr][nc] > heights[r][c]) continue;

            if(!visited.contains(cellId(cols, nr, nc))) {
                result |= hasDecrementingPathToBottom(heights, reach, visited, rows, cols, nr, nc);
            }

            if(result) break;
        }

        return result;
    }

    private boolean hasDecrementingPathToRight(int[][] heights, int[][] reach, Set<Integer> visited, int rows, int cols, int r, int c) {
        if(c == (cols - 1)) return true;

        boolean result = false;
        visited.add(cellId(cols, r, c));
        
        for(int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if(!withinBounds(rows, cols, nr, nc) || heights[nr][nc] > heights[r][c]) continue;

            if(!visited.contains(cellId(cols, nr, nc))) {
                result |= hasDecrementingPathToRight(heights, reach, visited, rows, cols, nr, nc);
            }

            if(result) break;
        }

        return result;
    }

    private boolean hasDecrementingPathToTopLeft(int[][] heights, int[][] reach, Set<Integer> visited, int rows, int cols, int r, int c) {
        if(r == 0 || c == 0 || reach[r][c] == 1 || reach[r][c] == 3) return true;

        boolean result = false;
        visited.add(cellId(cols, r, c));
        
        for(int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if(!withinBounds(rows, cols, nr, nc) || heights[nr][nc] > heights[r][c]) continue;

            if(!visited.contains(cellId(cols, nr, nc))) {
                result |= hasDecrementingPathToTopLeft(heights, reach, visited, rows, cols, nr, nc);
            }

            if(result) break;
        }

        return result;
    }

    private boolean hasDecrementingPathToBottomRight(int[][] heights, int[][] reach, Set<Integer> visited, int rows, int cols, int r, int c) {
        if(r == (rows - 1) || c == (cols - 1)  || reach[r][c] == 2 || reach[r][c] == 3) return true;

        boolean result = false;
        visited.add(cellId(cols, r, c));
        
        for(int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if(!withinBounds(rows, cols, nr, nc) || heights[nr][nc] > heights[r][c]) continue;

            if(!visited.contains(cellId(cols, nr, nc))) {
                result |= hasDecrementingPathToBottomRight(heights, reach, visited, rows, cols, nr, nc);
            }

            if(result) break;
        }

        return result;
    }

    // private boolean canReachPacific(int[] cell) {
    //     return cell[0] == 0 || cell[1] == 0;
    // }

    // private boolean canReachAtlantic(int[] cell, int rows, int cols) {
    //     return cell[0] == rows - 1 || cell[1] == cols - 1;
    // }

    private int cellId(int cols, int r, int c) {
        return r * cols + c;
    }

    private boolean withinBounds(int rows, int cols, int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }
}
