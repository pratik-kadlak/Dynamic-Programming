public class coinChangeII22 {

    // tabulated soln : TC -> O(n*amount) SC -> O(n*amount)
    public static int change(int amount, int[] coins){
        int n = coins.length;
        int[][] dp = new int[n+1][amount+1];

        for(int i = 1; i <= n; i++)
            dp[i][0] = 1;

        for(int index = 1; index <= n; index++){
            for(int amt = 1; amt <= amount; amt++){
                int notPick = dp[index-1][amt];
                int pick = 0;
                if(amt >= coins[index-1])
                    pick = dp[index][amt - coins[index-1]];
                
                dp[index][amt] = pick + notPick;
            }
        }
        return dp[n][amount];
    }

    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {1, 2, 5};

        System.out.println(change(amount, coins));
    }

}