import java.util.*;

public class minCostToCutStick49 {

    // Recursive Soln: TC -> O(2^c), SC -> O(c)
    public static int solveRecursiveMinCost(int i, int j, int start, int end, int[] cuts){
        if(i == j) return 0;

        int mc = (int) 1e9;
        for(int k = i; k < j; k++){
            mc = Math.min(mc, end-start + solveRecursiveMinCost(i, k, start, cuts[k], cuts) 
                                   + solveRecursiveMinCost(k+1, j, cuts[k], end, cuts));
        }

        return mc;
    }

    public static int recursiveMinCost(int n, int[] cuts){
        Arrays.sort(cuts);
        return solveRecursiveMinCost(0, cuts.length, 0 , n, cuts);
    }

    // Memoized Soln: TC -> O(c^3), SC -> O(c*c + n) c -> length of cuts array
    public static int solveMemoizedMinCost(int i, int j, int start, int end, int[] cuts, int[][] dp){
        if(i == j) return dp[i][j] = 0;
        if(dp[i][j] != -1) return dp[i][j];

        int mc = (int) 1e9;
        for(int k = i; k < j; k++){
            mc = Math.min(mc, end-start + solveMemoizedMinCost(i, k, start, cuts[k], cuts, dp)
                                        + solveMemoizedMinCost(k+1, j, cuts[k], end, cuts, dp));
        }

        return dp[i][j] = mc;
    }
   
    public static int memoizedMinCost(int n, int[] cuts){
        Arrays.sort(cuts);
        int[][] dp = new int[cuts.length+1][cuts.length+1];

        for(int i = 0; i <= cuts.length; i++){
            for(int j = 0; j <= cuts.length; j++)
                dp[i][j] = -1;
        }

        return solveMemoizedMinCost(0, cuts.length, 0, n, cuts, dp);
    }

    // Striver Solution

    // Recursive Soln: TC -> O(2^c), SC -> O(c)
    public static int solveRecursiveMinCost1(int i, int j, int[] cuts){
        if(i > j) return 0;
        int minCost = (int) 1e9;
        for(int k = i; k <= j; k++){
            minCost = Math.min(minCost, cuts[j+1]-cuts[i-1] + solveRecursiveMinCost1(i, k-1, cuts)
                                                            + solveRecursiveMinCost1(k+1, j, cuts));
        }
        return minCost;
    }
    
    public static int recursiveMinCost1(int n, int[] cuts){
        Arrays.sort(cuts);
        int[] extendexCuts = new int[cuts.length+2];
        extendexCuts[0] = 0;
        for(int i = 0; i < cuts.length; i++){
            extendexCuts[i+1] = cuts[i];
        }
        extendexCuts[cuts.length+1] = n;
        return solveRecursiveMinCost1(1, cuts.length, extendexCuts);
    }

   // Memoized Soln: TC -> O(c^3), SC -> O(c*c) c -> length of cuts array
    public static int solveMemoizedMinCost(int i, int j, int[] cuts, int[][] dp){
        if(i > j) return 0;
        if(dp[i][j] != -1) return dp[i][j];

        int minCost = (int) 1e9;
        for(int k = i; k <= j; k++){
            minCost = Math.min(minCost, cuts[j+1] - cuts[i-1] + solveMemoizedMinCost(i, k-1, cuts, dp)
                                                              + solveMemoizedMinCost(k+1, j, cuts, dp));
        }

        return dp[i][j] = minCost;
    }
    
    public static int memoizedMinCost1(int n, int[] cuts){
        Arrays.sort(cuts);
        int[] extendedCuts = new int[cuts.length+2];
        extendedCuts[0] = 0;
        for(int i = 0; i < cuts.length; i++)
            extendedCuts[i+1] = cuts[i];
        extendedCuts[cuts.length+1] = n;

        int[][] dp = new int[cuts.length+1][cuts.length+1];
        for(int i = 0; i <= cuts.length; i++){
            for(int j = 0; j <= cuts.length; j++)
                dp[i][j] = -1;
        }

        return solveMemoizedMinCost(1, cuts.length, extendedCuts, dp);
    }


    // Tabulated Soln: TC -> O(c^3), SC -> O(c*c) c -> length of cuts array
    public static int tabulatedMinCost(int n, int[] cuts){
        int c = cuts.length;
        int[] extendedCuts = new int[c+2];
        extendedCuts[0] = 0;
        for(int i = 0; i < cuts.length; i++)
            extendedCuts[i+1] = cuts[i];
        extendedCuts[c+1] = n;

        int[][] dp = new int[c+2][c+2];

        for(int i = c; i >= 1; i--){
            for(int j = 1; j <= c; j++){
                if(i > j) continue;
                dp[i][j] = (int) 1e9;
                
                for(int k = i; k <= j; k++){
                    dp[i][j] = Math.min(dp[i][j], extendedCuts[j+1]-extendedCuts[i-1]+dp[i][k-1]+dp[k+1][j]);
                }
            }
        }
        return dp[1][c];
    }
    

     public static void main(String[] args) {
        int n = 7;
        int[] cuts = {1, 3, 4, 5};

        // int n = 30;
        // int[] cuts = {13,25,16,20,26,5,27,8,23,14,6,15,21,24,29,1,19,9,3};

        System.out.println(recursiveMinCost(n, cuts));
        System.out.println(memoizedMinCost(n, cuts));

        System.out.println(recursiveMinCost1(n, cuts));
        System.out.println(memoizedMinCost1(n, cuts));
        System.out.println(tabulatedMinCost(n, cuts));

    }
    
}
