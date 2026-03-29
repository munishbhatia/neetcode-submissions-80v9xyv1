class Solution {
    public void reverseString(char[] s) {
        if(s == null) return;

        final int len = s.length;

        int left = 0;
        for(int right = len-1; right > left; --right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            ++left;
        }
    }
}