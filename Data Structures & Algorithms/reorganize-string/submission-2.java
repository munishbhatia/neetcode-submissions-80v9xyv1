class Solution {
    public String reorganizeString(String s) {
        if(s == null || s.length() <= 1) return s;

        final PriorityQueue<PQElement> pq = new PriorityQueue<>(
            Comparator.comparingInt(PQElement::getFrequency).reversed()
        );

        final int len = s.length();
        final int[] freq = new int[26]; //O(1) space; O(d) in general where d = length of the alphabet input is formed over
        for(char c : s.toCharArray()) { //O(N) operation; N = length of the input string
            freq[c-'a']++;
        }

        for(int i=0; i<26; ++i) { //O(d) time
            if(freq[i] > 0) {
                pq.offer(new PQElement((char)('a' + i), freq[i])); //O(d) space
            }
        }

        if(pq.peek().getFrequency() > Math.ceil(len/2.0)) {
            return "";
        }

        final StringBuilder sb = new StringBuilder();
        PQElement prev = null;

        while(!pq.isEmpty()) { //O(Nlogd) operations in this loop
            PQElement curr = pq.poll();
            sb.append(curr.getChar());

            if(prev != null) {
                pq.offer(new PQElement(prev.getChar(), prev.getFrequency()-1));
                prev = null;
            }

            if(curr.getFrequency() > 1) prev = curr;
        }

        if(prev != null) sb.append(prev.getChar());
        return sb.toString();
    }

    public class PQElement {
        char c;
        int frequency;

        public PQElement(char ci, int freqi) {
            c = ci;
            frequency = freqi;
        }

        public char getChar() {
            return c;
        }

        public int getFrequency() {
            return frequency;
        }
    }
}