import java.util.*;

public class burstBalloons50 {

    // My Soln
    public static int solveRecursiveMaxCoins(ArrayList<Integer> nums){
        if(nums.size() == 2) return 0;
        
        int maxCost = 0;
        for(int k = 1; k < nums.size()-1; k++){
            int cost = nums.get(k-1) * nums.get(k) * nums.get(k+1);
            int balloon = nums.remove(k);
            cost += solveRecursiveMaxCoins(nums);
            nums.add(k, balloon);
            maxCost = Math.max(maxCost, cost);
        }

        return maxCost;
    }

    public static int recursiveMaxCoins0(int[] nums){
        int n = nums.length;
        ArrayList<Integer> extendedNums = new ArrayList<>();

        extendedNums.add(1);

        for(int i = 0; i < n; i++)
            extendedNums.add(nums[i]);

        extendedNums.add(1);
        
        return solveRecursiveMaxCoins(extendedNums);
    }

    // Striver's Soln

    // Recursive Soln: TC -> O(2^n), SC -> O(n)
    public static int solveRecursiveMaxCoins(int i, int j, int[] extendedNums){
        if(i > j) return 0;
        
        int mxCost = 0;
        for(int k = i; k <= j; k++){
            mxCost = Math.max(mxCost, extendedNums[i-1] * extendedNums[k] * extendedNums[j+1]
                                      + solveRecursiveMaxCoins(i, k-1, extendedNums)
                                      + solveRecursiveMaxCoins(k+1, j, extendedNums));
        }
        return mxCost;
    }

    public static int recursiveMaxCoins(int[] nums){
        int n = nums.length;
        int[] extendedNums = new int[n+2];
        extendedNums[0] = 1;
        for(int i = 0; i < nums.length; i++)
            extendedNums[i+1] = nums[i];
        extendedNums[n+1] = 1;

        return solveRecursiveMaxCoins(1, n, extendedNums);
    }

    // Memoized Soln: TC -> O(n^3), SC -> O(n^n + n)
    public static int solveMemoizedMaxCoins(int i, int j, int[] extendedNums, int[][] dp){
        if(i > j) return 0;
        if(dp[i][j] != -1) return dp[i][j];

        int mxCost = 0;
        for(int k = i; k <= j; k++){
            mxCost = Math.max(mxCost, extendedNums[i-1] * extendedNums[k] * extendedNums[j+1] 
                                      + solveMemoizedMaxCoins(i, k-1, extendedNums, dp) 
                                      + solveMemoizedMaxCoins(k+1, j, extendedNums, dp));
        }
        return dp[i][j] = mxCost;
    }

    public static int memoizedMaxCoins(int[] nums){
        int n = nums.length;
        int[] extendedNums = new int[n+2];
        extendedNums[0] = 1;
        for(int i = 0; i < n; i++)
            extendedNums[i+1] = nums[i];
        extendedNums[n+1] = 1;

        int[][] dp = new int[n+1][n+1];

        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= n; j++){
                dp[i][j] = -1;
            }
        }

        return solveMemoizedMaxCoins(1, n, extendedNums, dp);
    }

    // Tabulated Soln: TC -> O(n^3), SC -> O(n^2)
    public static int tabulatedMaxCoins(int[] nums){
        int n = nums.length;
        int[] extendedNums = new int[n+2];
        extendedNums[0] = 1;
        for(int i = 0; i < n; i++)
            extendedNums[i+1] = nums[i];
        extendedNums[n+1] = 1;

        int[][] dp = new int[n+2][n+2];

        for(int i = n; i >= 1; i--){
            for(int j = i; j <= n; j++){
                dp[i][j] = Integer.MIN_VALUE;

                for(int k = i; k <= j; k++){
                    dp[i][j] = Math.max(dp[i][j], extendedNums[i-1]*extendedNums[k]*extendedNums[j+1]
                                                  + dp[i][k-1] + dp[k+1][j]);
                }
            }
        }

        return dp[1][n];
    }
    
    public static void main(String[] args) {
        int[] nums = {3,1,5,8};

        System.out.println(recursiveMaxCoins0(nums));

        System.out.println(recursiveMaxCoins(nums));
        System.out.println(memoizedMaxCoins(nums));
        System.out.println(tabulatedMaxCoins(nums));
    }

}
