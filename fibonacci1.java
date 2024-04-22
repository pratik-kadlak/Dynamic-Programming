public class fibonacci1{

    // recursive solution (TC-> O(2^n), SC-> O(n))
    public static int recursiveFib(int n){
        if(n <= 1) return n;
        return recursiveFib(n-1) + recursiveFib(n-2);
    }

    // memoized solution (TC-> O(n), SC-> O(n) + O(n))
    public static int memoizedFib(int n, int[] dp){
        if(n <= 1) return n;
        if(dp[n] != -1) return dp[n];
        return dp[n] = memoizedFib(n-1, dp) + memoizedFib(n-2, dp);
    }

    // tabulation solution (TC-> O(n), SC-> O(n))
    public static int tabulatedFib(int n){
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        
        for(int i = 2; i <= n; i++)
            dp[i] = dp[i-1] + dp[i-2];

        return dp[n];
    }

    // space optimized tabulation solution (TC-> O(n), SC-> O(1))
    public static int spaceOptimizedFib(int n){
        int prev2 = 0;
        int prev1 = 1;
        int curr = -1;

        for(int i = 2; i <= n; i++){
            curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    public static void main(String[] args) {
        int n = 10;

        System.out.println(recursiveFib(n));

        int[] dp = new int[n+1];
        for(int i = 0; i <= n; i++)
            dp[i] = -1;
        System.out.println(memoizedFib(n, dp));

        System.out.println(tabulatedFib(n));
        System.out.println(spaceOptimizedFib(n));
    }

}