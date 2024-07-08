public class cherryPickupII13 {

    // recursive soln (TC->O(9^n), SC->O(n))
    public static int solveRecursiveCherryPickup(int row0, int col0, int row1, int col1, int[][] grid){
        if(col0 < 0 || col0 > grid[0].length-1 || col1 < 0 || col1 > grid[0].length-1) return Integer.MIN_VALUE;
        if(row0 == grid.length-1){
            if(row0==row1 && col0 == col1) return grid[row0][col0];
            return grid[row0][col0] + grid[row1][col1];
        }

        int maxCherries = Integer.MIN_VALUE;
        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                int currCherries = solveRecursiveCherryPickup(row0+1, col0+i, row1+1, col1+j, grid);
                maxCherries = Math.max(maxCherries, currCherries);
            }
        }

        int curr = grid[row0][col0];
        if(col0 != col1) curr += grid[row1][col1];

        return curr + maxCherries;
    }

    public static int recursiveCherryPickup(int[][] grid){
        int n = grid[0].length;
        return solveRecursiveCherryPickup(0, 0, 0, n-1, grid);
    }    
    
    // memoized soln (TC->O(m*n*n)*9), SC->O(m*n*n)+O(n)) where n is the no of rows
    public static int solveMemoizedCherryPickup(int row0, int col0, int row1, int col1, int[][][] dp, int[][] grid){
        if(col0<0 || col0>grid[0].length-1 || col1<0 || col1>grid[0].length-1) return Integer.MIN_VALUE;
        if(row0 == grid.length-1){
            if(col0==col1) return dp[row0][col0][col1] = grid[row0][col0];
            return dp[row0][col0][col1] = grid[row0][col0] + grid[row1][col1];
        }
        if(dp[row0][col0][col1] != -1) return dp[row0][col0][col1];
        
        int maxCherries = Integer.MIN_VALUE;
        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                int currCherries = solveMemoizedCherryPickup(row0+1, col0+i, row1+1, col1+j, dp, grid);
                maxCherries = Math.max(maxCherries, currCherries);
            }
        } 

        int curr = grid[row0][col0];
        if(col0 != col1) curr += grid[row1][col1];

        return dp[row0][col0][col1] = curr + maxCherries;
    }

    public static int memoizedCherryPickup(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m][n][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    dp[i][j][k] = -1;
                }
            }
        }

        return solveMemoizedCherryPickup(0, 0, 0, n-1, dp, grid);
    }

    // tabulation soln  (TC->O(m*n*n)), SC->O(m*n*n))
    public static int tabulatedCherryPickup(int[][] grid){  
        int m = grid.length;
        int n = grid[0].length;

        int[][][] dp = new int[m][n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                dp[0][i][j] = -1 * (int)1e9;
            }
        }

        dp[0][0][n-1] = grid[0][0] + grid[0][n-1];

        for(int row = 1; row < m; row++){
            for(int col0 = 0; col0 < n; col0++){
                for(int col1 = n-1; col1 >= 0; col1--){
                    
                    int maxCherries = -1 * (int)1e9;
                    for(int i = -1; i < 2; i++){
                        for(int j = -1; j < 2; j++){
                            int ncol0 = col0 + i;
                            int ncol1 = col1 + j;
                            if(ncol0>=0 && ncol0<n && ncol1>=0 && ncol1<n){
                                int currCherries = dp[row-1][ncol0][ncol1];
                                if(col0 == col1) currCherries += grid[row][col0];
                                else currCherries += grid[row][col0] + grid[row][col1];
                                maxCherries = Math.max(maxCherries, currCherries);
                            }
                        }
                    }
                    dp[row][col0][col1] = maxCherries;
                }
            }
        }

        int maxCherries = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                maxCherries = Math.max(maxCherries, dp[m-1][i][j]);
            }
        }
        return maxCherries;
    }

    // space optimized soln (TC->O(m*n*n)), SC->O(n*n))
    public static int spaceOptimizedTabulatedCherryPickup(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++)
                dp[i][j] = -1 * (int)1e9;
        }
        dp[0][n-1] = grid[0][0] + grid[0][n-1];

        for(int row = 1; row < m; row++){
            int[][] temp = new int[n][n];
            for(int col0 = 0; col0 < n; col0++){
                for(int col1 = n-1; col1 >= 0; col1--){
                    int maxCherries = -1 * (int) 1e9;
                    
                    for(int i = -1; i < 2; i++){
                        for(int j = -1; j < 2; j++){
                            int ncol0 = col0 + i;
                            int ncol1 = col1 + j;

                            if(ncol0>=0 && ncol0<n && ncol1>=0 && ncol1<n){
                                int currCherries = dp[ncol0][ncol1];
                                if(col0 == col1) currCherries += grid[row][col0];
                                else currCherries += grid[row][col0] + grid[row][col1];
                                maxCherries = Math.max(maxCherries, currCherries);
                            }
                        }
                    }
                    temp[col0][col1] = maxCherries;
                }
            }
            dp = temp;
        }

        int maxCherries = -1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                maxCherries = Math.max(maxCherries, dp[i][j]);
            }
        }

        return maxCherries;
    }

    public static void main(String[] args) {
        // int[][] grid = {{3,1,1},{2,5,1},{1,5,5},{2,1,1}};
        int[][] grid = {{9,3,1,2},{9,9,2,2},{8,4,8,3},{9,2,7,7}};

        System.out.println(recursiveCherryPickup(grid));
        System.out.println(memoizedCherryPickup(grid));
        System.out.println(tabulatedCherryPickup(grid));
        System.out.println(spaceOptimizedTabulatedCherryPickup(grid));
    }
}
