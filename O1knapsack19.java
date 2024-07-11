import java.util.Arrays;

public class O1knapsack19 {

    // recursion soln (TC -> O(2^n), SC -> O(n))
    public static int solveRecursive01Knapsack(int index, int W, int[] values, int[] weights){
        if(index == 0){
            if(W >= weights[index]) return values[0];
            return 0;
        }

        if(W < weights[index])
            return solveRecursive01Knapsack(index-1, W, values, weights);
        
        return  Math.max(
                        solveRecursive01Knapsack(index-1, W, values, weights),
                        values[index] + solveRecursive01Knapsack(index-1, W-weights[index], values, weights)
                );
    }

    public static int recursive01Knapsack(int n, int W, int[] values, int[] weights){
        return solveRecursive01Knapsack(n-1, W, values, weights);
    }

    // memoization (TC -> O(n*W), SC -> O(n*W + n))
    public static int solveMemoized01Knapsack(int index, int W, int[] values, int[] weights, int[][] dp){
        if(index == 0){
            if(W >= weights[index]) return dp[index][W] = values[index];
            return dp[index][W] = 0;
        }
        if(dp[index][W] != -1) return dp[index][W];

        if(W < weights[index])
            return dp[index][W] = solveMemoized01Knapsack(index-1, W, values, weights, dp);

        return dp[index][W] = Math.max(
                                       solveMemoized01Knapsack(index-1, W, values, weights, dp),
                                       values[index] + solveMemoized01Knapsack(index-1, W-weights[index], values, weights, dp)
        );
    }

    public static int memoized01Knapsack(int n, int W, int[] values, int[] weights){
        int[][] dp = new int[n][W+1];
        for(int i = 0; i < n; i++){
            for(int j = 0; j <= W; j++)
                dp[i][j] = -1;
        }

        return solveMemoized01Knapsack(n-1, W, values, weights, dp);
    }

    // tabulation (TC -> O(n*W), SC -> O(n*W))
    public static int tabulated01Knapsack(int n, int W, int[] values, int[] weights){
        int[][] dp = new int[n][W+1];

        for(int i = weights[0]; i <= W; i++)
            dp[0][i] = values[0];

        for(int index = 1; index < n; index++){
            for(int wt = 1; wt <= W; wt++){
                int notPick = dp[index-1][W];
                int pick = 0;
                if(wt >= weights[index])
                    pick = values[index] + dp[index-1][wt - weights[index]];
                
                dp[index][wt] = Math.max(pick, notPick);
            }
        }
        return dp[n-1][W];
    }
    
    // space optimized tablation (TC -> O(n*W), SC -> O(2*W) dp and temp)
    public static int spaceOptimizedTabulated01Knapsack(int n, int W, int[] values, int[] weights){
        int[] dp = new int[W+1];

        for(int index = 0; index < n; index++){
            int[] temp = new int[W+1];
            for(int wt = 1; wt <= W; wt++){
                int notPick = dp[wt];
                int pick = 0;
                if(wt >= weights[index])
                    pick = values[index] + dp[wt - weights[index]];

                temp[wt] = Math.max(pick, notPick);
            }
            dp = temp;
        }

        return dp[W];
    }

    // space optimized tablation (TC -> O(n*W), SC -> O(W) dp)
    public static int spaceOptimizedTabulated01Knapsack1(int n, int W, int[] values, int[] weights){
        int[] dp = new int[W+1];

        for(int index = 0; index < n; index++){
            for(int wt = W; wt >= 0; wt--){
                int notPick = dp[wt];
                int pick = 0;
                if(wt >= weights[index])
                    pick = values[index] + dp[wt - weights[index]];
                
                dp[wt] = Math.max(pick, notPick);
            }
        }
        return dp[W];
    }

    public static void main(String[] args) {
        int n = 4;
        int W = 5;
        int[] values = {5, 4, 8, 6};
        int[] weights = {1, 2, 4, 5};

        System.out.println(recursive01Knapsack(n, W, values, weights));
        System.out.println(memoized01Knapsack(n, W, values, weights));
        System.out.println(tabulated01Knapsack(n, W, values, weights));
        System.out.println(spaceOptimizedTabulated01Knapsack(n, W, values, weights));
        System.out.println(spaceOptimizedTabulated01Knapsack1(n, W, values, weights));
    }

}
