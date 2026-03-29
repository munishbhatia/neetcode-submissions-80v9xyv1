class Solution {
    public int leastInterval(char[] tasks, int n) {
        if(null == tasks || tasks.length == 0) return 0;

        int minCycleCount = 0;
        final int[] taskFrequency = new int[26];
        for(char task : tasks) {
            taskFrequency[task - 'A']++;
        }

        PriorityQueue<Integer> primary = new PriorityQueue<>((a,b) -> Integer.compare(b, a));
        Queue<Integer> backup = new ArrayDeque<>();

        for(int i=0; i<26; ++i) {
            if(taskFrequency[i] > 0) {
                primary.offer(taskFrequency[i]);
            }
        }

        while(!primary.isEmpty()) {
            int count = 0;

            while(count < (n+1) && !primary.isEmpty()) {
                Integer curr = primary.poll();
                ++count;
                if(curr > 1) {
                    backup.offer(curr - 1);
                }
            }

            while(!backup.isEmpty()) {
                primary.offer(backup.poll()); //By using an ArrayDeque instead of PQ for the backup queue, this loop becomes O(N) instead of O(NlogN)!!
            }

            minCycleCount += count;
            if(count < (n+1) && !primary.isEmpty()) { //The empty check criteria is important otherwise we will add idle cycles even when there are no more tasks to process.
                minCycleCount += (n+1) - count; //Add idle cycles if needed to maintain a gap of at least n cycles.
            }
        }

        return minCycleCount;
    }
}
