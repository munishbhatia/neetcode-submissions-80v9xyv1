class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1 == null || text2 == null) return 0;

        final int len1 = text1.length();
        final int len2 = text2.length();

        int[] dp = new int[len2 + 1];
        Arrays.fill(dp, 0);

        for(int i=0; i<len1; ++i) {
            int[] row = new int[len2 + 1];
            row[0] = 0;

            for(int j=1; j<=len2; ++j) {
                if(text1.charAt(i) == text2.charAt(j-1)) {
                    row[j] = dp[j-1] + 1;
                } else {
                    row[j] = Math.max(dp[j], row[j-1]);
                }
            }

            dp = row;
            // System.out.println("Level: " + i + " -- " + Arrays.toString(dp));
        }

        return dp[len2];
    }
}
