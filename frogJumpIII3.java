public class frogJumpIII3 {

    // recursive solution (TC-> O(2^n), SC-> O(n))
    public static int recursiveFrogJump(int index, int[] heights){
        if(index == 0) return 0;
        int twoStep = Integer.MAX_VALUE;
        int oneStep = recursiveFrogJump(index-1, heights) + Math.abs(heights[index] - heights[index-1]);
        if(index > 1) 
            twoStep = recursiveFrogJump(index-2, heights) + Math.abs(heights[index] - heights[index-2]);
        return Math.min(oneStep, twoStep);  
    }


    // memoized solution (TC-> O(n), SC-> O(n) + O(n))
    public static int solveMemoized(int index, int[] heights, int[] dp){
        if(index == 0) return 0;
        if(dp[index] != -1) return dp[index];

        int twoStep = Integer.MAX_VALUE;
        int oneStep = solveMemoized(index-1, heights, dp) + Math.abs(heights[index] - heights[index-1]);
        if(index > 1)
            twoStep = solveMemoized(index-2, heights, dp) + Math.abs(heights[index] - heights[index-2]);

        return dp[index] = Math.min(oneStep, twoStep);
    }

    public static int memoizedFrogJump(int n, int[] heights){
        int[] dp = new int[n];
        for(int i = 0; i < n; i++)
            dp[i] = -1;
        solveMemoized(n-1, heights, dp);
        return dp[n-1];
    }

    
    // tabulation with recursion solution (TC-> O(n), SC-> O(n))
    public static void recursiveTabSolve(int index, int[] height, int[] energy){
        if(index >= height.length) return;
        int prevSecond = Math.abs(height[index] - height[index-2]);
        int prevFirst = Math.abs(height[index] - height[index-1]);
        energy[index] = Math.min(prevSecond+energy[index-2], prevFirst+energy[index-1]);
        recursiveTabSolve(index+1, height, energy);
    }

    public static int recursiveTabulatedFrogJump(int n, int[] height){
        int[] energy = new int[n];
        energy[0] = 0;
        energy[1] = Math.abs(height[1] - height[0]);
        recursiveTabSolve(2, height, energy);
        return energy[n-1];
    }


    // tabulation without recursion solution (TC-> O(n), SC-> O(n))
    public static int tabulatedFrogJump(int n, int[] height){
        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = Math.abs(height[0] - height[1]);

        for(int i = 2; i < n; i++){
            int twoStep = Math.abs(height[i] - height[i-2]);
            int oneStep = Math.abs(height[i] - height[i-1]);

            dp[i] = Math.min(twoStep+dp[i-2], oneStep+dp[i-1]);
        }

        return dp[n-1];
    }


    // space optimized tabulation solution (TC-> O(n), SC-> O(1))
    public static int spaceOptimizedFrogJump(int n, int[] height){
        int prev2 = 0;
        int prev1 = Math.abs(height[1] - height[0]);
        int currEnergy = -1;

        for(int i = 2; i < n; i++){
            int twoStep = Math.abs(height[i] - height[i-2]);
            int oneStep = Math.abs(height[i] - height[i-1]);

            currEnergy = Math.min(prev2+twoStep, prev1+oneStep);
            prev2 = prev1;
            prev1 = currEnergy;
        }
        return prev1;
    }

    public static void main(String[] args) {
        int[] height = {7, 4, 4, 2, 6, 6, 3, 4};
        int n = 8;
        System.out.println(recursiveFrogJump(n-1, height));
        System.out.println(memoizedFrogJump(n, height));
        System.out.println(recursiveTabulatedFrogJump(n, height));
        System.out.println(tabulatedFrogJump(n, height));
        System.out.println(spaceOptimizedFrogJump(n, height));
    }
    
}
