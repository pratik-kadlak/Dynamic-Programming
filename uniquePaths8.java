public class uniquePaths8 {

    // recursive soln (TC->O(2^m.n), SC->O(m+n))
    public static int solveRecursiveUniquePaths(int i, int j){
        if(i == 0 || j == 0) return 1;
        return solveRecursiveUniquePaths(i-1, j) + solveRecursiveUniquePaths(i, j-1);
    }

    public static int recursiveUniquePaths(int m, int n){
        return solveRecursiveUniquePaths(m-1, n-1);
    }

    // memeoized soln (TC->O(m.n), SC->O(m+n)+O(m.n))
    public static int solveMemoizedUniqePaths(int i, int j, int[][] dp){
        if(i == 0 || j == 0) return 1;
        if(dp[i][j] != -1) return dp[i][j];
        return dp[i][j] = solveMemoizedUniqePaths(i-1, j, dp) + solveMemoizedUniqePaths(i, j-1, dp);
    }

    public static int memoizedUniquePaths(int m, int n){
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++)
                dp[i][j] = -1;
        }
        solveMemoizedUniqePaths(m-1, n-1, dp);
        return dp[m-1][n-1];
    }

    // tabulation soln (TC->O(m.n), SC->O(m.n))
    public static int tabulatedUniquePaths(int m, int n){
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 || j == 0) dp[i][j] = 1;
                else dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    // space optimized tabulation (TC->O(m.n), SC->O(n))
    public static int spaceOptimizedTabulatedUniquePaths(int m, int n){
        int[] dp = new int[n];
        for(int i = 0; i < n; i++) dp[i] = 1;

        for(int i = 1; i < m; i++){
            int[] temp = new int[n];
            temp[0] = 1;
            for(int j = 1; j < n; j++){
                temp[j] = temp[j-1] + dp[j];
            }    
            dp = temp;
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        int m = 3, n = 7;
        System.out.println(recursiveUniquePaths(m, n));
        System.out.println(memoizedUniquePaths(m, n));
        System.out.println(tabulatedUniquePaths(m, n));
        System.out.println(spaceOptimizedTabulatedUniquePaths(m, n));
    }
}