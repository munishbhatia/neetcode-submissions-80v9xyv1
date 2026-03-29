class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) return 0;

        return numDecodingsHelper(s, 0);
    }

    private int numDecodingsHelper(String s, int index) {
        if(index > s.length()) return 0;

        if(index == s.length()) return 1; //Empty string can be decoded in one way

        if(s.charAt(index) == '0') return 0; //Invalid decoding

        int response = numDecodingsHelper(s, index+1); //Consume char at index to form a single digit decoding

        if(index+1 < s.length() && Integer.parseInt(s.substring(index, index+2)) <= 26) {
            response += numDecodingsHelper(s, index+2); //Consume chars at index & index+1 to form a two digit decoding
        }

        return response;
    }
}
