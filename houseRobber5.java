public class houseRobber5 {

    // My Soln 
    // Recursive Func (TC -> O(2^n), SC -> O(N))
    public static void recursiveRob(int index, int sum, int[] ans, int[] nums){
        if(index >= nums.length){
            ans[0] = Math.max(ans[0], sum);
            return;
        }
        recursiveRob(index+1, sum, ans, nums);
        sum += nums[index];
        recursiveRob(index+2, sum, ans, nums);
    }

    // recursive func (TC -> O(2^n), SC -> O(N))
    public static int recursiveRob(int index, int[] nums){
        if(index < 0) return 0;
        if(index == 0) return nums[0];
        int pick = nums[index] + recursiveRob(index-2, nums);
        int notPick = 0 + recursiveRob(index-1, nums);
        return Math.max(pick, notPick);
    }

    // memoized solution (TC -> O(n), SC -> O(2N))
    public static int solveMeoizedRob(int index, int[] dp, int[] nums){
        if(index < 0) return 0;
        if(index == 0) return nums[0];
        if(dp[index] != -1) return dp[index];
        int pick = nums[index] + solveMeoizedRob(index-2, dp, nums);
        int notPick = 0 + solveMeoizedRob(index-1, dp, nums);
        return dp[index] = Math.max(pick, notPick);
    }

    public static int memoizedRob(int[] nums){
        int[] dp = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            dp[i] = -1;
        }

        solveMeoizedRob(nums.length-1, dp, nums);
        return dp[nums.length-1];
    }

    // My Soln
    // tabular soln (TC -> O(n), SC -> O(n))
    public static int tabulatedRob(int[] nums){
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);
        if(nums.length == 3) return Math.max(nums[1], nums[0]+nums[2]);

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        dp[2] = Math.max(nums[1], nums[0]+nums[2]);

        for(int i = 3; i < nums.length; i++){
            dp[i] = nums[i] + Math.max(dp[i-2], dp[i-3]);
        }

        return dp[nums.length-1];
    }

    // tabular soln (TC -> O(n), SC -> O(n))
    public static int tabulatedRob2(int[] nums){
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        
        for(int i = 1; i < nums.length; i++){
            int pick = nums[i] + (i > 1 ? dp[i-2] : 0);
            int notPick = 0 + dp[i-1];
            dp[i] = Math.max(pick, notPick);
        }

        return dp[nums.length-1];
    }

    // space optimized soln (TC -> O(n), SC -> O(1))
    public static int spaceOptimizedTabulatedRob(int[] nums){
        int pick = 0;
        int notPick = 0;
        
        for(int i = 0; i < nums.length; i++){
            int rob = notPick + nums[i];
            int noRob = Math.max(pick, notPick);

            pick = rob;
            notPick = noRob;
        }
        return Math.max(pick, notPick);
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 1, 2};
        int[] ans = {0};
        recursiveRob(0, 0, ans, nums);
        System.out.println(ans[0]);
        System.out.println(recursiveRob(nums.length-1, nums));
        System.out.println(memoizedRob(nums));
        System.out.println(tabulatedRob(nums));
        System.out.println(tabulatedRob2(nums));
        System.out.println(spaceOptimizedTabulatedRob(nums));
    }
    
}
