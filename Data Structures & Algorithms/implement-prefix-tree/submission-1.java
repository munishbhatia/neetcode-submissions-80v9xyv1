class PrefixTree {

    private TrieNode root;

    public PrefixTree() {
        root = new TrieNode('_'); //Set a sentinel character
    }

    public void insert(String word) {
        TrieNode curr = this.root;
        
        for(int i=0; i<word.length(); ++i) {
            char c = word.charAt(i);
            if(curr.getChildren()[c - 'a'] == null) {
                curr.getChildren()[c - 'a'] = new TrieNode(c);
            }; //Should ideally be serialized
            curr = curr.getChildren()[c - 'a'];
        }

        curr.setTerminal(true);
    }

    public boolean search(String word) {
        TrieNode curr = this.root;
        
        for(int i=0; i<word.length(); ++i) {
            char c = word.charAt(i);
            if(curr == null) return false;
            curr = curr.getChildren()[c - 'a'];
        }

        return (curr != null) && curr.isTerminal();
    }

    public boolean startsWith(String prefix) {
        TrieNode curr = this.root;
        
        for(int i=0; i<prefix.length(); ++i) {
            char c = prefix.charAt(i);
            if(curr == null) return false;
            curr = curr.getChildren()[c - 'a'];
        }

        return curr != null;
    }

    private class TrieNode {
        char c;
        TrieNode[] children;
        boolean isTerminal;

        TrieNode(char c) {
            this.c = c;
            children = new TrieNode[26];
            isTerminal = false;
        } 

        TrieNode[] getChildren() {
            return this.children;
        }

        boolean isTerminal() {
            return isTerminal;
        }

        void setTerminal(boolean terminal) {
            this.isTerminal = terminal;
        }
    }
}
