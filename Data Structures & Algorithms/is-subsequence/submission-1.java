class Solution {
    public boolean isSubsequence(String s, String t) {
        if(s == null) return true;
        if(s.equals(t)) return true;
        if(t.length() <= s.length()) return false;

        int sptr = 0;
        int tptr = 0;
        int match = 0;

        while(sptr < s.length() && tptr < t.length()) {
            if(s.charAt(sptr) == t.charAt(tptr)) {
                ++match;
                ++sptr;
                ++tptr;
                continue;
            }

            //non-match
            while(tptr < t.length() && s.charAt(sptr) != t.charAt(tptr)) {
                ++tptr;
            } 
        }

        return match == s.length();
    }
}