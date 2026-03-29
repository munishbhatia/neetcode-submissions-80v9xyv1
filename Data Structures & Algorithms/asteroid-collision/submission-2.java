class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        if(asteroids == null) return null;
        
        final int len = asteroids.length;
        final Stack<Integer> stack = new Stack<>();

        for(int i : asteroids) {
            stack.push(i);
            while(stack.size() > 1) {
                int a = stack.pop();
                int b = stack.peek();
                stack.push(a);

                if(!areMovingInOppositeDirections(a, b)) {
                    break;
                }

                if(b < 0 && a > 0) {
                    break;
                }

                if(Math.abs(a) == Math.abs(b)) {
                    stack.pop();
                    stack.pop();
                    break;
                }

                if(Math.abs(a) < Math.abs(b)) {
                    stack.pop();
                    break;
                }

                //Math.abs(a) > Math.abs(b)
                int temp = stack.pop();
                stack.pop();
                stack.push(temp);
            }
        }

        return stack.stream().mapToInt(i -> i).toArray();
    }

    private boolean areMovingInOppositeDirections(int a, int b) {
        return (a > 0 && b < 0) || (a < 0 && b > 0);
    }
}

//MISTAKE_1: Did not consider signs - Same signs = no collision
//MISTAKE_2: Did not consider signs!! - Opposite signs but same size (ABSOLUTE) = remove top. Just comparing won't give us the correct answer here (-4 and 4 are not equal)
//MISTAKE_3: Unclear requirements - collisions will keep happening down the stack if the directions are opposite