import java.util.*;

public class countSubsetsWithSumK17 {

    // recurive soln (TC -> O(2^n), SC -> O(n))
    public static int solveRecursiveCountSubsetsWithSumK(int index, int sum, int[] nums){
        if(index < 0) return sum == 0 ? 1 : 0;
        if(sum < 0) return 0;

        int pick = solveRecursiveCountSubsetsWithSumK(index-1, sum-nums[index], nums);
        int notPick = solveRecursiveCountSubsetsWithSumK(index-1, sum, nums);

        return pick + notPick;
    }

    public static int recursiveCountSubsetsWithSumK(int k, int[] nums){
        return solveRecursiveCountSubsetsWithSumK(nums.length-1, k, nums);
    }

    // recurive soln (TC -> O(2^n), SC -> O(n))
    public static int solveRecursiveCountSubsetsWithSumK1(int index, int sum, int[] nums){
        if(index < 0){
            if(sum == 0) return 1;
            return 0;
        }

        int notPick = solveRecursiveCountSubsetsWithSumK1(index-1, sum, nums);
        int pick = 0;
        if(sum >= nums[index])
            pick = solveRecursiveCountSubsetsWithSumK1(index-1, sum-nums[index], nums);

        return pick + notPick;
    }

    public static int recursiveCountSubsetsWithSumK1(int k, int[] nums){
        return solveRecursiveCountSubsetsWithSumK1(nums.length-1, k, nums);
    }

    public static int solveMemoizedCountSubsetsWithSumK(int index, int sum, int[] nums, int[][] dp){
        if(index < 0){
            if(sum == 0) return 1;
            return 0;
        }
        if(dp[index][sum] != -1) return dp[index][sum];

        int notPick = solveMemoizedCountSubsetsWithSumK(index-1, sum, nums, dp);
        int pick = 0;
        if(sum >= nums[index])
            pick = solveMemoizedCountSubsetsWithSumK(index-1, sum-nums[index], nums, dp);
        
        return dp[index][sum] = pick + notPick;
    }

    public static int memoizedCountSubsetsWithSumK(int k, int[] nums){
        int[][] dp = new int[nums.length][k+1];
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j <= k; j++){
                dp[i][j] = -1;
            }
        }
        return solveMemoizedCountSubsetsWithSumK(nums.length-1, k, nums, dp);
    }

    public static int tabulatedCountSubsetsWithSumK(int k, int[] nums){
        Arrays.sort(nums);

        int low = 0, high = nums.length-1;
        while(low < high){
            int temp = nums[low];
            nums[low] = nums[high];
            nums[high] = temp;
            low++;
            high--;
        }


        int[][] dp = new int[nums.length][k+1];

        for(int i = 0; i < nums.length; i++)
            dp[i][0] = 1;
        
        if(nums[0] <= k) dp[0][nums[0]] = 1;


        for(int index = 1; index < nums.length; index++){
            for(int target = 1; target <= k; target++){
                int notPick = dp[index-1][target];
                int pick = 0;
                if(target >= nums[index]) pick = dp[index-1][target-nums[index]];
                dp[index][target] = pick + notPick;
            }
        }
        return dp[nums.length-1][k];
    }

    public static int spaceOptimizedTabulatedCountSubsetsWithSumK(int k, int[] nums){
        Arrays.sort(nums);
        int low = 0, high = nums.length-1;
        while(low < high){
            int temp = nums[low];
            nums[low] = nums[high];
            nums[high] = temp;
            low++;
            high--;
        }

        int[] dp = new int[k+1];
        dp[0] = 1;
        if(nums[0] <= k) dp[nums[0]] = 1;

        for(int index = 1; index < nums.length; index++){
            int[] temp = new int[k+1];
            for(int target = 1; target <= k; target++){
                int notPick = dp[target];
                int pick  = 0;
                if(target >= nums[index])
                    pick = dp[target-nums[index]];
                temp[target] = pick + notPick;
            }
            dp = temp;
        }
        return dp[k];
    }
    
    public static void main(String[] args) {
        // int k = 10;
        // int[] nums = {5, 2, 3, 10, 6, 8};

        int k = 1;
        int[] nums = {0, 0, 0, 0, 0, 0, 0, 0, 1};


        System.out.println(recursiveCountSubsetsWithSumK(k, nums));
        System.out.println(recursiveCountSubsetsWithSumK1(k, nums));
        System.out.println(memoizedCountSubsetsWithSumK(k, nums));
        System.out.println(tabulatedCountSubsetsWithSumK(k, nums));
        System.out.println(spaceOptimizedTabulatedCountSubsetsWithSumK(k, nums));
    }

}
