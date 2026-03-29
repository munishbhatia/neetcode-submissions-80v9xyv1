class Solution {
    public boolean lemonadeChange(int[] bills) {
        final int[] stash = new int[]{0, 0, 0}; //5, 10 and 20 dollar bills in our stash at index 0, 1, and 2 respectively
        for(int bill : bills) {
            int changeToReturn = bill - 5;

            if(changeToReturn >= 20) {
                int multiples = changeToReturn/20;
                changeToReturn -= Math.min(stash[2], multiples) * 20;
                stash[2] -= Math.min(stash[2], multiples);
            }

            if(changeToReturn >= 10) {
                int multiples = changeToReturn/10;
                changeToReturn -= Math.min(stash[1], multiples) * 10;
                stash[1] -= Math.min(stash[1], multiples);
            }

            if(changeToReturn >= 5) {
                int multiples = changeToReturn/5;
                changeToReturn -= Math.min(stash[0], multiples) * 5;
                stash[0] -= Math.min(stash[0], multiples);
            }

            if(changeToReturn > 0) return false;

            switch(bill) {
                case 5: stash[0]++; break;
                case 10: stash[1]++; break;
                case 20: stash[2]++; break;
            }
        }

        return true;
    }
}

//Keep track of spare change bills of 5, 10 and 20, starting with 0
//For every customer change to given back = bills[i] - $5
//Greedily use largest bills possible to form that change to give back (e.g. 15 should be 10+5 and not 5+5+5 unless we don't have $10 bills in stash)
//Subtract the change given back from the spare change tracker
//If at any point of time the change amount to give back > 0 && we can't form that amount from the bills we have return false, else return true at the end
//E.g. we need to give back 10, and we have only single $5 bill in stash and no other bills
//Or we need to give back $5 back, but we have only $10 and $20 bills in stash and no $5 bills