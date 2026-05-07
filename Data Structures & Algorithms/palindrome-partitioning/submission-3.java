class Solution {
    public List<List<String>> partition(String s) {
        if(s == null || s.length() == 0) return new ArrayList<>();

        final List<List<String>> result = new ArrayList<>();
        
        final boolean[][] palindromeCheck = precomputePalindromes(s);
        partitionHelperWithPrecomputedChecks(s, s.length(), 0, palindromeCheck, new LinkedList<>(), result);

        return result;
    }

    private void partitionHelperWithPrecomputedChecks(String input, int len, int index, boolean[][] palindromeCheck, List<String> curr, List<List<String>> result) {
        if(index < 0 || index > len) return;

        if(index == len) {
            result.add(new ArrayList(curr));
            return;
        }

        final char startChar = input.charAt(index);
        for(int i = index; i < len; ++i) {
            if(!palindromeCheck[index + 1][i + 1]) continue; //First check on the char match prevents O(N) palindrome checks on substring that will definitely not be palindromes since the first and last chars don't match

            String sub = input.substring(index, i+1); //i+1 because end index is exclusive in the substring method
            curr.addLast(sub);
            partitionHelperWithPrecomputedChecks(input, len, i+1, palindromeCheck, curr, result);

            //Backtrack
            curr.removeLast();
        }
    }

    private boolean[][] precomputePalindromes(String input) {
        final int len = input.length();
        final boolean[][] palindromeCheck = new boolean[len + 1][len + 1];

        Arrays.fill(palindromeCheck[0], true);
        for(int i = 0; i <= len; ++i) {
            palindromeCheck[i][0] = true;
        }

        for(int i = len-1; i >= 0; --i) {
            for(int j = i; j < len; ++j) {
                palindromeCheck[i + 1][j + 1] = (i+2 > j ? true : palindromeCheck[i + 2][j]) && (input.charAt(i) == input.charAt(j));
                // System.out.println(String.format("i: %c; j: %c; equal: %b; sub: %b", input.charAt(i), input.charAt(j), (input.charAt(i) == input.charAt(j)), (i+2 > j ? true : palindromeCheck[i + 2][j])));
            }
        }
        
        return palindromeCheck;
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
