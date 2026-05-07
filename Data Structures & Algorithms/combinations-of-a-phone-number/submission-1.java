class Solution {
    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0) return new ArrayList<>();

        final Map<Character, List<String>> digitCharMap = getDigitCharacterMap();
        final int len = digits.length();

        List<String> response = new LinkedList<>();
        for(String s : digitCharMap.get(digits.charAt(0))) {
            response.add(s);
        }
        
        for(int i=1; i < len; ++i) {
            List<String> temp = new LinkedList<>();
            for(String s : digitCharMap.get(digits.charAt(i))) {
                for(String sb : response) {
                    temp.add(sb + s);
                }
            }
            response = temp;
        }

        return response;
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
