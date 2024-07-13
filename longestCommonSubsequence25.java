public class longestCommonSubsequence25 {

    // recursive: TC -> O(2^m + 2^n), SC -> O(min(m, n))
    public static int solveRecursiveLongestCommonSubsequence(int i, int j, char[] str1, char[] str2){
        if(i == str1.length || j == str2.length) return 0;

        if(str1[i] == str2[j])
            return 1 + solveRecursiveLongestCommonSubsequence(i+1, j+1, str1, str2);

        return Math.max(
            solveRecursiveLongestCommonSubsequence(i, j+1, str1, str2),
            solveRecursiveLongestCommonSubsequence(i+1, j, str1, str2)    
        );
    }

    public static int recursiveLongestCommonSubsequence(String text1, String text2){
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        return solveRecursiveLongestCommonSubsequence(0, 0, str1, str2);
    }

    // memoized: TC -> O(m * n), SC -> O((m * n) + min(m, n))
    public static int solveMemoizedLongestCommonSubsequence(int i, int j, char[] str1, char[] str2, int[][] dp){
        if(i == str1.length || j == str2.length) return 0;
        if(dp[i][j] != -1) return dp[i][j];

        if(str1[i] == str2[j])
            return dp[i][j] = 1 + solveMemoizedLongestCommonSubsequence(i+1, j+1, str1, str2, dp);

        return dp[i][j] = Math.max(
            solveMemoizedLongestCommonSubsequence(i, j+1, str1, str2, dp),
            solveMemoizedLongestCommonSubsequence(i+1, j, str1, str2, dp)    
        );
    }

    public static int memoizedLongestCommonSubsequence(String text1, String text2){
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        
        int[][] dp = new int[str1.length][str2.length];
        for(int i = 0; i < str1.length; i++){
            for(int j = 0; j < str2.length; j++){
                dp[i][j] = -1;
            }
        }
        
        return solveMemoizedLongestCommonSubsequence(0, 0, str1, str2, dp);
    }

    // tabulation: TC -> O(m * n), SC -> O(m * n)
    public static int tabulatedLongestCommonSubsequence(String text1, String text2){
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        
        int[][] dp = new int[str1.length+1][str2.length+1];
        for(int i = 0; i <= str1.length; i++) dp[i][0] = 0;
        for(int j = 0; j <= str2.length; j++) dp[0][j] = 0;

        for(int i = 1; i <= str1.length; i++){
            for(int j = 1; j <= str2.length; j++){
                if(str1[i-1] == str2[j-1]) 
                    dp[i][j] = 1 + dp[i-1][j-1];
                else 
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        return dp[str1.length][str2.length];
    }

    // space optimization tabulation: TC -> O(m * n), SC -> O(m * n)
    public static int spaceOptimizedTabulatedLongestCommonSubsequence(String text1, String text2){
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();

        int[] dp = new int[str2.length+1];

        for(int i = 1; i <= str1.length; i++){
            int[] temp = new int[str2.length+1];
            for(int j = 1; j <= str2.length; j++){
                if(str1[i-1] == str2[j-1])
                    temp[j] = 1 + dp[j-1];
                else
                    temp[j] = Math.max(dp[j], temp[j-1]);
            }
            dp = temp;
        }
        return dp[str2.length];
    }

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";

        System.out.println(recursiveLongestCommonSubsequence(text1, text2));
        System.out.println(memoizedLongestCommonSubsequence(text1, text2));
        System.out.println(tabulatedLongestCommonSubsequence(text1, text2));
        System.out.println(spaceOptimizedTabulatedLongestCommonSubsequence(text1, text2));
    }
}
