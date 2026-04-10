class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        if(triplets == null || triplets.length == 0) return false;

        List<int[]> formativeTriplets = Arrays.stream(triplets).filter(triplet -> isFormativeTriplet(triplet, target)).collect(Collectors.toList());
        if(formativeTriplets == null || formativeTriplets.size() == 0) return false;

        int[] mush = formativeTriplets.get(0);

        for(int i = 1; i < formativeTriplets.size(); ++i) {
            if(isGoodMerge(mush, formativeTriplets.get(i), target)) {
                mush = mergeTriplets(mush, formativeTriplets.get(i));
                if(areTripletsEqual(mush, target)) return true;
            }
        }

        return areTripletsEqual(mush, target);
    }

    private boolean isFormativeTriplet(int[] triplet, int[] target) {
        return (getSimilarityCount(triplet, target) > 0) && (!hasLargerElement(triplet, target));
    }

    private boolean hasLargerElement(int[] triplet, int[] target) {
        for(int i=0; i<3; ++i) {
            if(triplet[i] > target[i]) return true;
        }
        return false;
    }

    private int getSimilarityCount(int[] t1, int[] t2) {
        int similarityCount = 0;

        for(int i=0; i<3; ++i) {
            if(t1[i] == t2[i]) ++similarityCount;
        }

        return similarityCount;
    }

    /*
    It is a good merge iff the similarityCount of the merged triplet with target is greater than
    the similarity counts of each of the original triplets with the target

    There is no benefit in merging if we are not getting closer to the target
    */
    private boolean isGoodMerge(int[] t1, int[] t2, int[] target) {
        int[] merged = mergeTriplets(t1, t2);

        int mergedSimilarityCount = getSimilarityCount(merged, target);

        boolean result = (mergedSimilarityCount > getSimilarityCount(t1, target)) && 
            (mergedSimilarityCount > getSimilarityCount(t2, target));

        return result;
    }

    private int[] mergeTriplets(int[] t1, int[] t2) {
        int[] result = new int[3];

        for(int i=0; i<3; ++i) {
            result[i] = Math.max(t1[i], t2[i]);
        }

        return result;
    }

    private boolean areTripletsEqual(int[] t1, int[] t2) {
        return t1[0] == t2[0] && t1[1] == t2[1] && t1[2] == t2[2];
    }
}
