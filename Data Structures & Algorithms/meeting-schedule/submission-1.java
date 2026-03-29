/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

class Solution {
    public boolean canAttendMeetings(List<Interval> intervals) {
        if(intervals == null || intervals.size() <= 1) return true;

        Collections.sort(intervals, (a,b) -> a.start - b.start);

        // System.out.print("[");
        // for(Interval i : intervals) {
        //     System.out.print(String.format("(%d,%d),", i.start, i.end));
        // }
        // System.out.println("]");

        for(int i=1; i<intervals.size(); ++i) {
            if(intervals.get(i).start >= intervals.get(i-1).start && 
                intervals.get(i).start < intervals.get(i-1).end) {
                    return false;
                }
        }

        return true;
    }
}
