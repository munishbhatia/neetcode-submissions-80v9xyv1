class Solution {
    private Set<String> parenthesis = new HashSet<>();

    public List<String> generateParenthesis(int n) {
        if(n <= 0) {
            return List.of();
        }
        
        generateParenthesisHelper(n, new StringBuilder(2*n));
        return new ArrayList(parenthesis);
    }

    private void generateParenthesisHelper(int n, StringBuilder curr) {
        final int len = curr.length();
        if(len == 2*n) { //parenthesis will occur in pairs
            parenthesis.add(curr.toString());
            return;
        }

        for(int i=0; i<=len; ++i) {
            curr = curr.insert(i, "()");
            
            generateParenthesisHelper(n, curr);
            
            //Backtrack
            curr = curr.delete(i, i+2); //upper range is exclusive
        }
    }
}

// () -> ()(), (())
// ()() -> ()()(), (())(), ()(())
// (()) -> (()()), ((()))