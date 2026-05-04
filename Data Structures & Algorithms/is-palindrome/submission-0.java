class Solution {
    public boolean isPalindrome(String s) {
        if(s == null) return true;

        final int len = s.length();
        if(len <= 1) return true;

        String snospace = s.replace(" ", "").toLowerCase();

        // System.out.println(snospace);

        int left = 0;
        int right = snospace.length() - 1;

        while(left < right) {
            if(!Character.isLetterOrDigit(snospace.charAt(left))) {
                // System.out.println("Skipping " + snospace.charAt(left));
                ++left;
                continue;
            }
            if(!Character.isLetterOrDigit(snospace.charAt(right))) {
                // System.out.println("Skipping " + snospace.charAt(right));
                --right;
                continue;
            }
            if(snospace.charAt(left) != snospace.charAt(right)) {
                // System.out.println("Mismatch " + snospace.charAt(left) + " and " + snospace.charAt(right));
                return false;
            }
            ++left;
            --right;
        }

        return true;
    }
}
