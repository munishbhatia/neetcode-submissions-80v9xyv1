class PrefixTree {

    private TrieNode root;

    public PrefixTree() {
        root = new TrieNode('_'); //Set a sentinel character
    }

    public void insert(String word) {
        TrieNode curr = this.root;
        
        for(int i=0; i<word.length(); ++i) {
            char c = word.charAt(i);
            curr.getChildren().putIfAbsent(c, new TrieNode(c)); //Should ideally be serialized
            curr = curr.getChildren().get(c);
        }

        curr.setTerminal(true);
    }

    public boolean search(String word) {
        TrieNode curr = this.root;
        
        for(int i=0; i<word.length(); ++i) {
            char c = word.charAt(i);
            if(curr == null) return false;
            curr = curr.getChildren().get(c);
        }

        return (curr != null) && curr.isTerminal();
    }

    public boolean startsWith(String prefix) {
        TrieNode curr = this.root;
        
        for(int i=0; i<prefix.length(); ++i) {
            char c = prefix.charAt(i);
            if(curr == null) return false;
            curr = curr.getChildren().get(c);
        }

        return curr != null;
    }

    private class TrieNode {
        char c;
        Map<Character, TrieNode> children;
        boolean isTerminal;

        TrieNode(char c) {
            this.c = c;
            children = new HashMap<>();
            isTerminal = false;
        } 

        Map<Character, TrieNode> getChildren() {
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
