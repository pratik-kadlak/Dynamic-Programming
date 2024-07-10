// You are given an array 'arr' containing 'n' non-negative integers.
// Your task is to partition this array into two subsets such that the absolute difference between subset sums is minimum.
// You just need to find the minimum absolute difference considering any valid division of the array elements.    

public class partitionIntoTwoWithMinAbsDiff16 {

    public static int tabulatedPartitionWithMinDiff(int n, int[] arr){
        int sum = 0;
        for(int it: arr)
            sum += it;

        boolean[][] dp = new boolean[n][sum+1];
        for(int i = 0; i < n; i++)
            dp[i][0] = true;

        if(arr[0] <= sum) dp[0][arr[0]] = true;

        for(int index = 1; index < n; index++){
            for(int target = 1; target <= sum; target++){
                boolean notPick = dp[index-1][target];
                boolean pick = false;
                if(target >= arr[index]) pick = dp[index-1][target-arr[index]];
                dp[index][target] = pick || notPick;
            }
        }

        int minDiff = Integer.MAX_VALUE;
        for(int i = 0; i <= sum; i++){
            if(dp[n-1][i]){
                int s1 = i;
                int s2 = sum - i;
                int currDiff = Math.abs(s1-s2);
                minDiff = Math.min(minDiff, currDiff);
            }
        }
        return minDiff;
    }

    public static void main(String[] args) {
        int[] nums = {3,9,7,3};

        System.out.println(tabulatedPartitionWithMinDiff(nums.length, nums));
    }

}
