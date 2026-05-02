class Solution {
    class TrieNode {
        int count;
        TrieNode[] children;

        public TrieNode() {
            count = 0;
            children = new TrieNode[26];
        }
    }

    private String prefix = "";
    private int total = 0;
    public String longestCommonPrefix(String[] strs) {
        if(strs == null) return "";

        final int len = strs.length;
        if (len == 0) return "";
        if (len == 1) return strs[0];

        total = len;
        final TrieNode root = new TrieNode();
        for(String str : strs) {
            insertStringAndUpdate(root, str);
        }

        // TrieNode curr = root;
        // for(int i = 0; i < 26; ++i) {
        //     if(curr.children[i] != null)
        //         System.out.println((char) ('a' + i) + " : " + curr.children[i].count);
        // }

        return prefix;
    }

    public void insertStringAndUpdate(TrieNode root, String str) {
        TrieNode curr = root;
        for(int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if(curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }

            if(++curr.children[c - 'a'].count == total) {
                prefix = (prefix.length() < (i+1)) ? str.substring(0, i+1) : prefix;
            }

            curr = curr.children[c - 'a'];
        }
    }
}