public class unboundedKnapsack23 {

    // tabulated soln: TC -> O(N*W) SC -> O(N*W)
    static int knapSack(int N, int W, int val[], int weights[]) {
        // code here
        int[][] dp = new int[N+1][W+1];
        
        for(int index = 1; index <= N; index++){
            for(int wt = 1; wt <= W; wt++){
                int notPick = dp[index-1][wt];
                int pick = 0;
                if(wt >= weights[index-1])
                    pick = val[index-1] + dp[index][wt - weights[index-1]];
                    
                dp[index][wt] = Math.max(pick, notPick);
            }
        }
        return dp[N][W];
    }

    public static void main(String[] args) {
        int N = 4;
        int W = 8;
        int[] val = {6, 1, 7, 7};
        int[] weights = {1, 3, 4, 5};

        System.out.println(knapSack(N, W, val, weights));
    }
}
