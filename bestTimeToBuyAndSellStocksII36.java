public class bestTimeToBuyAndSellStocksII36 {

    // My Recursive Soln
    public static int solveMyRecursiveMaxProfit(int i, int j, int[] prices){
        if(j >= prices.length) return 0;
        if(j == prices.length-1){
            return prices[j] - prices[i];
        }

        int sell = (prices[j] - prices[i]) + solveMyRecursiveMaxProfit(j+1, j+1, prices);
        int keep = solveMyRecursiveMaxProfit(i, j+1, prices);

        return Math.max(sell, keep);
    }

    public static int myRecursiveMaxProfit(int[] prices){
        return solveMyRecursiveMaxProfit(0, 0, prices);
    }

    // My Memoized Soln
    public static int solveMyMemoizedMaxProfit(int i, int j, int[] prices, int[][] dp){
        if(j >= prices.length) return 0;
        if(j == prices.length-1){
            return dp[i][j] = prices[j] - prices[i];
        }
        if(dp[i][j] != -1) return dp[i][j];

        int sell = (prices[j] - prices[i]) + solveMyMemoizedMaxProfit(j+1, j+1, prices, dp);
        int keep = solveMyMemoizedMaxProfit(i, j+1, prices, dp);

        return dp[i][j] = Math.max(sell, keep);
    }

    public static int myMemoizedMaxProfit(int[] prices){
        int m = prices.length;
        int n = prices.length;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++)
                dp[i][j] = -1;
        }

        return solveMyMemoizedMaxProfit(0, 0, prices, dp);
    }

    // Striver's Soln

    // TC -> O(2^n), SC -> O(1)
    public static int solveRecursiveProfit(int index, boolean canBuy, int[] prices){
        if(index == prices.length) return 0;

        if(canBuy){
            int buy = -prices[index] + solveRecursiveProfit(index+1, false, prices);
            int notBuy = 0 + solveRecursiveProfit(index+1, true, prices);
            return Math.max(buy, notBuy);
        } else {
            int sell = prices[index] + solveRecursiveProfit(index+1, true, prices);
            int notSell = 0 + solveRecursiveProfit(index+1, false, prices);
            return Math.max(sell, notSell);
        }
    }

    public static int recursiveMaxProfit(int[] prices){
        return solveRecursiveProfit(0, true, prices);
    }

    // TC -> O(n*2), SC -> O(n*2) + O(n)
    public static int solveMemoizedMaxProfit(int index, int canBuy, int[] prices, int[][] dp){
        if(index == prices.length) return 0;
        if(dp[index][canBuy] != -1) return dp[index][canBuy];

        if(canBuy == 1){
            int buy = -prices[index] + solveMemoizedMaxProfit(index, 0, prices, dp);
            int notBuy = 0 + solveMemoizedMaxProfit(index+1, 1, prices, dp);
            return dp[index][canBuy] = Math.max(buy, notBuy);
        } else {
            int sell = prices[index] + solveMemoizedMaxProfit(index+1, 1, prices, dp);
            int notSell = 0 + solveMemoizedMaxProfit(index+1, 0, prices, dp);
            return dp[index][canBuy] = Math.max(sell, notSell);
        }
    }

    public static int memoizedMaxProfit(int[] prices){
        int[][] dp = new int[prices.length][2];
        for(int i = 0; i < prices.length; i++){
            for(int j = 0; j < 2; j++)
                dp[i][j] = -1;
        }

        return solveMemoizedMaxProfit(0, 1, prices, dp);
    }

    // TC -> O(n*2), SC -> O(n*2)
    public static int tabulatedMaxProfit(int[] prices){
        int[][] dp = new int[prices.length+1][2];
        dp[prices.length][0] = 0;
        dp[prices.length][1] = 0;

        for(int index = prices.length-1; index >= 0; index--){
            for(int canBuy = 0; canBuy < 2; canBuy++){
                if(canBuy == 1){
                    int buy = -prices[index] + dp[index+1][0];
                    int notBuy = 0 + dp[index+1][1];
                    dp[index][canBuy] = Math.max(buy, notBuy);
                } else {
                    int sell = prices[index] + dp[index+1][1];
                    int notSell = 0 + dp[index+1][0];
                    dp[index][canBuy] = Math.max(sell, notSell);
                }
            }
        }

        return dp[0][1];
    }

    // TC -> O(n*2), SC -> O(1)
    public static int spaceOptimizedTabulatedMaxProfit(int[] prices){
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = 0;

        for(int index = prices.length-1; index >= 0; index--){
            for(int canBuy = 0; canBuy < 2; canBuy++){
                if(canBuy == 1){
                    int buy = -prices[index] + dp[0];
                    int notBuy = 0 + dp[1];
                    dp[canBuy] = Math.max(buy, notBuy);
                } else {
                    int sell = prices[index] + dp[1];
                    int notSell = 0 + dp[0];
                    dp[canBuy] = Math.max(sell, notSell);
                }
            }
        }

        return dp[1];
    }



    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};

        System.out.println(myRecursiveMaxProfit(prices));
        System.out.println(myMemoizedMaxProfit(prices));

        System.out.println(recursiveMaxProfit(prices));
        System.out.println(memoizedMaxProfit(prices));
        System.out.println(tabulatedMaxProfit(prices));
        System.out.println(spaceOptimizedTabulatedMaxProfit(prices));
    }

}
