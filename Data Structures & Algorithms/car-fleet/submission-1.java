class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        final int len = position.length;
        final Pair[] pairs = new Pair[len];

        final Stack<Double> stack = new Stack<>();
        final double targetl = target * 1.0;

        for(int i=0; i<len; ++i) {
            pairs[i] = new Pair(position[i], speed[i]);
        }

        Arrays.sort(pairs, Collections.reverseOrder());
        // System.out.println(Arrays.toString(pairs));

        for(int i=0; i<len; ++i) {
            final double arrival = (targetl - pairs[i].position)/pairs[i].speed;
            if(stack.isEmpty()) {
                stack.push(arrival);
                continue;
            }
            //Stack is not empty
            if(stack.peek() < arrival) {
                stack.push(arrival); //This will be the new limiting arrival time for all cars behind it
            }
        }

        return stack.size();
    }

    class Pair implements Comparable {
        public int position;
        public int speed;

        public Pair(int position, int speed) {
            this.position = position;
            this.speed = speed;
        }

        public int compareTo(Object o) {
            return this.position - ((Pair)o).position;
        }

        public String toString() {
            return "Pos: " + position + "; Speed: " + speed;
        }
    }
}

// 100/4 = 25
// 98/2 = 49
// 96/1 = 96

// 9/3 = 3 hours
// 6/2 = 3 hours

// 6/2 = 3 hours
// 9/2 = 4.5 hours
// 10/1 = 10 hours
// 3/1 = 3 hours

// Monotonically increasing stack - number of distinct elements in the stack is the number of fleets
// We need to consider the starting position

// 7,4 will be one fleet
// 1 will be a fleet
// 0 will be a fleet

// Brute?
// Start calculating time of arrival from left to right
// A cars time of arrival will be greater of it's calculated time of arrival and 
// that of any car at a starting position ahead or equal of it
// The number of distinct times of arrivals will give the number of fleets
// O(N^2) time, O(N) space