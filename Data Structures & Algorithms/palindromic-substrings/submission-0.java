class Solution {
    public int countSubstrings(String s) {
        if(s == null) return 0;

        //Find even length palindromic substrings
        int evenLengthPalindromeCount = getEvenLengthPalindromeCount(s);

        //Find odd length palindromic substrings, including single chars
        int oddLengthPalindromeCount = getOddLengthPalindromeCount(s);

        //Return response
        return evenLengthPalindromeCount + oddLengthPalindromeCount;
    }

    private int getEvenLengthPalindromeCount(String input) {
        int count = 0;
        int len = input.length();
        for(int i=0; i<len-1; ++i) {
            int left = i;
            int right = i+1;

            while(left >= 0 && right <len && input.charAt(left) == input.charAt(right)) {
                ++count;
                --left;
                ++right;
            }
        }

        return count;
    }

    private int getOddLengthPalindromeCount(String input) {
        int count = 0;
        int len = input.length();
        for(int i=0; i<len; ++i) {
            int start = i;
            int end = i;

            while(start >= 0 && end < len && input.charAt(start) == input.charAt(end)) {
                ++count;
                --start;
                ++end;
            }
        }

        return count;
    }
}
