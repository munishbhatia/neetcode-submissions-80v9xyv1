class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) return 0;

        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, 0);
        dp[s.length()] = 1; //One way to decode an empty string

        for(int index = s.length()-1; index >= 0; --index) {
            if(s.charAt(index) == '0') continue; //dp[index] is already set to 0
            
            dp[index] = dp[index+1]; //Consume char at index to form a single digit decoding. This of course assume valid input
            
            if(index+1 < s.length() && Integer.parseInt(s.substring(index, index+2)) <= 26) {
                dp[index] += dp[index+2]; //Consume chars at index & index+1 to form a two digit decoding
            }
        }
        return dp[0];
    }
}
