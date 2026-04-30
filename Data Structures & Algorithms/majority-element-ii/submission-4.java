class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int c1 = 0, c2 = 0; //c = candidate (so candidate1 and candidate2)
        int f1 = 0, f2 = 0; //f = frequency (so frequency1 and frequency2 == frequency of candiate1 and 2 respectively)

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

        final int c1Copy = c1;
        final int c2Copy = c2;
        final int limit = (int) Math.floor(nums.length/3.0);
        
        return Arrays.stream(nums)
            .filter(x -> x == c1Copy || x == c2Copy)
            .boxed()
            .collect(
                Collectors.groupingBy(
                    java.util.function.Function.identity(),
                    Collectors.counting()
                )
            )
            .entrySet().stream().filter(e -> e.getValue() > limit)
            .map(e -> e.getKey())
            .toList();
    }
}