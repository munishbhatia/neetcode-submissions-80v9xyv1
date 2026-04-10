class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        if(triplets == null || triplets.length == 0) return false;

        final int len = triplets.length;

        List<int[]> formativeTriplets = Arrays.stream(triplets).filter(triplet -> isFormativeTriplet(triplet, target)).collect(Collectors.toList());
        if(formativeTriplets == null || formativeTriplets.size() == 0) return false;

        List<int[]> curr = formativeTriplets;
        List<int[]> next = new ArrayList<>();

        for(int[] t : formativeTriplets) {
            System.out.println(Arrays.toString(t));
        }

        // for(int i=0; i<curr.size() - 1; ++i) {
        //     for(int j=i+1; j<curr.size(); ++j) {
        //         if(isGoodMerge(formativeTriplets.get(i), formativeTriplets.get(j), target)) {
        //             int[] mergedTriplet = mergeTriplets(formativeTriplets.get(i), formativeTriplets.get(j));
        //             if(areTripletsEqual(mergedTriplet, target)) return true;
        //             next.add(mergedTriplet);
        //         }
        //     }
        //     curr = next;
        // }

        int[] mush = curr.get(0);

        for(int i = 1; i < curr.size(); ++i) {
            if(isGoodMerge(mush, curr.get(i), target)) {
                mush = mergeTriplets(mush, curr.get(i));
                if(areTripletsEqual(mush, target)) return true;
            }
        }

        // for(int[] triplet : curr) {
        //     if(areTripletsEqual(triplet, target)) return true;
        // }

        // return false;

        return areTripletsEqual(mush, target);
    }

    private boolean isFormativeTriplet(int[] triplet, int[] target) {
        // return (triplet[0] <= target[0]) && (triplet[1] <= target[1]) && (triplet[2] <= target[2]);
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

    private boolean isGoodMerge(int[] t1, int[] t2, int[] target) {
        // System.out.println("Checking good merge: " + Arrays.toString(t1) + " and " + Arrays.toString(t2));

        int[] merged = mergeTriplets(t1, t2);

        int similarityCount1 = getSimilarityCount(t1, target);

        int similarityCount2 = getSimilarityCount(t2, target);

        int similarityCount3 = getSimilarityCount(merged, target);

        boolean result = (similarityCount3 > similarityCount1) && (similarityCount3 > similarityCount2);

        // System.out.println("Returning " + result);

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
