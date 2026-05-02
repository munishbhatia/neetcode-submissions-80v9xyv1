class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null) return "";

        final int len = strs.length;
        if (len == 0) return "";
        if (len == 1) return strs[0];

        String commonPrefix = getCommonPrefix(strs[0], strs[1]);
        for(int i = 2; i < len; ++i) {
            commonPrefix = getCommonPrefix(commonPrefix, strs[i]);
        }

        return commonPrefix;
    }

    private String getCommonPrefix(String a, String b) {
        if(a == null || b == null || a.length() == 0 || b.length() == 0) return "";

        final StringBuilder sb = new StringBuilder();
        int i = 0;
        while(i < a.length() && i < b.length() && a.charAt(i) == b.charAt(i)) {
            sb.append(a.charAt(i));
            ++i;
        }

        return sb.toString();
    }
}

//Get the longest common prefix of strings at index 0 and 1
//Then continue matching that prefix with other array elements, shrinking it as required
//Return the adjusted/shrinked prefix string