class Solution {
    private final char SENTINEL = '_';

    public boolean exist(char[][] board, String word) {
        if(board == null && word == null) return true;

        if(word == null) return true;
        if(board == null) return false;

        final int wordLen = word.length();
        final int rows = board.length;
        final int cols = board[0].length;

        final char firstChar = word.charAt(0);

        for(int i=0; i<rows; ++i) {
            for(int j=0; j<cols; ++j) {
                if(board[i][j] != firstChar) continue;
                if(wordExistenceHelper(board, word, 0, i, j, rows, cols, wordLen)) return true;
            }
        }

        return false;
    }

    private boolean wordExistenceHelper(char[][] board, String word, int wordIndex, int currRow, int currCol, int rows, int cols, int wordLen) {
        if(wordIndex == wordLen) return true;
        
        if(currRow < 0 || currRow >= rows || currCol < 0 || currCol >= cols) return false;

        if(wordIndex > wordLen) return false;

        if(board[currRow][currCol] != word.charAt(wordIndex)) return false;

        board[currRow][currCol] = SENTINEL; //Use the char

        for(int i=-1; i<2; i+=2) { //check up and down neighbors of the current cell -- vertical neighbors
            if(wordExistenceHelper(board, word, wordIndex+1, currRow + i, currCol, rows, cols, wordLen)) return true;
        }

        for(int j=-1; j<2; j+=2) { //Check left and right neighbor of the current cell -- horizontal neighbors
            if(wordExistenceHelper(board, word, wordIndex+1, currRow, currCol+j, rows, cols, wordLen)) return true;
        }

        //Bactrack
        board[currRow][currCol] = word.charAt(wordIndex);

        return false;
    }
}





//For finding neighbours
//i=0; j -> +1, -1
//j=0; i -> +1, -1