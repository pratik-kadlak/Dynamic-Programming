public class editDistance33 {

    // recursive soln: TC -> O(3^min(m, n)), SC -> O(min(m,n))
    public static int solveRecursiveMinDistance(int i, int j, char[] src, char[] trg){
        if(j == -1) return i+1;
        if(i == -1) return j+1; 

        // insert character
        int ins = 1 + solveRecursiveMinDistance(i, j-1, src, trg);

        // delete character
        int del = 1 + solveRecursiveMinDistance(i-1, j, src, trg);

        // replace charachter
        int rep = 0;
        if(src[i] == trg[j]) rep = solveRecursiveMinDistance(i-1, j-1, src, trg);
        else rep = 1 + solveRecursiveMinDistance(i-1, j-1, src, trg);

        return Math.min(ins, Math.min(del, rep));

    }

    public static int recursiveMinDistance(String word1, String word2){
        char[] src = word1.toCharArray();
        char[] trg = word2.toCharArray();

        return solveRecursiveMinDistance(src.length-1, trg.length-1, src, trg);
    }

    // memoized soln: TC -> O(m*n), SC -> O(m*n + min(m, n))
    public static int solveMemoizedMinDistance(int i, int j, char[] src, char[] trg, int[][] dp){
        if(j == -1) return i+1;
        if(i == -1) return j+1;

        if(dp[i][j] != -1) return dp[i][j];

        // insert character
        int ins = 1 + solveMemoizedMinDistance(i, j-1, src, trg, dp);

        // delete charcter
        int del = 1 + solveMemoizedMinDistance(i-1, j, src, trg, dp);

        // replace character
        int rep;
        if(src[i] == trg[j]) rep = solveMemoizedMinDistance(i-1, j-1, src, trg, dp);
        else rep = 1 + solveMemoizedMinDistance(i-1, j-1, src, trg, dp);

        return dp[i][j] = Math.min(ins, Math.min(del, rep));
    }

    public static int memoizedMinDistance(String word1, String word2){
        char[] src = word1.toCharArray();
        char[] trg = word2.toCharArray();
        
        int[][] dp = new int[src.length+1][trg.length+1];
        for(int i = 0; i <= src.length; i++){
            for(int j = 0; j <= trg.length; j++){
                dp[i][j] = -1;
            }
        }
        return solveMemoizedMinDistance(src.length-1, trg.length-1, src, trg, dp);
    }

    // tabulated soln: TC -> O(m*n), SC -> O(m*n)
    public static int tabulatedMinDistance(String word1, String word2){
        char[] src = word1.toCharArray();
        char[] trg = word2.toCharArray();

        int[][] dp = new int[src.length+1][trg.length+1];

        for(int i = 0; i <= src.length; i++)
            dp[i][0] = i;
        for(int j = 0; j <= trg.length; j++)
            dp[0][j] = j;
            
        for(int i = 1; i <= src.length; i++){
            for(int j = 1; j <= trg.length; j++){
                int ins = 1 + dp[i][j-1];
                int del = 1 + dp[i-1][j];
                int rep;
                if(src[i-1] == trg[j-1]) rep = dp[i-1][j-1];
                else rep = 1 + dp[i-1][j-1];

                dp[i][j] = Math.min(ins, Math.min(del, rep));
            }
        }
        return dp[src.length][trg.length];
    }

    // tabulated soln: TC -> O(m*n), SC -> O(2n)
    public static int spaceOptimizedTabulatedMinDistance(String word1, String word2){
        char[] src = word1.toCharArray();
        char[] trg = word2.toCharArray();

        int[] dp = new int[trg.length+1];
        for(int i = 0; i < dp.length; i++) 
            dp[i] = i;

        for(int i = 1; i <= src.length; i++){
            int[] temp = new int[trg.length+1];
            temp[0] = i;
            for(int j = 1; j <= trg.length; j++){
                int ins = 1 + temp[j-1];
                int del = 1 + dp[j];
                int rep = 0;
                if(src[i-1] == trg[j-1]) rep = dp[j-1];
                else rep = 1 + dp[j-1];

                temp[j] = Math.min(ins, Math.min(del, rep));
            }
            dp = temp;
        }
        return dp[trg.length];
    }

    public static void main(String[] args) {
        String word1 = "dinitrophenyl";
        String word2 = "acetylphenyl";

        System.out.println(recursiveMinDistance(word1, word2));
        System.out.println(memoizedMinDistance(word1, word2));
        System.out.println(tabulatedMinDistance(word1, word2));
        System.out.println(spaceOptimizedTabulatedMinDistance(word1, word2));
    }
    
}
