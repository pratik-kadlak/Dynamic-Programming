public class bestTimeToBuyAndSellStocks38 {

    // Tabulated Soln TC -> O(n*k), SC -> O(n*k)
    public static int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n+1][2][k+1];

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < k; j++)
                dp[n][i][j] = 0;
        }

        for(int index = n-1; index >= 0; index--){
            for(int canBuy = 0; canBuy < 2; canBuy++){
                for(int numTransactions = 0; numTransactions <= k; numTransactions++){
                    if(canBuy == 1){
                        if(numTransactions < k){
                            int buy = -prices[index] + dp[index+1][0][numTransactions];
                            int notBuy = 0 + dp[index+1][1][numTransactions];
                            dp[index][canBuy][numTransactions] = Math.max(buy, notBuy);
                        } else{
                            dp[index][canBuy][numTransactions] = 0;
                        }
                    } else {
                        if(numTransactions < k){
                            int sell = prices[index] + dp[index+1][1][numTransactions+1];
                            int notSell = 0 + dp[index+1][0][numTransactions];
                            dp[index][canBuy][numTransactions] = Math.max(sell, notSell);
                        } else {
                            dp[index][canBuy][numTransactions] = 0;
                        }
                    }
                }
            }
        }

        return dp[0][1][0];
    }

    public static void main(String[] args) {
        int k = 2;
        int[] prices = {3,2,6,5,0,3};

        System.out.println(maxProfit(k, prices));
    }
}
