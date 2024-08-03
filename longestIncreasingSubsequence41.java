public class longestIncreasingSubsequence41 {

    // Recursive Soln: TC -> O(2^n), SC -> O(n)
    public static int solveRecursiveLengthOfLIS(int index, int prevIndex, int[] nums){
        if(index == nums.length) return 0;

        int pick = -1;
        if(prevIndex == -1 || nums[index] > nums[prevIndex])
            pick = 1 + solveRecursiveLengthOfLIS(index+1, index, nums);
        
        int notPick = 0 + solveRecursiveLengthOfLIS(index+1, prevIndex, nums);

        return Math.max(pick, notPick);
    }

    public static int recursiveLengthOfLIS(int[] nums){
        return solveRecursiveLengthOfLIS(0, -1, nums);
    }   

    // Memoized Soln: TC -> O(n^2), SC -> O(n^2 + n)
    public static int solveMemoizedLengthOfLIS(int index, int prevIndex, int[] nums, int[][] dp){
        if(index == nums.length) return 0;
        if(dp[index][prevIndex+1] != -1) return dp[index][prevIndex];

        int pick = -1;
        if(prevIndex == -1 || nums[index] > nums[prevIndex])
            pick = 1 + solveMemoizedLengthOfLIS(index+1, index, nums, dp);
        
        int notPick = 0 + solveMemoizedLengthOfLIS(index+1, prevIndex, nums, dp);

        return dp[index][prevIndex+1] = Math.max(pick, notPick);
    }

    public static int memoizedMaxProfit(int[] nums){
        int n = nums.length;
        int[][] dp = new int[n][n+1];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n+1; j++){
                dp[i][j] = -1;
            }
        }

        return solveMemoizedLengthOfLIS(0, -1, nums, dp);
    }

    // Tabulated Soln: TC -> O(n^2), SC -> O(n^2)
    public static int tabulatedLengthOfLIS(int[] nums){
        int n = nums.length;
        int[][] dp = new int[n+1][n+1];
        
        for(int index = n-1; index >= 0; index--){
            for(int prevIndex = index-1; prevIndex >= -1; prevIndex--){
                int pick = -1;
                if(prevIndex == -1 || nums[index] > nums[prevIndex]){
                    pick = 1 + dp[index+1][index+1]; 
                }
                int notPick = 0 + dp[index+1][prevIndex+1];
                dp[index][prevIndex+1] = Math.max(pick, notPick);
            }
        }
        return dp[0][0];
    }

    // Space Optimized Tabulated Soln: TC -> O(n^2), SC -> O(2n)
    public static int spaceOptimizedTabulatedLengthOfLIS(int[] nums){
        int n = nums.length;
        int[] dp = new int[n+1];
        
        for(int index = n-1; index >= 0; index--){
            int[] temp = new int[n+1];
            for(int prevIndex = index-1; prevIndex >= -1; prevIndex--){
                int pick = -1;
                if(prevIndex == -1 || nums[index] > nums[prevIndex]){
                    pick = 1 + dp[index+1]; 
                }
                int notPick = 0 + dp[prevIndex+1];
                temp[prevIndex+1] = Math.max(pick, notPick);
            }
            dp = temp;
        }
        return dp[0];
    }

   
   
    
    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};

        System.out.println(recursiveLengthOfLIS(nums));
        System.out.println(memoizedMaxProfit(nums));
        System.out.println(tabulatedLengthOfLIS(nums));
        System.out.println(spaceOptimizedTabulatedLengthOfLIS(nums));
    }

}
