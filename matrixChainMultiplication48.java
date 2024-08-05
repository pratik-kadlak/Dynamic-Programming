public class matrixChainMultiplication48 {

    // Recursive Soln: TC -> O(2^n), SC -> O(n)
    public static int solveRecursiveMatrixMultiplication(int i, int j, int[] arr){
        if(i == j) return 0;

        int mcm = (int) 1e9;
        for(int k = i; k <= j-1; k++){
            mcm = Math.min(mcm, arr[i-1] * arr[k] * arr[j] + solveRecursiveMatrixMultiplication(i, k, arr) + 
                                                             solveRecursiveMatrixMultiplication(k+1, j, arr));
        }

        return mcm;
    }

    public static int recursiveMatrixMultiplication(int[] arr){
        return solveRecursiveMatrixMultiplication(1, arr.length-1, arr);
    }

    // Memoized Soln: TC -> O(n^3), SC -> O(n^2 + n)
    public static int solveMemoizedMatrixMultiplication(int i, int j, int[] arr, int[][] dp){
        if(i == j) return 0;
        if(dp[i][j] != -1) return dp[i][j];

        int mcm = (int) 1e9;
        for(int k = i; k <= j-1; k++){
            mcm = Math.min(mcm, arr[i-1] * arr[k] * arr[j] + solveMemoizedMatrixMultiplication(i, k, arr, dp)
                                                           + solveMemoizedMatrixMultiplication(k+1, j, arr, dp));
        }

        return dp[i][j] = mcm;
        
    }

    public static int memoizedMatrixMultiplication(int[] arr){
        int n = arr.length;
        int[][] dp = new int[n][n];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                dp[i][j] = -1;
            }
        }
        return solveMemoizedMatrixMultiplication(1, n-1, arr, dp);
    }

    // Tabulated Soln: TC -> O(n^3), SC -> O(n^2)
    public static int tabulatedMatrixMultiplication(int[] arr){
        int n = arr.length;
        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i++)
            dp[i][i] = 0;

        for(int i = n-1; i >= 1; i--){
            for(int j = i+1; j < n; j++){
                dp[i][j] = (int) 1e9;
                for(int k = i; k <= j-1; k++){
                    dp[i][j] = Math.min(dp[i][j], arr[i-1] * arr[k] * arr[j] + dp[i][k]+dp[k+1][j]);
                }
            }
        }

        return dp[1][n-1];
    }

    public static void main(String[] args) {
        int[] arr = {40, 20, 30, 10, 30};
        System.out.println(recursiveMatrixMultiplication(arr));
        System.out.println(memoizedMatrixMultiplication(arr));
        System.out.println(tabulatedMatrixMultiplication(arr));
    }
    
}