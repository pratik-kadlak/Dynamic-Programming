public class partitionArrayForMaximumSum54 {

    // Recursive Soln: TC -> O(k^n), SC -> O(n)
    public static int solveRecursiveMaxParitionSum(int index, int[] arr, int k){
        if(index >= arr.length) return 0;

        int mxCost = -1;
        int currMax = -1;
        for(int i = index; i < arr.length && i < index+k; i++){
            currMax = Math.max(currMax, arr[i]);
            mxCost = Math.max(mxCost, (currMax*(i-index+1)) + solveRecursiveMaxParitionSum(i+1, arr, k));
        }

        return mxCost;
    }

    public static int recursiveMaxParitionSum(int[] arr, int k){
        return solveRecursiveMaxParitionSum(0, arr, k);
    }

    // Memoized Soln: TC -> O(n*k), SC -> O(n*k + n)
    public static int solveMemoizedMaxPartitionSum(int index, int[] arr, int k, int[] dp){
        if(index >= arr.length) return 0;
        if(dp[index] != -1) return dp[index];

        int mxCost = -1;
        int currMax = -1;

        for(int i = index; i < arr.length && i < index+k; i++){
            currMax = Math.max(currMax, arr[i]);
            mxCost = Math.max(mxCost, (currMax * (i-index+1)) + solveMemoizedMaxPartitionSum(i+1, arr, k, dp));
        }

        return mxCost;
    }

    public static int memoizedMaxPartitionSum(int[] arr, int k){
        int[] dp = new int[arr.length];
        for(int i = 0; i < dp.length; i++)
            dp[i] = -1;

        return solveMemoizedMaxPartitionSum(0, arr, k, dp);
    }

    // Tabulated Soln: TC -> O(n*k), SC -> O(n)
    public static int tabulatedMaxPartitionSum(int[] arr, int k){
        int n = arr.length;
        int[] dp = new int[n+1];


        for(int index = n-1; index >= 0; index--){
            dp[index] = -1;
            int currMax = -1;
            for(int i = index; i < n && i < index+k; i++){
                currMax = Math.max(currMax, arr[i]);
                dp[index] = Math.max(dp[index], (currMax * (i-index+1)) + dp[i+1]);
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        // int k = 3;
        // int[] arr = {1,15,7,9,2,5,10};

        int k = 4;
        int[] arr = {1,4,1,5,7,3,6,1,9,9,3};

        System.out.println(recursiveMaxParitionSum(arr, k));
        System.out.println(memoizedMaxPartitionSum(arr, k));
        System.out.println(tabulatedMaxPartitionSum(arr, k));
    }

}
