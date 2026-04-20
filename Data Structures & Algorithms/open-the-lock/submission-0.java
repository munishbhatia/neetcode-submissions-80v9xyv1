class Solution {
    public int openLock(String[] deadends, String target) {
        if(target == null || target.length() == 0) return -1; //Target not achievable

        return bfs(deadends, "0000", target);
    }

    private int bfs(String[] deadEnds, String start, String target) {
        final Set<String> visited = new HashSet<>(Arrays.asList(deadEnds));
        final Queue<String> queue = new LinkedList<>();

        if(visited.contains("0000")) return -1; //We can't even start traversing

        queue.offer(start);
        visited.add(start);

        int turns = 0;
        while(!queue.isEmpty()) {
            final int size = queue.size();

            for(int i = 0; i < size; ++i) {
                String curr = queue.poll();

                if(curr.equals(target)) return turns;

                List<String> neighbours = getNeighbours(curr);
                for(String neighbour : neighbours) {
                    if(visited.contains(neighbour)) continue;

                    visited.add(neighbour);
                    queue.offer(neighbour);
                }
            }

            ++turns;
        }

        return -1; 
    }

    private List<String> getNeighbours(String s) {
        List<String> neighbours = new ArrayList<>();

        for(int i = 0; i < s.length(); ++i) {
            StringBuilder n = new StringBuilder(s);

            char next = (char)((((s.charAt(i) - '0') + 1) % 10) + '0');
            n.setCharAt(i, next);
            neighbours.add(n.toString());
            
            char prev = (char)((((s.charAt(i) - '0') - 1 + 10) % 10) + '0');
            n.setCharAt(i, prev);
            neighbours.add(n.toString());
        }

        return neighbours;
    }
}