public class uniquePathsII9 {

    // recursive soln (TC->O(2^m.n), SC->O(m+n))
    public static int solveRecursiveUniquePathsII(int i, int j, int[][] mat){
        if(i < 0 || j< 0 || mat[i][j] == -1) return 0;
        if(i == 0 && j == 0) return 1;
        return solveRecursiveUniquePathsII(i-1, j, mat) + solveRecursiveUniquePathsII(i, j-1, mat);
    }

    public static int recursiveUniquePathsII(int m, int n, int[][] mat){
        return solveRecursiveUniquePathsII(m-1, n-1, mat);
    }

    // memeoized soln (TC->O(m.n), SC->O(m+n)+O(m.n))
    public static int solveMemoizedUniqePathsII(int m, int n, int[][] dp, int[][] mat){
        if(m < 0 || n < 0 || mat[m][n] == -1) return 0;
        if(m == 0 && n == 0) return 1;
        if(dp[m][n] != -1) return dp[m][n];
        return dp[m][n] = solveMemoizedUniqePathsII(m-1, n, dp, mat) + solveMemoizedUniqePathsII(m, n-1, dp, mat);
    }

    public static int memoizedUniquePathsII(int m, int n, int[][] mat){
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++)
                dp[i][j] = -1;
        }
        return solveMemoizedUniqePathsII(m-1, n-1, dp, mat);
    }

    // tabulation soln (TC->O(m.n), SC->O(m.n))
    public static int tabulatedUniquePathsII(int m, int n, int[][] mat){
        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(mat[i][j] == -1) dp[i][j]= 0;
                else if(i == 0 && j == 0) dp[i][j] = 1;
                else {
                    int left = 0, up = 0;
                    if(i > 0) up = dp[i-1][j];
                    if(j > 0) left = dp[i][j-1];
                    
                    dp[i][j] = left + up;
                }
            }
        }
        return dp[m-1][n-1];
    }

    // space optimized tabulation (TC->O(m.n), SC->O(n))
    public static int spaceOptimizedTabulatedUniquePathsII(int m, int n, int[][] mat){
        if(mat[0][0]==-1 || mat[m-1][n-1]==-1) return 0;
        int[] dp = new int[n];

        for(int i = 0; i < n; i++){
            int[] temp = new int[n];
            for(int j = 0; j < n; j++){
                if(mat[i][j] == -1) temp[j] = 0;
                else if(i == 0 && j == 0) temp[j] = 1;
                else{
                    int left = 0, up = 0;
                    if(i > 0) up = dp[j];
                    if(j > 0) left = temp[j-1];
                    temp[j] = up + left; 
                }
            }
            dp = temp;
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        // int[][] mat = {{0,0,0},{0,-1,0},{0,0,0}};
        int[][] mat = {{0,0},{-1,-1},{0,0}};
        System.out.println(recursiveUniquePathsII(3, 2, mat));
        System.out.println(memoizedUniquePathsII(3, 2, mat));
        System.out.println(tabulatedUniquePathsII(3, 2, mat));
        System.out.println(spaceOptimizedTabulatedUniquePathsII(3, 2, mat));
    }
    
}
