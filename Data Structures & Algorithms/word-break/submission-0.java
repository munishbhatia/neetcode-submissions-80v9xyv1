class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s == null || s.length() == 0) return true;

        final Set<String> dict = new HashSet<>(wordDict); //Converting to hash set to make dict lookups O(1)
        final int len = s.length();
        final boolean[] dp = new boolean[len+1];
        dp[len] = true;

        for(int i = len-1; i >= 0; --i) {
            for(int j = i; j < len; ++j) {
                if(!dict.contains(s.substring(i, j+1))) continue; //s[i..j] is not a valid word in dict
                
                dp[i] = dp[j+1]; //s[i..j] is a valid word in dict, so if s[j+1..end] can be broken into valid words, s[i..end] can also be broken into valid words
                if(dp[i]) break; //We now know that substring s(i) can be broken into valid words, preempt
            }
        }

        return dp[0];
    }
}
