class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int c1 = 0, c2 = 0; //c = candidate (so candidate1 and candidate2)
        int f1 = 0, f2 = 0; //f = frequency (so frequency1 and frequency2 == frequency of candiate1 and 2 respectively)

        final int limit = (int) Math.floor(nums.length/3.0);

        for(int num : nums) {
            if(num == c1) {
                f1++;
                continue;
            }

            if(num == c2) {
                f2++;
                continue;
            }

            if(f1 <= f2) {
                c1 = num;
                f1 = 1;
                continue;
            }

            //else
            c2 = num;
            f2 = 1;
        }

        final List<Integer> result = new ArrayList<>();
        final int c1Copy = c1;
        final int c2Copy = c2;
        if(f1 > limit || Arrays.stream(nums).filter(x -> x == c1Copy).count() > limit) result.add(c1);
        if(f2 > limit || Arrays.stream(nums).filter(x -> x == c2Copy).count() > limit) result.add(c2);

        return result;
    }
}