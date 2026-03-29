class Solution {
    public String longestPalindrome(String s) {
        if(s == null) return s;
        
        final int len = s.length();
        if(len == 1) return s;

        // final boolean[] isPalindrome = new boolean[len];
        // Arrays.fill(isPalindrome, true); //Each individual char is a palindromic substring

        //Get longest palindrome of odd length
        int oddLenMax = 1;
        int oddMaxStart = 0;
        int oddMaxEnd = 0;

        for(int i=1; i<len-1; ++i) {
            int start = i-1;
            int end = i+1;

            while(start >=0 && end < len && s.charAt(start) == s.charAt(end)) {
                int currMax = end - start + 1;
                oddLenMax = Math.max(oddLenMax, currMax);
                if(oddLenMax == currMax) {
                    oddMaxStart = start;
                    oddMaxEnd = end;
                }
                --start;
                ++end;
            }
        }

        int evenLenMax = 1;
        int evenMaxStart = 0;
        int evenMaxEnd = 0;

        //Get longest palindrome of even length
        for(int i=1; i<len; ++i) {
            int start = i-1;
            int end = i;

            while(start >=0 && end < len && s.charAt(start) == s.charAt(end)) {
                int currMax = end - start + 1;
                evenLenMax = Math.max(evenLenMax, currMax);
                if(evenLenMax == currMax) {
                    evenMaxStart = start;
                    evenMaxEnd = end;
                }
                --start;
                ++end;
            }

        }

        //Return max of the two palindromes
        if(oddLenMax > evenLenMax) {
            return s.substring(oddMaxStart, oddMaxEnd+1); //maxEnd+1 because end is non-inclusive in substring method
        }
        return s.substring(evenMaxStart, evenMaxEnd+1);
    }
}
