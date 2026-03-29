class Solution {
    final Map<Character,Character> parenthesis = Map.of(
        '(', ')',
        '{', '}',
        '[', ']'
    );
    final Map<Character,Character> reverse = Map.of(
        ')', '(',
        '}', '{',
        ']', '['
    );

    public boolean isValid(String s) {
        if(s == null || s.length() == 0) {
            return true;
        }
        final Stack<Character> stack = new Stack();
        for(char curr : s.toCharArray()) {
            if(parenthesis.containsKey(curr)) { //It's an open parenthesis
                stack.push(curr); //Push the char on to the stack
                continue;
            }

            if(reverse.containsKey(curr)) { //It's a closing parenthesis
                if(stack.isEmpty() || stack.peek() != reverse.get(curr)) {
                    return false; //We found a non-matching closing parenthesis; invalid input
                }
                stack.pop(); //We found a matching parenthesis; keep going
                continue;
            }

            // throw new InvalidArgumentException("Invalid input received: " + curr);
        }
        return stack.isEmpty();
    }
}
