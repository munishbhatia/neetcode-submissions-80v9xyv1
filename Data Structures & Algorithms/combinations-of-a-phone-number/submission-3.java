class Solution {
    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0) return new ArrayList<>();

        final Map<Character, List<String>> digitCharMap = getDigitCharacterMap();
        final int len = digits.length();

        final List<String> response = new LinkedList<>();

        letterCombinationsBacktrack(digits, 0, digitCharMap, new StringBuilder(), response);

        return response;
    }

    private void letterCombinationsBacktrack(String digits, int index, Map<Character, List<String>> digitCharMap, StringBuilder current, List<String> response) {
        if(index < 0) return; //Mostly a safeguard against errors

        if(index == digits.length()) {
            response.add(current.toString());
            return;
        }

        for(String s : digitCharMap.get(digits.charAt(index))) {
            current.append(s); //Build
            letterCombinationsBacktrack(digits, index+1, digitCharMap, current, response); //Recurse
            current.deleteCharAt(index); //Backtrack
        }
    }

    private Map<Character, List<String>> getDigitCharacterMap() {
        final Map<Character, List<String>> digitCharMap = new HashMap<>();

        digitCharMap.put('2', List.of("a", "b", "c"));
        digitCharMap.put('3', List.of("d", "e", "f"));
        digitCharMap.put('4', List.of("g", "h", "i"));
        digitCharMap.put('5', List.of("j", "k", "l"));
        digitCharMap.put('6', List.of("m", "n", "o"));
        digitCharMap.put('7', List.of("p", "q", "r", "s"));
        digitCharMap.put('8', List.of("t", "u", "v"));
        digitCharMap.put('9', List.of("w", "x", "y", "z"));

        return digitCharMap;
    }
}
