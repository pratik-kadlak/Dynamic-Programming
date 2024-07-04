import java.util.Arrays;

public class frogJumpKDist4 {

    // brute force soln (TC -> O(k^n), SC -> O(n))
    public static int recursiveFrogJumpK(int n, int k, int[] heights){
        if(n <= 0) return 0;
        int minEnergy = Integer.MAX_VALUE;
        for(int i = n-1; i >= 0 && i > n-1-k; i--){
            minEnergy = Math.min(minEnergy, Math.abs(heights[n]-heights[i]) + recursiveFrogJumpK(i, k, heights));
        }

        return minEnergy;
    }

    public static int solveMemoizedFrogJumpK(int n, int k, int[] heights, int[] dp){
        if(n <= 0) return 0;
        if(dp[n] != 0) return dp[n];
        
        int minEnergy = Integer.MAX_VALUE;
        for(int i = n-1; i >= 0 && i > n-1-k; i--){
            minEnergy = Math.min(minEnergy, Math.abs(heights[n]-heights[i])+solveMemoizedFrogJumpK(i, k, heights, dp));
        }

        return dp[n] = minEnergy;

    }

    // memoization solution (TC -> O(n*k), SC -> O(2N)) 
    public static int memoizedFrogJumpK(int n, int k, int[] heights){
        int[] dp = new int[n];
        for(int i = 0; i < n; i++){
            dp[i] = 0;
        }
        solveMemoizedFrogJumpK(n-1, k, heights, dp);
        return dp[n-1];
    }

    // tabular solution (TC -> O(n*k), SC -> O(N))
    public static int tabulatedFrogJumpK(int n, int k, int[] heights){
        int[] dp = new int[n];
        Arrays.fill(dp, 0);
        
        for(int i = 1; i < n; i++){
            int minEnergy = Integer.MAX_VALUE;
            for(int j = i-1; j >= 0 && j >= i-k; j--){
                minEnergy = Math.min(minEnergy, dp[j] + Math.abs(heights[i]-heights[j]));
            }
            dp[i] = minEnergy;
        }

        return dp[n-1];

    }
    
    public static void main(String[] args) {
        int n = 5, k = 3;
        int[] heights = {10, 30, 40, 50, 20};

        System.out.println(recursiveFrogJumpK(n-1, k, heights));
        System.out.println(memoizedFrogJumpK(n, k, heights));
        System.out.println(tabulatedFrogJumpK(n, k, heights));
    }

}
