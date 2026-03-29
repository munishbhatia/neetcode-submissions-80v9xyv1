class Solution {
    public String predictPartyVictory(String senate) {
        Stack<Character> rstack = new Stack<>();
        Stack<Character> dstack = new Stack<>();

        StringBuilder sb = new StringBuilder(senate);
        // sb.append(senate);

        // int rc = 0;
        // int dc = 0;

        boolean seenR = false;
        boolean seenD = false;

        do {
            // rc = 0;
            // dc = 0;
            seenR = false;
            seenD = false;

            for(int i=0; i<sb.length(); ++i) {
                char curr = sb.charAt(i);

                switch(curr) {
                    case 'R': seenR = true;
                            if(!rstack.isEmpty()) {
                                rstack.pop();
                                sb.setCharAt(i, '-');
                            } else {
                                // --dc;
                                // ++rc;
                                dstack.push('D');
                            }
                        break;
                    case 'D': seenD = true;
                            if(!dstack.isEmpty()) {
                                dstack.pop();
                                sb.setCharAt(i, '-');
                            } else {
                                // --rc;
                                // ++dc;
                                rstack.push('R');
                            }
                        break;
                }
            }
        } while(seenR && seenD);

        // System.out.println(sb.toString());
        // System.out.println("RC: " + rc + "; DC: " + dc);
        // return rc > dc ? "Radiant" : "Dire";
        return seenR ? "Radiant" : "Dire";
    }
}