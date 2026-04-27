class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(wordList == null || wordList.size() == 0 || !wordList.contains(endWord)) return 0;

        wordList.add(beginWord);
        return bfs(beginWord, endWord, wordList);
    }

    private int bfs(String beginWord, String endWord, List<String> wordList) {
        final Set<String> visited = new HashSet<>();
        final Queue<String> q = new LinkedList<>();
        int len = 0;

        q.offer(beginWord);
        while(!q.isEmpty()) {
            len++;
            final int qsize = q.size();

            for(int i = 0; i < qsize; ++i) {
                String curr = q.poll();
                if(curr.equals(endWord)) return len;

                for(int j = 0; j < curr.length(); ++j) {
                    String pattern = curr.substring(0, j) + ".*" + curr.substring(j+1);
                    for(String n : wordList.stream().filter(w -> w.matches(pattern)).filter(w -> !visited.contains(w)).collect(Collectors.toList())) { //This generates the list of matching neighbours for current word (curr), that have not already been visited, at runtime -- prevents graph precomputation
                        visited.add(n);
                        q.offer(n);
                    }
                }
            }
        }

        return 0; //If we reach here that would mean we did not find a path from beginWord to endWord
    }
}
