class Solution {
    private Set<String> parenthesis = new HashSet<>();

    public List<String> generateParenthesis(int n) {
        if(n <= 0) {
            return List.of();
        }
        
        final StringBuilder builder = new StringBuilder(2*n);
        builder.append("");
        generateParenthesisHelper(n, builder);
        return new ArrayList(parenthesis);
    }

    private void generateParenthesisHelper(int n, StringBuilder curr) {
        final int len = curr.length();
        if(len == 2*n) { //parenthesis will occur in pairs
            parenthesis.add(curr.toString());
            return;
        }

        StringBuilder c = new StringBuilder(curr);

        for(int i=0; i<=len; ++i) {
            c = c.insert(i, "()");
            
            generateParenthesisHelper(n, c);
            
            //Backtrack
            c = c.delete(i, i+2); //upper range is exclusive
        }
    }
}

// () -> ()(), (())
// ()() -> ()()(), (())(), ()(())
// (()) -> (()()), ((()))