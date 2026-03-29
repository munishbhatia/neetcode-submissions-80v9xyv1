class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s == null || s.length() == 0) return true;

        final Set<String> dict = new HashSet<>(wordDict); //Convert list to hash set to make dict lookups O(1)
        final int len = s.length();
        final int[] dp = new int[len+1];

        Arrays.fill(dp, -1); //-1 = uninitialized; 0 = false; 1 = true;
        dp[len] = 1;

        final int maxDictWordLength = dict.stream().mapToInt(String::length).max().getAsInt();

        topDownWordBreakHelper(s, dp, dict, maxDictWordLength, 0);

        return (dp[0] == 1) ? true : false;
    }

    private int topDownWordBreakHelper(String s, int[] dp, Set<String> dict, int maxDictWordLength, int startIndex) {
        if(startIndex == s.length()) return 1;

        if(dp[startIndex] != -1) {
            return dp[startIndex];
        }

        for(int j=startIndex; j < Math.min(startIndex + maxDictWordLength, s.length()) && dp[startIndex] != 1; ++j) { //dp[startIndex] != 1 indicates we now know that substring s[i..end] can be broken into valid words, preempt
            if(dict.contains(s.substring(startIndex, j+1))) {
                dp[startIndex] = topDownWordBreakHelper(s, dp, dict, maxDictWordLength, j+1);
            }
        }

        if(dp[startIndex] == -1) dp[startIndex] = 0; //Set this to false if we were unable to solve this sub-problem; I was getting Timeout without this since we'd leaving it at -1 and all the recursion is tried again even though we have tried it once.
        return dp[startIndex];
    }
}
