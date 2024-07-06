public class minimumPathSum10 {

    // recursive soln (TC->O(2^m.n), SC->O(m+n))
    public static int solveRecursiveMinPathSum(int i, int j, int[][] grid){
        if(i == 0 && j == 0){
            return grid[0][0];
        }
        if(i < 0 || j < 0) return Integer.MAX_VALUE;
        return grid[i][j] + Math.min(solveRecursiveMinPathSum(i-1, j, grid), 
                                     solveRecursiveMinPathSum(i, j-1, grid));
    }

    public static int recursiveMinPathSum(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;

        return solveRecursiveMinPathSum(m-1, n-1, grid);
    }

    // memeoized soln (TC->O(m.n), SC->O(m+n)+O(m.n))
    public static int solveMemoizedMinPathSum(int i, int j, int[][] dp, int[][] grid){
        if(i == 0 && j == 0) return grid[0][0];
        if(i < 0 || j < 0) return Integer.MAX_VALUE;
        if(dp[i][j] != -1) return dp[i][j];
        
        return dp[i][j] = grid[i][j] + Math.min(solveMemoizedMinPathSum(i-1, j, dp, grid),
                                                solveMemoizedMinPathSum(i, j-1, dp, grid));
    }

    public static int memoizedMinPathSum(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++)
                dp[i][j] = -1;
        }

        return solveMemoizedMinPathSum(m-1, n-1, dp, grid);
    }

    // tabulation soln (TC->O(m.n), SC->O(m.n))
    public static int tabulatedMinPathSum(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0) dp[i][j] = grid[i][j];
                else {
                    int left = Integer.MAX_VALUE, up = Integer.MAX_VALUE;
                    if(i > 0) up = dp[i-1][j];
                    if(j > 0) left = dp[i][j-1];
                    dp[i][j] = grid[i][j] + Math.min(up, left);
                }
            }
        }
        return dp[m-1][n-1];
    }

    // space optimized tabulation (TC->O(m.n), SC->O(n))
    public static int spaceOptimizedMinPathSum(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;

        int[] dp = new int[n];

        for(int i = 0; i < m; i++){
            int[] temp = new int[n];
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0) temp[j] = grid[i][j];
                else{
                    int left = Integer.MAX_VALUE, up = Integer.MAX_VALUE;
                    if(i > 0) up = dp[j];
                    if(j > 0) left = temp[j-1];
                    temp[j] = grid[i][j] +  Math.min(up, left);
                }
            }
            dp = temp;
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};

        System.out.println(recursiveMinPathSum(grid));
        System.out.println(memoizedMinPathSum(grid));
        System.out.println(tabulatedMinPathSum(grid));
        System.out.println(spaceOptimizedMinPathSum(grid));
    }
}
