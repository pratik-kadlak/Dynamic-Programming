public class coinChange20 {

    // Recursion TC -> O(2^n) SC -> O(target)
    public static int solveRecursiveCoinChange(int index, int[] coins, int amount){
        if(amount == 0) return 0;
        if(index < 0) return (int) 1e9;
        
        int notPick = solveRecursiveCoinChange(index-1, coins, amount);
        int pick = (int) 1e9;
        if(amount >= coins[index])
            pick = 1 + solveRecursiveCoinChange(index, coins, amount-coins[index]);
        
        return Math.min(pick, notPick);
    }
    
    public static int recursiveCoinChange(int[] coins, int amount){
        int n = coins.length;
        int minCoins = solveRecursiveCoinChange(n-1, coins, amount);
        if(minCoins == (int) 1e9) return -1;
        return minCoins;
    }

    //Memoized Soln: TC -> O(n * amount) SC -> O(n * amount + target) 
    public static int solveMemoizedCoinChange(int index, int[] coins, int amount, int[][] dp){
        if(amount == 0) return dp[index][amount] = 0;
        if(index < 0) return (int) 1e9;
        if(dp[index][amount] != -1) return dp[index][amount];

        int notPick = solveMemoizedCoinChange(index-1, coins, amount, dp);
        int pick = (int) 1e9;
        if(amount >= coins[index])
            pick = 1 + solveMemoizedCoinChange(index, coins, amount-coins[index], dp);

        return dp[index][amount] = Math.min(pick, notPick);
    }

    public static int memoizedCoinChange(int[] coins, int amount){
        int n = coins.length;

        int[][] dp = new int[n][amount+1];
        for(int i = 0; i < n; i++){
            for(int j = 0; j <= amount; j++){
                dp[i][j] = -1;
            }
        }
        
        int minCoins = solveMemoizedCoinChange(n-1, coins, amount, dp);
        if(minCoins == (int) 1e9) return -1;
        return minCoins;
    }

    // Tabulated Soln: TC -> O(n*amount) SC -> O(n*amount)
    public static int tabulatedCoinChange(int[] coins, int amount){
        int n = coins.length;
        int[][] dp = new int[n+1][amount+1];
        for(int i = 1; i <= amount; i++){
            dp[0][i] = (int) 1e9;
        }
        for(int i = 0; i <= n; i++)
            dp[i][0] = 0;

        for(int index = 1; index <= n; index++){
            for(int amt = 1; amt <= amount; amt++){
                int notPick = dp[index-1][amt];
                int pick = (int) 1e9;
                if(amt >= coins[index-1])
                    pick = 1 + dp[index][amt-coins[index-1]];
                
                dp[index][amt] = Math.min(pick, notPick);
            }
        }
        int minCoins = dp[n][amount];
        if(minCoins == (int)1e9) return -1;
        return minCoins;
    }

    // space optimized tabulated soln: TC -> O(n*amount) SC -> O(2*amount)
    public static int spaceOptimizedTabulatedCoinChange(int[] coins, int amount){
        int n = coins.length;
        int[] dp = new int[amount+1];
        for(int i = 1; i <= amount; i++)
            dp[i] = (int) 1e9;
        
        for(int index = 0; index < n; index++){
            int[] temp = new int[amount+1];
            for(int amt = 1; amt <= amount; amt++){
                int notPick = dp[amt];
                int pick = (int) 1e9;
                if(amt >= coins[index])
                    pick = 1 + temp[amt-coins[index]];
                
                temp[amt] = Math.min(pick, notPick);
            }
            dp = temp;
        }
        if(dp[amount] == (int) 1e9) return -1;
        return dp[amount];
    }

    // space optimized tabulated soln: TC -> O(n*amount) SC -> O(amount)
    public static int spaceOptimizedTabulatedCoinChange1(int[] coins, int amount){
        int n = coins.length;
        int[] dp = new int[amount+1];
        for(int i = 1; i <= amount; i++)
            dp[i] = (int) 1e9;
        
        for(int index = 0; index < n; index++){
            for(int amt = 1; amt <= amount; amt++){
                int notPick = dp[amt];
                int pick = (int) 1e9;
                if(amt >= coins[index])
                    pick = 1 + dp[amt-coins[index]];
                
                dp[amt] = Math.min(pick, notPick);
            }
        }
        if(dp[amount] == (int) 1e9) return -1;
        return dp[amount];
    }

    public static void main(String[] args) {
        int amount = 11;
        int[] coins = {1, 2, 5};

        System.out.println(recursiveCoinChange(coins, amount));
        System.out.println(memoizedCoinChange(coins, amount));
        System.out.println(tabulatedCoinChange(coins, amount));
        System.out.println(spaceOptimizedTabulatedCoinChange(coins, amount));
        System.out.println(spaceOptimizedTabulatedCoinChange1(coins, amount));

    }
}
