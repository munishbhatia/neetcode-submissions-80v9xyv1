class Solution {
    public boolean validPalindrome(String s) {
        if(null == s) return true;
        
        final int len = s.length();
        if(len == 1) return true;

        int left = 0;
        int right = len-1;

        while(left < right && s.charAt(left) == s.charAt(right)) {
            ++left;
            --right;
        }

        if(left >= right) return true;

        return helperPalindrome(s, left+1, right) || helperPalindrome(s, left, right-1);
    }

    private boolean helperPalindrome(String s, int left, int right) {
        while(left < right && s.charAt(left) == s.charAt(right)) {
            ++left;
            --right;
        }

        if(left >= right) return true;

        return false;
    }
}