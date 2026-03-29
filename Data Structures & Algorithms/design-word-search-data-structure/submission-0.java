class WordDictionary {
    private DictionaryNode root;

    public WordDictionary() {
        root = new DictionaryNode();
    }

    public void addWord(String word) {
        DictionaryNode curr = root;

        for(int i=0; i<word.length(); ++i) {
            char c = word.charAt(i);
            curr.getChildren().putIfAbsent(c, new DictionaryNode());
            curr = curr.getChildren().get(c);
        }

        curr.setTerminal(true);
    }

    public boolean search(String word) {
        return searchHelper(root, word, 0);
    }

    private boolean searchHelper(DictionaryNode curr, String word, int index) {
        if(index > word.length() || curr == null) return false; //Index > word.length() shouldn't happen but defending against potential bugs

        if(index == word.length()) {
            return curr.isTerminal(); //curr being null is handled in above check
        }

        char c = word.charAt(index);
        if(c == '.') {
            for(DictionaryNode node : curr.getChildren().values()) {
                if(searchHelper(node, word, index+1)) return true;
            }
            return false;
        } else {
            return searchHelper(curr.getChildren().get(c), word, index+1);
        }
    }

    class DictionaryNode {
        private final Map<Character, DictionaryNode> children;
        private boolean isTerminal;

        DictionaryNode() {
            children = new HashMap<>();
            isTerminal = false;
        }

        Map<Character, DictionaryNode> getChildren() {
            return children;
        }

        boolean isTerminal() {
            return isTerminal;
        }

        void setTerminal(boolean terminal) {
            isTerminal = terminal;
        }
    }
}
