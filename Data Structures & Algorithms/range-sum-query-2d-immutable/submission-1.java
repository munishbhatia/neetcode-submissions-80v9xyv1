class NumMatrix {
    private final int[][] matrix;
    private int[][] prefixSum;

    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        this.prefixSum = new int[matrix.length + 1][matrix[0].length + 1];
        initializePrefixSum();
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int r1a = row1+1;
        int c1a = col1+1;
        int r2a = row2+1;
        int c2a = col2+1;

        return prefixSum[r2a][c2a] 
                - prefixSum[r1a-1][c2a]
                - prefixSum[r2a][c1a-1]
                + prefixSum[r1a-1][c1a-1];
    }

    private void initializePrefixSum() {
        for(int row = 1; row <= matrix.length; ++row) {
            for(int col = 1; col <= matrix[0].length; ++col) {
                prefixSum[row][col] = matrix[row-1][col-1] + prefixSum[row-1][col] + prefixSum[row][col-1] - prefixSum[row-1][col-1];
                //Here we use matrix[row-1][col-1] since we created the prefixSum matrix with the buffer for zero-th row & col
            }
        }

        // System.out.println("PrefixSum:");
        // for(int[] row : prefixSum) {
        //     System.out.println(Arrays.toString(row));
        // }
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */

 /*
 The prefixSum matrix contains the running sum of all the matrix elements seen so far (row and col included)
 As such, PS[r][c] = Matrix[r][c] + PS[r-1][c] + PS[r][c-1] - PS[r-1][c-1];
 We subtract PS[r-1][c-1] since it gets double added as part of the previous row sum, and previous col sum

 Once this has been created, getting the sum of any submatrix will be an O(1) operation. This can be achieved like:
 Sum of submatrix starting at (row1, col1) and ending at (row2, col2) is:
 PrefixSum[row2][col2] - PrefixSum[row1-1][col2] - PrefixSum[row2][col1-1] + PrefixSum[row1-1][col1-1];

 PrefixSum[row2][col2] contains the sum of ALL the elements in the matrix from (0,0) to (row2, col2)
 We then exclude the sum of all elements in the matrix from (0,0) to (row1-1)(col2) - so all elements till row1-1 (upper matrix if you will)
 Likewise, we exclude the sum of all elements in the matrix to the left of the submatrix we are interested in by doing PrefixSum[row2][col1-1]
 Then, we add back PrefixSum[row1-1][col1-1] because this gets subtracted twice since this is overlapped between both the upper and the left submatrices. This is the sum of all the matrix elements from (0,0) to (row1-1)(col1-1)
 */