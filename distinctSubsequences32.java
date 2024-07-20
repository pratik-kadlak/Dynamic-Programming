public class distinctSubsequences32 {

    // Recursive Soln : TC -> O(2^n * 2^m), SC -> O(n)
    public static int solveRecuriveNumDistinct(int i, int j, char[] src, char[] trg){
        if(j == trg.length) return 1;
        if(i == src.length) return 0;
        
        int matched = 0;
        if(src[i] == trg[j]){
            matched = solveRecuriveNumDistinct(i+1, j+1, src, trg);
        }

        int notMatched = solveRecuriveNumDistinct(i+1, j, src, trg);
        return matched + notMatched;
    }

    public static int recursiveNumDistinct(String s, String t) {
        if(t.length() > s.length()) return 0;
        char[] src = s.toCharArray();
        char[] trg = t.toCharArray();

        return solveRecuriveNumDistinct(0, 0, src, trg);
    }

    // Memoized Soln : TC -> O(m * n), SC -> O(m*n + n)
    public static int solveMemoizedNumDistinct(int i, int j, char[] src, char[] trg, int[][] dp){
        if(j == trg.length) return dp[i][j] = 1;
        if(i == src.length) return dp[i][j] = 0;

        if(dp[i][j] != -1) return dp[i][j];

        int matched = 0;
        if(src[i] == trg[j])
            matched = solveMemoizedNumDistinct(i+1, j+1, src, trg, dp);
        
        int notMatched = solveMemoizedNumDistinct(i+1, j, src, trg, dp);

        return dp[i][j] = matched + notMatched;

    }

    public static int memoizedNumDistinct(String s, String t){
        if(t.length() > s.length()) return 0;
        char[] src = s.toCharArray();
        char[] trg = t.toCharArray();

        int[][] dp = new int[src.length+1][trg.length+1];
        for(int i = 0; i <= src.length; i++){
            for(int j = 0; j <= trg.length; j++){
                dp[i][j] = -1;
            }
        }
        return solveMemoizedNumDistinct(0, 0, src, trg, dp);
    }

    // Tabulated Soln : TC -> O(m * n), SC -> O(m*n)
    public static int tabulatedNumDistinct(String s, String t){
        if(t.length() > s.length()) return 0;        
        char[] src = s.toCharArray();
        char[] trg = t.toCharArray();

        int[][] dp = new int[src.length+1][trg.length+1];
        for(int i = 0; i <= src.length; i++)
            dp[i][trg.length] = 1;

        for(int i = src.length-1; i >= 0; i--){
            for(int j = trg.length-1; j >= 0; j--){
                dp[i][j] = dp[i+1][j];
                if(src[i] == trg[j])
                    dp[i][j] += dp[i+1][j+1];
            }
        }

        return dp[0][0];
    }

    // Space Optimized  Soln : TC -> O(m * n), SC -> O(2m)
    public static int spaceOptimizedTabulatedNumDistinct(String s, String t){
        if(s.length() < t.length()) return 0;
        char[] src = s.toCharArray();
        char[] trg = t.toCharArray();

        int[] dp = new int[trg.length+1];
        dp[trg.length] = 1;

        for(int i = src.length-1; i >= 0; i--){
            int[] temp = new int[trg.length+1];
            temp[temp.length-1] = 1;
            for(int j = trg.length-1; j >= 0; j--){
                temp[j] = dp[j];
                if(src[i] == trg[j])
                    temp[j] += dp[j+1];
            }
            dp = temp;
        }
        return dp[0];
    }

    // Space Optimized  Soln : TC -> O(m * n), SC -> O(m)
    public static int spaceOptimizedTabulatedNumDistinct1(String s, String t){
        if(s.length() < t.length()) return 0;
        char[] src = s.toCharArray();
        char[] trg = t.toCharArray();

        int[] dp = new int[trg.length+1];
        dp[trg.length] = 1;

        for(int i = src.length-1; i >= 0; i--){
            dp[dp.length-1] = 1;
            for(int j = trg.length-1; j >= 0; j--){
                if(src[i] == trg[j])
                    dp[j] += dp[j+1];
            }
        }
        return dp[0];
    }
    
    public static void main(String[] args) {
        String s = "babgbag";
        String t = "bag";

        System.out.println(recursiveNumDistinct(s, t));
        System.out.println(memoizedNumDistinct(s, t));
        System.out.println(tabulatedNumDistinct(s, t));
        System.out.println(spaceOptimizedTabulatedNumDistinct(s, t));
        System.out.println(spaceOptimizedTabulatedNumDistinct1(s, t));
    }

}
