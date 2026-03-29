class Solution {
    public String longestDiverseString(int a, int b, int c) {
        final PriorityQueue<PQNode> pq = new PriorityQueue<>((x,y) -> y.getCount() - x.getCount());
        // final PriorityQueue<PQNode> temp = new PriorityQueue<>((a,b) -> b.getCount() - a.getCount());

        if(a > 0) pq.offer(new PQNode('a', a));
        if(b > 0) pq.offer(new PQNode('b', b));
        if(c > 0) pq.offer(new PQNode('c', c));

        final StringBuilder responseBuilder = new StringBuilder("");
        PQNode tnode = null;

        while(!pq.isEmpty()) {
            PQNode curr = pq.poll();
            int max = (tnode == null) ? 0 : tnode.getCount();
            max = Math.max(max, pq.isEmpty() ? 0 : pq.peek().getCount());
            // System.out.println("Before: " + curr);
            // System.out.println("TNode: " + tnode);

            // if(pq.isEmpty()) {
            //     // System.out.println("Empty PQ");
            //     if(tnode != null && tnode.getCount() >= curr.getCount()) {
            //         // System.out.println("C1");
            //         //use 1
            //         responseBuilder.append(curr.getChar());
            //         curr.setCount(curr.getCount() - 1);
            //     } else {
            //         // System.out.println("C2");
            //         int useCount = Math.min(2, curr.getCount());
            //         //use 2 if available
            //         for(int i=0; i < useCount; ++i) {
            //             responseBuilder.append(curr.getChar());
            //             curr.setCount(curr.getCount() - 1);
            //         }
            //     }
            // } else {
            //     if(curr.getCount() - pq.peek().getCount() > 1) {
            //         // System.out.println("Using two " + curr.getChar() + "(s)");
            //         responseBuilder.append(curr.getChar());
            //         responseBuilder.append(curr.getChar());
            //         curr.setCount(curr.getCount() - 2);;
            //     } else {
            //         // System.out.println("Using one " + curr.getChar());
            //         responseBuilder.append(curr.getChar());
            //         curr.setCount(curr.getCount() - 1);;
            //     }
            // }

            if(curr.getCount() - max > 1) {
                // System.out.println("Using two " + curr.getChar() + "(s)");
                responseBuilder.append(curr.getChar());
                responseBuilder.append(curr.getChar());
                curr.setCount(curr.getCount() - 2);;
            } else {
                // System.out.println("Using one " + curr.getChar());
                responseBuilder.append(curr.getChar());
                curr.setCount(curr.getCount() - 1);;
            }

            if(tnode != null) {
                pq.offer(tnode);
                tnode = null; //MISSING THIS CAUSE HEAP OUTAGE + TLE !! tnode will stay set from previous iteration and will keep getting added infinitely
            }

            // System.out.println("After: " + curr);
            if(curr.getCount() > 0) {
                tnode = curr;
            }

            // System.out.println("TNode: " + tnode);
        }

        // if(tnode != null) {
        //     for(int i=0; i<2 && i<tnode.getCount(); ++i) {
        //         responseBuilder.append(tnode.getChar());
        //     }
        // }

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