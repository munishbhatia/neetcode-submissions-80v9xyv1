class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1 == null || s1.length() == 0) {
            return true; //Empty string is a part of every string
        }
        if(s2 == null || s2.length() == 0 || s2.length() < s1.length()) {
            return false; //Empty or smaller sized string cannot contain a non-empty/longer string
        }
        final Map<Character, Integer> s1FreqMap = new HashMap<>();
        final Map<Character, Integer> s2FreqMap = new HashMap<>();
        final int n = s1.length();

        //Build out s1 map
        for(char c : s1.toCharArray()) {
            s1FreqMap.put(c, s1FreqMap.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        for(int right = 0; right < s2.length(); ++right) {
            final char curr = s2.charAt(right);
            s2FreqMap.put(curr, s2FreqMap.getOrDefault(curr, 0) + 1);
            if(right < n-1) {
                continue; //We haven't seen enough chars yet
            }
            // System.out.println("Right: " + right);
            //We've seen enough chars now
            if(right >= n) {
                s2FreqMap.put(s2.charAt(left), s2FreqMap.get(s2.charAt(left)) - 1);
                if(s2FreqMap.get(s2.charAt(left)) == 0) { //I did not do this in first iteration
                    s2FreqMap.remove(s2.charAt(left));
                }
                ++left;
            }
            //Match to see if the current window is a permutation of s1
            // System.out.println("S1 keys: " + s1FreqMap.keySet());
            // System.out.println("S2 keys: " + s2FreqMap.keySet());
            boolean permutationFound = frequenciesMatch(s1FreqMap, s2FreqMap);
            if(permutationFound) {
                return true;
            }
        }
        return false;
    }

    private boolean frequenciesMatch(Map<Character, Integer> s1FreqMap,
                                Map<Character, Integer> s2FreqMap) {
        if(s2FreqMap == null) {
            return false;
        }
        if(s1FreqMap.keySet().size() != s2FreqMap.keySet().size()) {
            return false;
        }
        for(char c : s1FreqMap.keySet()) {
            if(s1FreqMap.get(c) != s2FreqMap.getOrDefault(c, 0)) {
                return false;
            }
        }
        return true;
    }
}

//Sliding window operation
//Roll a window of size = s1 on s2 and keep track of all the character counts seen in
//the current window
//If the freq map is a match, we have found a permutation of s1 in s2
//When moving a window over, drop the char count for the window start and add the char count
//for window end

//Space complexity - O(n) where n,m are sizes of strings s1 and s2 respectively
//Time complexity = O(nm)