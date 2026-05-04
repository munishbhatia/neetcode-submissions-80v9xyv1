class Solution {
    public boolean isPalindrome(String s) {
        if(s == null) return true;

        final int len = s.length();
        if(len <= 1) return true;

        s = s.toLowerCase();

        int left = 0;
        int right = len - 1;

        while(left < right) {
            if(!Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
                continue;
            }
            if(!Character.isLetterOrDigit(s.charAt(right))) {
                --right;
                continue;
            }
            if(s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        
        return true;
    }
}
