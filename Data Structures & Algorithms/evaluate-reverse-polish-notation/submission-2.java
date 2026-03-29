class Solution {
    public int evalRPN(String[] tokens) {
        if(tokens == null || tokens.length == 0) {
            return 0;
        }

        final Stack<Integer> operands = new Stack<>();
        for(String c : tokens) {
            if("+-*/".contains(c)) { //Found an operator; perform arithmetic
                //TODO: Do we need to handle unary operators?
                if(operands.isEmpty() || operands.size() < 2) {
                    throw new RuntimeException("Invalid expression");
                }
                int operand2 = operands.pop();
                int operand1 = operands.pop();

                int result = switch(c) {
                    case "+" -> operand1 + operand2; //Potential overflow
                    case "-" -> operand1 - operand2; //Potential underflow
                    case "*" -> operand1 * operand2; //Potential overflow
                    case "/" -> operand1 / operand2; //Potential Divide by zero
                    default -> throw new RuntimeException("Invalid expression");
                };
                operands.push(result);
                continue;
            }

            //Found an operand, push to stack
            operands.push(Integer.valueOf(c));
        }
        return operands.pop();
    }
}
