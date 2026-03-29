class Solution {
    private static final int SIZE = 58; //26 lowercase + 26 uppercase + 6 chars in between = 52 + 6 = 58
    public String minWindow(String s, String t) {
        if(t == null || t.length() == 0) {
            return "";
        }
        if(s == null || s.length() == 0 || s.length() < t.length()) {
            return "";
        }
        final int[] sfreq = new int[SIZE];
        final int[] tfreq = new int[SIZE];

        //Initialize the frequencies
        for(int i=0; i<t.length(); ++i) {
            sfreq[s.charAt(i) - 'A']++;
            tfreq[t.charAt(i) - 'A']++;
        }

        boolean isMatch = isMatch(sfreq, tfreq);

        if(isMatch) { //We got a match at the start itself, and window size = |t| so can't go smaller than this, return
            return s.substring(0, t.length());
        }

        //Expand window
        int left = 0;
        int minStart = 0;
        int minEnd = s.length()-1;
        boolean matchFound = false;
        
        for(int right = t.length(); right < s.length(); ++right) { //We start at t.length() since we have already calculated matches up until there
            int ridx = s.charAt(right) - 'A';
            sfreq[ridx]++;

            isMatch = isMatch(sfreq, tfreq);

            if(isMatch) {
                matchFound = true;
                int prevMin = minEnd - minStart + 1;
                if(prevMin > (right - left + 1)) {
                    minStart = left;
                    minEnd = right;
                }

                //Squeeze window from left and try
                while(left < right) {
                    int lidx = s.charAt(left) - 'A';
                    sfreq[lidx]--;
                    ++left;

                    isMatch = isMatch(sfreq, tfreq);

                    if(!isMatch) break;

                    prevMin = minEnd - minStart + 1;
                    if(prevMin > (right - left + 1)) {
                        minStart = left;
                        minEnd = right;
                    }
                }
            }
        }
        if(matchFound) {
            return s.substring(minStart, minEnd+1);
        }
        return "";
    }

    private boolean isMatch(int[] sfreq, int tfreq[]) {
        for(int i=0; i<SIZE; ++i) {
            if(tfreq[i] != 0 && sfreq[i] < tfreq[i]) {
                // System.out.println(String.format("FALSE: tfreq: [%d]; sfreq: [%d]; i=%d", tfreq[i], sfreq[i], i));
                return false;
            }
        }
        return true;
    }
}

//1. Start a window from left end of the string
//2. Keep expanding the window on the right till we see a full char freq match
    //2.1 Record this window length and possibly update the min window length
    //2.2 Record window start and end for this min
//3. Start shrinking the window from the left and update the min window length till 2 no longer stands true
    //3.1 Record window start and end for this min
//4. Repeat steps 2 and 3
//5. Return min window substring captured

//0. Base case - if |s| == 0 or |s| < |t| - return empty string
