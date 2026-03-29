class Solution {
    public int numDecodings(String s) {
        if(s == null) return 0;

        final int len = s.length();

        if(len == 0) return 0;
        
        int[] dp = new int[len + 1];
        Arrays.fill(dp, 0);
        dp[len] = 1; //One way to decode an empty string
        if(Integer.parseInt(s.substring(len-1)) > 0) {
            dp[len - 1] = 1;
        } 

        for(int index = len-2; index >= 0; --index) {
            if(s.charAt(index) == '0') continue; //dp[index] is already set to 0
            
            dp[index] = dp[index+1]; //Consume char at index to form a single digit decoding. This of course assume valid input
            
            if(Integer.parseInt(s.substring(index, index+2)) <= 26) { //Length check in every iteration can be removed by setting dp[s.length() - 1]
                dp[index] += dp[index+2]; //Consume chars at index & index+1 to form a two digit decoding
            }
        }
        return dp[0];
    }
}
