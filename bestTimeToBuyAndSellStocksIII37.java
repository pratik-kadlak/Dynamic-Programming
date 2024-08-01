public class bestTimeToBuyAndSellStocksIII37 {
    
    // Recursive Soln TC -> O(2^n), SC -> O(n)
    public static int solveRecursiveMaxProfit(int index, int[] prices, int canBuy, int numTransactions){
        if(index == prices.length) return 0;

        if(canBuy == 1){
            if(numTransactions > 0){
                int buy = -prices[index] + solveRecursiveMaxProfit(index+1, prices, 0, numTransactions); 
                int notBuy =  0 + solveRecursiveMaxProfit(index+1, prices, 1, numTransactions);
                return Math.max(buy, notBuy);
            } else {
                return 0;
            }           
        } else {
            if(numTransactions > 0){
                int sell = prices[index] + solveRecursiveMaxProfit(index+1, prices, 1, numTransactions-1);
                int notSell = 0 + solveRecursiveMaxProfit(index+1, prices, 0, numTransactions);
                return  Math.max(sell, notSell);
            } else {
                return 0;
            }
            
        }
    }

    public static int recursiveMaxProfit(int[] prices){
        return solveRecursiveMaxProfit(0, prices, 1, 2);
    }
    
    // Memoized Soln TC -> O(n), SC -> O(n + n)
    public static int solveMemoizedMaxProfit(int index, int[] prices, int canBuy, int numTransactions, int[][][] dp){
        if(index == prices.length) return 0;
        if(dp[index][canBuy][numTransactions] != -1) return dp[index][canBuy][numTransactions];

        if(canBuy == 1){
            if(numTransactions > 0){
                int buy = -prices[index] + solveMemoizedMaxProfit(index+1, prices, 0, numTransactions, dp); 
                int notBuy =  0 + solveMemoizedMaxProfit(index+1, prices, 1, numTransactions, dp);
                return dp[index][canBuy][numTransactions] = Math.max(buy, notBuy);
            } else {
                return dp[index][canBuy][numTransactions] = 0;
            }           
        } else {
            if(numTransactions > 0){
                int sell = prices[index] + solveMemoizedMaxProfit(index+1, prices, 1, numTransactions-1, dp);
                int notSell = 0 + solveMemoizedMaxProfit(index+1, prices, 0, numTransactions, dp);
                return dp[index][canBuy][numTransactions] =  Math.max(sell, notSell);
            } else {
                return dp[index][canBuy][numTransactions] = 0;
            }
        }
    }

    public static int memoizedMaxProfit(int[] prices){
        int[][][] dp = new int[prices.length][2][3];
        for(int i = 0; i < prices.length; i++)
            for(int j = 0; j < 2; j++)
                for(int k = 0; k < 3; k++)
                    dp[i][j][k] = -1;

        return solveMemoizedMaxProfit(0, prices, 1, 2, dp);
    }

    // Tabulated Soln TC -> O(n), SC -> O(n)
    public static int tabulatedMaxProfit(int[] prices){
        int n = prices.length;
        int[][][] dp = new int[n+1][2][3];
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 3; j++){
                dp[n][i][j] = 0;
            }
        }

        for(int index = n-1; index >= 0; index--){
            for(int canBuy = 0; canBuy <= 1; canBuy++){
                for(int numTransactions = 0; numTransactions <= 2; numTransactions++){
                    if(canBuy == 1){
                        if(numTransactions < 2){
                            int buy = -prices[index] + dp[index+1][0][numTransactions];
                            int notBuy =  0 + dp[index+1][1][numTransactions];
                            dp[index][canBuy][numTransactions] = Math.max(buy, notBuy);
                        } else {
                            dp[index][canBuy][numTransactions] = 0;
                        }           
                    } else {
                        if(numTransactions < 2){
                            int sell = prices[index] + dp[index+1][1][numTransactions+1]; 
                            int notSell = 0 + dp[index+1][0][numTransactions]; 
                            dp[index][canBuy][numTransactions] =  Math.max(sell, notSell);
                        } else {
                            dp[index][canBuy][numTransactions] = 0;
                        }
                    }
                }
            }
        }
        return dp[0][1][0];
    }

    // Space Optimized Soln TC -> O(n), SC -> O(1)
    public static int spaceOptimizedTabulatedMaxProfit(int[] prices){
        int n = prices.length;
        int[][] dp = new int[2][3];
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 3; j++){
                dp[i][j] = 0;
            }
        }

        for(int index = n-1; index >= 0; index--){
            int[][] temp = new int[2][3];
            for(int canBuy = 0; canBuy <= 1; canBuy++){
                for(int numTransactions = 0; numTransactions <= 2; numTransactions++){
                    if(canBuy == 1){
                        if(numTransactions < 2){
                            int buy = -prices[index] + dp[0][numTransactions];
                            int notBuy =  0 + dp[1][numTransactions];
                            temp[canBuy][numTransactions] = Math.max(buy, notBuy);
                        } else {
                            temp[canBuy][numTransactions] = 0;
                        }           
                    } else {
                        if(numTransactions < 2){
                        int sell = prices[index] + dp[1][numTransactions+1]; 
                            int notSell = 0 + dp[0][numTransactions]; 
                            temp[canBuy][numTransactions] =  Math.max(sell, notSell);
                        } else {
                            temp[canBuy][numTransactions] = 0;
                        }
                    }
                }
            }
            dp = temp;
        }
        return dp[1][0];
    }

    public static void main(String[] args) {
        int[] prices = {3,3,5,0,0,3,1,4};

        System.out.println(recursiveMaxProfit(prices));
        System.out.println(memoizedMaxProfit(prices));
        System.out.println(tabulatedMaxProfit(prices));
        System.out.println(spaceOptimizedTabulatedMaxProfit(prices));

    }


}
