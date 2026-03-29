class Solution {
    public int numRescueBoats(int[] people, int limit) {
        if(people == null) return 0;

        final int len = people.length;
        if(len == 1) return 1; //This is assuming problem is always solvable, i.e. no 'i' exists such that people[i] > limit

        int left = 0;
        int right = len-1;
        int boats = 0;

        Arrays.sort(people);
        while(left <= right) {
            if(people[left] + people[right] <= limit) {
                ++boats;
                ++left;
                --right;
            } else {
                ++boats;
                --right; //We are greedy on the weight per boat since count is capped at 2
            }
        }

        return boats;
    }
}