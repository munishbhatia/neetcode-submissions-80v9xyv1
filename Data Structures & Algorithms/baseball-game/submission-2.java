class Solution {
    public int calPoints(String[] operations) {
        final Stack<Integer> stack = new Stack<>();
        int response = 0;

        for(String s : operations) {
            if(s.equalsIgnoreCase("C")) {
                response -= stack.pop();
                continue;
            } else if(s.equalsIgnoreCase("+")) {
                int top = stack.pop();
                int toPush = top + stack.peek();
            
                stack.push(top);
                stack.push(toPush);
            } else if(s.equalsIgnoreCase("D")) {
                stack.push(stack.peek() * 2);
            } else {
                int number = Integer.parseInt(s); //Should handle NumberFormatException
                stack.push(number);
            }
            response += stack.peek();
        }

        return response;
    }
}