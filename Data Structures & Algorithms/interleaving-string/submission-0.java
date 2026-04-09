class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.equals("") && s2.equals("") && s3.equals("")) return true; //same for nulls and len 0

        final int s1Len = s1.length();
        final int s2Len = s2.length();
        final int s3Len = s3.length();

        if(s1Len + s2Len != s3Len) return false;

        return dfs(s1, s2, s3, 0, 0, 0);
    }

    private boolean dfs(String s1, String s2, String s3, int s1Idx, int s2Idx, int s3Idx) {
        if(s1Idx >= s1.length() && s2Idx >= s2.length() && s3Idx >= s3.length()) return true;

        boolean result = false;

        if(s1Idx < s1.length() && s1.charAt(s1Idx) == s3.charAt(s3Idx)) {
            result = dfs(s1, s2, s3, s1Idx + 1, s2Idx, s3Idx + 1);
        }

        if(result) return result;

        if(s2Idx < s2.length() && s2.charAt(s2Idx) == s3.charAt(s3Idx)) {
            result = dfs(s1, s2, s3, s1Idx, s2Idx + 1, s3Idx + 1);
        }

        return result;
    }
}
