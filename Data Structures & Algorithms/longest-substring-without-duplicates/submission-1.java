class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        int leftIdx = 0;
        int maxLenWithoutRepeat = 0;
        final Set<Character> tracker = new HashSet<>();

        for(int rightIdx = 0; rightIdx < s.length(); ++rightIdx) {
            char curr = s.charAt(rightIdx);
            if(tracker.contains(curr)) {
                //Shrink the window
                while(leftIdx < rightIdx && s.charAt(leftIdx) != curr) {
                    tracker.remove(s.charAt(leftIdx));
                    ++leftIdx;
                }
                //Shrink one more time
                ++leftIdx;
                continue;
            }
            //We have not seen this char before
            //Add it to the tracker
            tracker.add(curr);
            //update maxLenWithoutRepeat
            maxLenWithoutRepeat = Math.max(maxLenWithoutRepeat, rightIdx-leftIdx+1);
        }
        return maxLenWithoutRepeat;
    }
}

//We'll need to traverse the input string and keep track of all the characters
//we have seen in the current window

//As long as we keep seeing chars that have not occurred in the past we keep expanding
//the window and updating the longest substring found without dupe chars

//When we see a character that we have seen in the past, we start shrinking the window
//to the point where char[leftIdx] == char[rightIdx] and then shrink by another
//There can only ever be one such char otherwise we would have violated the requirement
//already in the past

//We then keep expanding the window and updating the longest substring and so on
