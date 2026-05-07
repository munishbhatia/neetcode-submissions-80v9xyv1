class Solution {
    public List<List<String>> partition(String s) {
        if(s == null || s.length() == 0) return new ArrayList<>();

        final List<List<String>> result = new ArrayList<>();
        partitionHelper(s, s.length(), 0, new LinkedList<>(), result);

        return result;
    }

    private void partitionHelper(String input, int len, int index, List<String> curr, List<List<String>> result) {
        if(index < 0 || index > len) return;

        if(index == len) {
            result.add(new ArrayList(curr));
            return;
        }

        final char startChar = input.charAt(index);
        for(int i = index; i < len; ++i) {
            String sub = input.substring(index, i+1); //i+1 because end index is exclusive in the substring method
            if(input.charAt(i) != startChar || !isPalindrome(sub)) continue; //First check on the char match prevents O(N) palindrome checks on substring that will definitely not be palindromes since the first and last chars don't match

            curr.addLast(sub);
            partitionHelper(input, len, i+1, curr, result);

            //Backtrack
            curr.removeLast();
        }
    }

    private boolean isPalindrome(String input) {
        if(input == null) return false;

        final int len = input.length();
        
        if(len == 0) return false;
        if(len == 1) return true;

        int left = 0, right = len - 1;
        while(left < right) {
            if(input.charAt(left++) != input.charAt(right--)) return false;
        }

        return true;
    }
}
