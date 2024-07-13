public class rodCutting24 {

    // recursive: TC -> O(2^n), SC -> O(n)
    public static int recursiveRodCutting(int n, int[] price){
        if(n == 0) return 0;
        int pick = 0;
        for(int i = n; i > 0; i--){
            pick = Math.max(pick, price[i-1] + recursiveRodCutting(n-i, price));
        }
        return pick;
    }

    // memoized: TC -> O(n^2), SC -> O(n + n)
    public static int solveMemoizedRodCutting(int n, int[] price, int[] dp){
        if(n == 0) return dp[n] = 0;
        if(dp[n] != -1) return dp[n];
        int pick = 0;
        for(int i = n; i > 0; i--){
            pick = Math.max(pick, price[i-1] + solveMemoizedRodCutting(n-i, price, dp));
        }

        return dp[n] = pick;
    }

    public static int memoizedRodCutting(int n, int[] price){
        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i++)
            dp[i] = -1;
        
        return solveMemoizedRodCutting(n, price, dp);
    }

    // tabulated: TC -> O(n^2), SC -> O(n)
    public static int tabulatedRodCutting(int n, int[] price){
        int[] dp = new int[n+1];
        dp[0] = 0;
    
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= i; j++){
                dp[i] = Math.max(dp[i], price[j-1] + dp[i-j]);
            }
        }
        return dp[n];
    }

    
    public static void main(String[] args) {
        int n = 8;
        int price[] = {1, 5, 8, 9, 10, 17, 17, 20};

        System.out.println(recursiveRodCutting(n, price));
        System.out.println(memoizedRodCutting(n, price));
        System.out.println(tabulatedRodCutting(n, price));
    }

}
