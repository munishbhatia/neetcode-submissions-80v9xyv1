class Solution {
    public int calPoints(String[] operations) {
        final int len = operations.length;
        final Stack<Integer> stack = new Stack<>();
        int response = 0;

        for(String s : operations) {
            if(s.equalsIgnoreCase("+")) {
                int top = stack.pop();
                int toPush = top + stack.peek();
                response += toPush;
                
                stack.push(top);
                stack.push(toPush);
            } else if(s.equalsIgnoreCase("C")) {
                response -= stack.pop();
            } else if(s.equalsIgnoreCase("D")) {
                response += (stack.peek() * 2);
                stack.push(stack.peek() * 2);
            } else {
                int number = Integer.parseInt(s); //Should handle NumberFormatException
                response += number;
                stack.push(number);
            }
        }

        return response;
    }
}