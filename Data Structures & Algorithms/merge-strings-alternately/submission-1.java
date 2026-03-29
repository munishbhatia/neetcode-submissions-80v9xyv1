class Solution {
    public String mergeAlternately(String word1, String word2) {
        if(word1 == null) return word2;
        if(word2 == null) return word1;

        final StringBuilder response = new StringBuilder();
        int i=0, j=0;

        while (i < word1.length() && j < word2.length()) {
            response.append(word1.charAt(i++));
            response.append(word2.charAt(j++));
        }

        if(i < word1.length()) {
            response.append(word1.substring(i));
        }

        if(j < word2.length()) {
            response.append(word2.substring(j));
        }

        return response.toString();
    }
}