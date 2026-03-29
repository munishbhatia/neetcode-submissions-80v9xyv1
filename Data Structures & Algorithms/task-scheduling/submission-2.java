class Solution {
    public int leastInterval(char[] tasks, int n) {
        if(null == tasks || tasks.length == 0) return 0;

        int minCycleCount = 0;
        final int[] taskFrequency = new int[26];
        for(char task : tasks) {
            taskFrequency[task - 'A']++;
        }

        PriorityQueue<Integer> primary = new PriorityQueue<>((a,b) -> Integer.compare(b, a));
        PriorityQueue<Integer> backup = new PriorityQueue<>((a,b) -> Integer.compare(b, a));

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

            minCycleCount += count;
            if(count < (n+1) && (!backup.isEmpty() || !primary.isEmpty())) { //The empty check criteria is important otherwise we will add idle cycles even when there are no more tasks to process.
                minCycleCount += (n+1) - count; //Add idle cycles if needed to maintain a gap of at least n cycles.
            }
            
            while(!backup.isEmpty()) {
                primary.offer(backup.poll());
            }
        }

        return minCycleCount;
    }
}
