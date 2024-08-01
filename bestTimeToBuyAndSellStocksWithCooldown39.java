public class bestTimeToBuyAndSellStocksWithCooldown39 {

    // Recursive Soln TC -> O(2^n),  SC -> O(n) 
    public static int solveRecursiveMaxProfit(int index, int canBuy, int[] prices){
        if(index >= prices.length) return 0;

        if(canBuy == 1){
            int buy = -prices[index] + solveRecursiveMaxProfit(index+1, 0, prices);
            int notBuy = 0 + solveRecursiveMaxProfit(index+1, 1, prices);
            return Math.max(buy, notBuy);
        } else {
            int sell = prices[index] + solveRecursiveMaxProfit(index+2, 1, prices);
            int notSell = 0 + solveRecursiveMaxProfit(index+1, 0, prices);
            return Math.max(sell, notSell);
        }
    }

    public static int recursiveMaxProfit(int[] prices){
        return solveRecursiveMaxProfit(0, 1, prices);
    }

    // Memoized Soln  TC -> O(n), SC -> O(2n + n)
    public static int solveMemoizedMaxProfit(int index, int canBuy, int[] prices, int[][] dp){
        if(index >= prices.length) return 0;
        if(dp[index][canBuy] != -1) return dp[index][canBuy];

        if(canBuy == 1){
            int buy = -prices[index] + solveMemoizedMaxProfit(index+1, 0, prices, dp);
            int notBuy = 0 + solveMemoizedMaxProfit(index+1, 1, prices, dp);
            return dp[index][canBuy] = Math.max(buy, notBuy);
        } else {
            int sell = prices[index] + solveMemoizedMaxProfit(index+2, 1, prices, dp);
            int notSell = 0 + solveMemoizedMaxProfit(index+1, 0, prices, dp);
            return dp[index][canBuy] = Math.max(sell, notSell);
        }
    }

    public static int memoizedMaxProfit(int[] prices){
        int n = prices.length;
        int[][] dp = new int[n][2];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 2; j++){
                dp[i][j] = -1;
            }
        }
        return solveMemoizedMaxProfit(0, 1, prices, dp);
    }

    // Tabulated Soln: TC -> O(n), SC -> O(n)
    public static int tabulatedMaxProfit(int[] prices){
        int n = prices.length;
        int[][] dp = new int[prices.length+2][2];

        for(int i = n; i < n+2; i++)
            for(int j = 0; j < 2; j++)
                dp[i][j] = 0;

        for(int index = n-1; index >= 0; index--){
            for(int canBuy = 0; canBuy < 2; canBuy++){
                if(canBuy == 1){
                    int buy = -prices[index] + dp[index+1][0];
                    int notBuy = 0 + dp[index+1][1];
                    dp[index][canBuy] = Math.max(buy, notBuy);
                } else {
                    int sell = prices[index] + dp[index+2][1];
                    int notSell = 0 + dp[index+1][0];
                    dp[index][canBuy] = Math.max(sell, notSell);
                }
            }
        }
        return dp[0][1];
    }

    
    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2};

        System.out.println(recursiveMaxProfit(prices));
        System.out.println(memoizedMaxProfit(prices));
        System.out.println(tabulatedMaxProfit(prices));
    }


}
