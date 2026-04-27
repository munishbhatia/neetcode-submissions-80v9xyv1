class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(wordList == null || wordList.size() == 0 || !wordList.contains(endWord)) return 0;

        wordList.add(beginWord);
        return bfs(beginWord, endWord, wordList);
    }

    private int bfs(String beginWord, String endWord, List<String> wordList) {
        Map<String, Integer> visitedFront = new HashMap<>();
        Queue<String> qFront = new LinkedList<>();
        Map<String, Integer> visitedEnd = new HashMap<>();
        Queue<String> qEnd = new LinkedList<>();
        

        qFront.offer(beginWord);
        qEnd.offer(endWord);
        visitedFront.put(beginWord, 1);
        visitedEnd.put(endWord, 1);

        while(!qFront.isEmpty() && !qEnd.isEmpty()) {
            if(qFront.size() > qEnd.size()) {
                Queue<String> tempQ = qFront;
                qFront = qEnd;
                qEnd = tempQ;

                Map<String, Integer> tempMap = visitedFront;
                visitedFront = visitedEnd;
                visitedEnd = tempMap;
            }

            final int qsize = qFront.size();

            for(int i = 0; i < qsize; ++i) {
                String curr = qFront.poll();
                int steps = visitedFront.get(curr);

                for(int j = 0; j < curr.length(); ++j) {
                    String pattern = curr.substring(0, j) + ".*" + curr.substring(j+1);
                    for(String n : wordList.stream().filter(w -> w.matches(pattern)).collect(Collectors.toList())) { //This generates the list of matching neighbours for current word (curr), that have not already been visited, at runtime -- prevents graph precomputation
                        if(visitedEnd.containsKey(n)) {
                            return visitedEnd.get(n) + steps;
                        }
                        
                        if(!visitedFront.containsKey(n)) {
                            visitedFront.put(n, steps + 1);
                            qFront.offer(n);
                        }
                    }
                }
            }
        }

        return 0; //If we reach here that would mean we did not find a path from beginWord to endWord
    }
}
