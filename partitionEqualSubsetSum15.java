public class partitionEqualSubsetSum15 {

    // recursive (TC->O(2^n), SC->O(n))
    public static boolean solverecursiveCanPartition(int index, int sum1, int sum2, int[] nums){
        if(index == 0){
            if(sum1 == sum2+nums[index]) return true;
            if(sum1+nums[index] == sum2) return true;
            return false;
        }
        boolean notPick = solverecursiveCanPartition(index-1, sum1, sum2+nums[index], nums);
        boolean pick = solverecursiveCanPartition(index-1, sum1+nums[index], sum2, nums);

        return pick || notPick;
    }

    public static boolean recursiveCanPartition(int[] nums){
        int n = nums.length;
        return solverecursiveCanPartition(n-1, 0, 0, nums);
    }

    // memoized (TC->O(n . sum(arr)), SC->O(sum(arr) . sum(arr) + n))
    public static boolean solveMemoizedCanPartition(int index, int sum1, int sum2, int[] nums, int[][] dp){
        if(index == 0){
            if(sum1 == sum2+nums[index]){
                dp[sum1][sum2+nums[index]] = 1;
                return true;
            }
            if(sum1+nums[index] == sum2){
                dp[sum1+nums[index]][sum2] = 1;
                return true;
            }
            
            dp[sum1][sum2+nums[index]] = 0;
            dp[sum1+nums[index]][sum2] = 0;
            return false;
        }

        if(dp[sum1][sum2] != -1){
            if(dp[sum1][sum2] == 1) return true;
            if(dp[sum1][sum2] == 0) return false;
        }
       

        boolean pick = solveMemoizedCanPartition(index-1, sum1+nums[index], sum2, nums, dp);
        boolean notPick = solveMemoizedCanPartition(index-1, sum1, sum2+nums[index], nums, dp);

        if(pick || notPick){
            dp[sum1][sum2] = 1;
            return true;
        }

        dp[sum1][sum2] = 0;
        return false;

    }

    public static boolean memoizedCanPartition(int[] nums){
        int sum = 0;
        for(int i = 0; i < nums.length; i++)
            sum += nums[i];
        
        int[][] dp = new int[sum+1][sum+1];
        for(int i = 0; i < sum; i++){
            for(int j = 0; j < sum; j++){
                dp[i][j] = -1;
            }
        }

        return solveMemoizedCanPartition(nums.length-2, nums[nums.length-1], 0, nums, dp);
    }

    //  memoized (TC->O(n . sum(arr)), SC->O(n . sum(arr) + n))
    public static boolean solveMemoizedCanPartition1(int index, int sum1, int sum2, int[] nums, int[][] dp){
        if(index == 0){
            if(sum1 == sum2+nums[index]){
                dp[index][sum1] = 1;
                dp[index][sum2+nums[index]] = 1;
                return true;
            }
            if(sum1+nums[index] == sum2){
                dp[index][sum1+nums[index]]= 1;
                dp[index][sum2] = 1;
                return true;
            }

            dp[index][sum1] = 0;
            dp[index][sum2] = 0;
            dp[index][sum1+nums[index]] = 0;
            dp[index][sum2+nums[index]] = 0;
            return false;
        }

        if(dp[index][sum1] != -1 || dp[index][sum2] != -1){
            if(dp[index][sum1] == 1 || dp[index][sum2] == 1) return true;
            if(dp[index][sum1] == 0 || dp[index][sum2] == 0) return false;
        }

        boolean pick = solveMemoizedCanPartition1(index-1, sum1+nums[index], sum2, nums, dp);
        boolean notPick = solveMemoizedCanPartition1(index-1, sum1, sum2+nums[index], nums, dp);

        if(pick || notPick){
            dp[index][sum1] = 1;
            dp[index][sum2] = 1;
            return true;
        }

        dp[index][sum1] = 0;
        dp[index][sum2] = 0;
        return false;
    }

    public static boolean memoizedCanPartition1(int[] nums){
        int sum = 0;
        for(int i = 0; i < nums.length; i++)
            sum += nums[i];

        int[][] dp = new int[nums.length][sum+1];
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j <= sum; j++){
                dp[i][j] = -1;
            }
        }
        return solveMemoizedCanPartition1(nums.length-1, 0, 0, nums, dp);
    }
    
    // tabulated soln ( TC->O(n*sum(arr)), SC->O(n*sum(arr)) )
    public static boolean tabulatedCanPartition(int[] nums){
        int sum = 0;
        for(int i = 0; i < nums.length; i++)
            sum += nums[i];

        if(sum % 2 == 1) return false;
        sum = sum / 2;

        boolean[][] dp = new boolean[nums.length][sum+1];
        for(int i = 0; i < nums.length; i++)
            dp[i][0] = true;
        
        if(nums[0] <= sum) dp[0][nums[0]] = true;

        for(int index = 1; index < nums.length; index++){
            for(int target = 1; target <= sum; target++){
                boolean notPick = dp[index-1][target];
                boolean pick = false;
                if(target >= nums[index])
                    pick = dp[index-1][target-nums[index]];

                dp[index][target] = pick || notPick;
            }
        }
        return dp[nums.length-1][sum];
    }

    public static void main(String[] args) {
        int[] nums = {1,5,11,5};
        
        System.out.println(recursiveCanPartition(nums));
        System.out.println(memoizedCanPartition(nums));
        System.out.println(memoizedCanPartition1(nums));
        System.out.println(tabulatedCanPartition(nums));
    }

}
