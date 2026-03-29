class Solution {
    public int shipWithinDays(int[] weights, int days) {
        if(weights == null || weights.length == 0) throw new IllegalArgumentException("Weights cannot be null");
        if(days <= 0) throw new IllegalArgumentException("Days must be positive");

        final int minCapacity = Arrays.stream(weights).max().getAsInt(); //This optional should always be present in the view of checks we made above
        final int maxCapacity = Arrays.stream(weights).sum();

        //Our search space is between min and max capacities
        int left = minCapacity;
        int right = maxCapacity;
        int response = maxCapacity;

        while(left <= right) {
            int mid = left + (right-left)/2;
            int numDays = getDaysToShipWithCapacity(weights, mid);

            if(numDays <= days) { //We want to get the lower bound on the capacity
                response = Math.min(response, mid);
                right = mid-1; //We can further REDUCE the max capacity of the ship
            } else {
                left = mid+1; //We took more days than we have, we need to increase the capacity of the ship
            }
        }

        return response;
    }

    private int getDaysToShipWithCapacity(int[] weights, int capacity) {
        int days = 0;
        int currSum = 0;

        for(int i=0; i<weights.length; ++i) {
            currSum += weights[i];
            if(currSum > capacity) {
                ++days;
                currSum = weights[i];
            }
        }

        if(currSum > 0) ++days;
        // System.out.println(String.format("It'll take %d days to ship the weights with max capacity of %d", days, capacity));
        return days;
    }
}