public class matrixChainMultiplication49 {

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
        System.out.println(tabulatedMatrixMultiplication(arr));
    }


}
