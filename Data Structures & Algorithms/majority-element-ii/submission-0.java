class Solution {
    // public List<Integer> majorityElement(int[] nums) {
    //     // final int UPPER = 1000000000;
    //     final int len = nums.length;
    //     final int limit = (int) Math.floor(len/3.0);
    //     final int UPPER = Arrays.stream(nums).max().getAsInt();
    //     final int[] freq = new int[2*UPPER + 1];

    //     for(int num : nums) {
    //         freq[num + UPPER] ++;
    //     }

    //     return IntStream.range(0, freq.length).filter(i -> freq[i] > limit).map(i -> i - UPPER).boxed().collect(Collectors.toList());
    // }

    public List<Integer> majorityElement(int[] nums) {
        final int len = nums.length;
        final int limit = (int) Math.floor(len/3.0);
        final Map<Integer, Integer> freq = new HashMap<>();

        for(int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        return freq.entrySet().stream().filter(e -> e.getValue() > limit).map(e -> e.getKey()).toList();
    }
}