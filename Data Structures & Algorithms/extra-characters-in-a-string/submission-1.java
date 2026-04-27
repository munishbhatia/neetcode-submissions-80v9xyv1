class Solution {
    public int minExtraChar(String s, String[] dictionary) {
        final Set<String> dict = Arrays.stream(dictionary).collect(Collectors.toSet());
        return helper(new HashMap<>(), dict, s);
    }

    private int helper(Map<String, Integer> cache, Set<String> dict, String s) {
        if(s == null || s == " ") return 0;
        if(cache.containsKey(s)) return cache.get(s);

        int min = s.length();
        for(int i = 0; i < s.length(); ++i) {
            String sc = s.substring(0, i+1);
            int curr = dict.contains(sc) ? 0 : (i + 1);
            curr += helper(cache, dict, s.substring(i+1));
            min = Math.min(min, curr);
        }

        cache.put(s, min);
        return min;
    }
}