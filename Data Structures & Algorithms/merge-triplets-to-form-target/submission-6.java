class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        if(triplets == null || triplets.length == 0) return false;

        boolean x = false, y = false, z = false;

        for(int[] t : triplets) {
            if(!hasLargerElement(t, target)) {
                x |= t[0] == target[0];
                y |= t[1] == target[1];
                z |= t[2] == target[2];
            }

            if(x && y && z) return true;
        }

        return false;
    }

    private boolean hasLargerElement(int[] triplet, int[] target) {
        for(int i=0; i<3; ++i) {
            if(triplet[i] > target[i]) return true;
        }
        return false;
    }
}
