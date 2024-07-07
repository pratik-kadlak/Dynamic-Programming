public class minFallingPathSum12 {

    // recursive soln (TC->O(2^(n^2)), SC->O(n))
    public static int solveRecursiveMinFallingPathSum(int index, int level, int[][] matrix){
        if(level == 0) return matrix[0][index];

        int left = Integer.MAX_VALUE;
        int up = matrix[level-1][index];
        int right = Integer.MAX_VALUE;

        if(index > 0) left = solveRecursiveMinFallingPathSum(index-1, level-1, matrix);
        if(index < matrix[0].length-1) right = solveRecursiveMinFallingPathSum(index+1, level-1, matrix);

        return matrix[level][index] +  Math.min(left, Math.min(up, right));
    }

    public static int recursiveMinFallingPathSum(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;

        int minSum = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            minSum = Math.min(minSum, solveRecursiveMinFallingPathSum(i, m-1, matrix));
        }
        return minSum;
    }

    // memoized soln (TC->O(n^2)), SC->O(n^2)+O(n)) where n is the no of rows
    public static int solveMemoizedMinFallingPathSum(int index, int level, int[][] dp, int[][] matrix){
        if(level == 0) return matrix[level][index];
        if(dp[index][level] != -1) return dp[level][index];

        int left = Integer.MAX_VALUE;
        int up = matrix[level-1][index];
        int right = Integer.MAX_VALUE;

        if(index > 0) left = solveMemoizedMinFallingPathSum(index-1, level-1, dp, matrix);
        if(index < matrix[0].length-1) right = solveMemoizedMinFallingPathSum(index+1, level-1, dp, matrix);

        return dp[level][index] = matrix[level][index] + Math.min(left, Math.min(up, right));

    }

    public static int memlizedMinFallingPathSum(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++)
                dp[i][j] = -1;
        }

        int minSum = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++)
            minSum = Math.min(minSum, solveMemoizedMinFallingPathSum(i, m-1, dp, matrix));
        
        return minSum;
    }

    // tabulation soln  (TC->O(n^2)), SC->O(n^2))
    public static int tabulatedMinFallingPathSum(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];

        for(int i = 0; i < n; i++)
            dp[0][i] = matrix[0][i];

        for(int i = 1; i < m; i++){
            for(int j = 0; j < n; j++){
                int left = Integer.MAX_VALUE;
                int up = dp[i-1][j];
                int right = Integer.MAX_VALUE;
                
                if(j > 0) left = dp[i-1][j-1];
                if(j < n-1) right = dp[i-1][j+1];

                int minTotal = matrix[i][j] + Math.min(left, Math.min(up, right));
                dp[i][j] = minTotal;
            }
        }

        int minTotal = dp[m-1][0];
        for(int i = 1; i < n; i++){
            minTotal = Math.min(minTotal, dp[m-1][i]);
        }
        return minTotal;
    }

    // space optimized soln (TC->O(n^2)), SC->O(n))
    public static int spaceOptimizedTabulatedMinFallingPathSum(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;
        int[] dp = new int[n];

        for(int i = 0; i < n; i++)
            dp[i] = matrix[0][i];

        for(int i = 1; i < m; i++){
            int[] temp = new int[n];
            for(int j = 0; j < n; j++){
                int left = Integer.MAX_VALUE;
                int up = dp[j];
                int right = Integer.MAX_VALUE;
                
                if(j > 0) left = dp[j-1];
                if(j < n-1) right = dp[j+1];
                
                temp[j] = matrix[i][j] + Math.min(left, Math.min(up, right));
            }
            dp = temp;
        }

        int minTotal = dp[0];
        for(int i = 1; i < dp.length; i++)
            minTotal = Math.min(minTotal, dp[i]);

        return minTotal;
    }

    public static void main(String[] args) {
        int[][] matrix = {{2,1,3},{6,5,4},{7,8,9}};

        System.out.println(recursiveMinFallingPathSum(matrix));
        System.out.println(memlizedMinFallingPathSum(matrix));
        System.out.println(tabulatedMinFallingPathSum(matrix));
        System.out.println(spaceOptimizedTabulatedMinFallingPathSum(matrix));
    }
    
}
