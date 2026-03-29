class Solution {
    public String predictPartyVictory(String senate) {
        Stack<Character> rstack = new Stack<>();
        Stack<Character> dstack = new Stack<>();

        StringBuilder sb = new StringBuilder(senate);
        // sb.append(senate);

        int rc = 0;
        int dc = 0;

        for(int j=0; j<5; ++j) {
            rc = 0;
            dc = 0;

            for(int i=0; i<sb.length(); ++i) {
                char curr = sb.charAt(i);

                switch(curr) {
                    case 'R': if(!rstack.isEmpty()) {
                                rstack.pop();
                                sb.setCharAt(i, '-');
                            } else {
                                // --dc;
                                ++rc;
                                dstack.push('D');
                            }
                        break;
                    case 'D': if(!dstack.isEmpty()) {
                                dstack.pop();
                                sb.setCharAt(i, '-');
                            } else {
                                // --rc;
                                ++dc;
                                rstack.push('R');
                            }
                        break;
                }
            }
        }

        System.out.println(sb.toString());
        System.out.println("RC: " + rc + "; DC: " + dc);
        return rc > dc ? "Radiant" : "Dire";
    }
}