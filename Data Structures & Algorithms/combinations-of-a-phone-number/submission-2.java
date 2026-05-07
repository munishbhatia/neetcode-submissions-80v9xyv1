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
        digitCharMap.put('4', List.of("G", "H", "I").stream().map(String::toLowerCase).toList());
        digitCharMap.put('5', List.of("J", "K", "L").stream().map(String::toLowerCase).toList());
        digitCharMap.put('6', List.of("M", "N", "O").stream().map(String::toLowerCase).toList());
        digitCharMap.put('7', List.of("P", "Q", "R", "S").stream().map(String::toLowerCase).toList());
        digitCharMap.put('8', List.of("T", "U", "V").stream().map(String::toLowerCase).toList());
        digitCharMap.put('9', List.of("W", "X", "Y", "Z").stream().map(String::toLowerCase).toList());

        return digitCharMap;
    }
}
