class Solution {
    // public String longestDiverseString(int a, int b, int c) {
    //     final PriorityQueue<PQNode> pq = new PriorityQueue<>((x,y) -> y.getCount() - x.getCount());

    //     if(a > 0) pq.offer(new PQNode('a', a));
    //     if(b > 0) pq.offer(new PQNode('b', b));
    //     if(c > 0) pq.offer(new PQNode('c', c));

    //     final StringBuilder responseBuilder = new StringBuilder("");
    //     PQNode tnode = null;

    //     while(!pq.isEmpty()) {
    //         PQNode curr = pq.poll();
    //         int max = (tnode == null) ? 0 : tnode.getCount();
    //         max = Math.max(max, pq.isEmpty() ? 0 : pq.peek().getCount());

    //         if(curr.getCount() - max > 1) {
    //             responseBuilder.append(curr.getChar());
    //             responseBuilder.append(curr.getChar());
    //             curr.setCount(curr.getCount() - 2);;
    //         } else {
    //             responseBuilder.append(curr.getChar());
    //             curr.setCount(curr.getCount() - 1);;
    //         }

    //         if(tnode != null) {
    //             pq.offer(tnode);
    //             tnode = null; //MISSING THIS CAUSE HEAP OUTAGE + TLE !! tnode will stay set from previous iteration and will keep getting added infinitely
    //         }

    //         if(curr.getCount() > 0) {
    //             tnode = curr;
    //         }
    //     }

    //     return responseBuilder.toString();
    // }

    public String longestDiverseString(int a, int b, int c) {
        final PriorityQueue<PQNode> pq = new PriorityQueue<>((x,y) -> y.getCount() - x.getCount());

        if(a > 0) pq.offer(new PQNode('a', a));
        if(b > 0) pq.offer(new PQNode('b', b));
        if(c > 0) pq.offer(new PQNode('c', c));

        final StringBuilder responseBuilder = new StringBuilder("");
        PQNode tnode = null;
        char prev = ' ';
        boolean set = false;

        while(!pq.isEmpty()) {
            PQNode curr = pq.poll();
            // System.out.println("Prev: " + prev);
            // System.out.println("Curr: " + curr);
            // int max = (tnode == null) ? 0 : tnode.getCount();
            // max = Math.max(max, pq.isEmpty() ? 0 : pq.peek().getCount());

            // if(curr.getCount() - max > 1) {
            //     responseBuilder.append(curr.getChar());
            //     responseBuilder.append(curr.getChar());
            //     curr.setCount(curr.getCount() - 2);
            // } else {
            //     responseBuilder.append(curr.getChar());
            //     curr.setCount(curr.getCount() - 1);
            // }

            responseBuilder.append(curr.getChar());
            curr.setCount(curr.getCount() - 1);

            // curr.setCount(curr.getCount() - 1);

            // int newCount = curr.getCount() - 1;
            // System.out.println("NC: " + newCount);

            if(tnode != null) {
                // System.out.println("Inserting: " + tnode);
                pq.offer(tnode);
                tnode = null; //MISSING THIS CAUSE HEAP OUTAGE + TLE !! tnode will stay set from previous iteration and will keep getting added infinitely
            }

            if(prev == curr.getChar()) {
                // System.out.println("Polled");
                // curr.setCount(newCount);
                if(curr.getCount() > 0) tnode = curr;
            } else {
                // curr.setCount(newCount);
                if(curr.getCount() > 0) pq.offer(curr);
            }

            prev = curr.getChar();
        }

        return responseBuilder.toString();
    }

    class PQNode {
        private char c;
        private int count;

        public PQNode(char c, int count) {
            this.c = c;
            this.count = count;
        }

        public char getChar() {
            return c;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String toString() {
            return String.format("Char: %c; Count: %d", c, count);
        }
    }
}

//We want to consume the elements in the order of their availability
//So if a > b > c, we will consume 'a' first and then decide the next order
//We temporarily take the curr element out of PQ and store it in tNode (temp node) to not use it again in order to avoid "aa" sequence followed by "aa" which would violate the criteria since that would for a substring "aaaa" which would contain 3 a's "aaa"
//The next decision is if we want to be GREEDY and use 2 occurrences of the chosen character, or just one
//That decision is based on the factor of "how many more of the chosen chars do we have available"
//If diff between the chosen char count and the next available char count (either tNode or PQ.peek()) is greater than 1 and the chosen char is higher count, we go greedy
//Otherwise we just use 1 count of the chosen char and conservatively save the rest for future use.