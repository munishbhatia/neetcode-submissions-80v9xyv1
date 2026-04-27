class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(wordList == null || wordList.size() == 0 || !wordList.contains(endWord)) return 0;

        wordList.add(beginWord);
        final Map<String, List<String>> adjList = createGraphFromWords(wordList);

        // for(Map.Entry<String, List<String>> graphEntry : adjList.entrySet()) {
        //     System.out.println(graphEntry.getKey() + " : " + graphEntry.getValue());
        // }

        return bfs(adjList, beginWord, endWord, wordList);
    }

    private Map<String, List<String>> createGraphFromWords(List<String> wordList) {
        final Map<String, List<String>> graph = new HashMap<>();
        // System.out.println(wordList);

        final int size = wordList.size();
        for(int i = 0; i < size; ++i) {
            for(int j = i+1; j < size; ++j) {
                if(dist(wordList.get(i), wordList.get(j)) == 1) {
                    graph.computeIfAbsent(wordList.get(i), k -> new ArrayList<>()).add(wordList.get(j));
                    graph.computeIfAbsent(wordList.get(j), k -> new ArrayList<>()).add(wordList.get(i));
                }
            }
        }

        return graph;
    }

    private int dist(String a, String b) {
        int diff = 0;

        for(int i = 0; i < a.length(); ++i) {
            if(a.charAt(i) != b.charAt(i)) ++diff;
            if(diff > 1) break;
        }

        return diff;
    }

    private int bfs(Map<String, List<String>> adjList, String beginWord, String endWord, List<String> wordList) {
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

                for(String n : adjList.getOrDefault(curr, new ArrayList<>())) {
                    if(!visited.contains(n)) {
                        visited.add(n);
                        q.offer(n);
                    }
                }
            }
        }

        return 0; //If we reach here that would mean we did not find a path from beginWord to endWord
    }
}
