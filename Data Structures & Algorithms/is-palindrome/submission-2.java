class Solution {
    public boolean isPalindrome(String s) {
        if(s == null) return true;

        final int len = s.length();
        if(len <= 1) return true;

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
            if(Character.toLowerCase(s.charAt(left++)) != Character.toLowerCase(s.charAt(right--))) {
                return false;
            }
        }

        return true;
    }
}
