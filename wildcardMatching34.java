public class wildcardMatching34 {

    // recursive soln TC -> O(3^min(m,n)), SC ->  O(m+n)
    public static boolean solveRecursiveIsMatch(int i, int j, char[] src, char[] ptrn){
        if(i == -1 && j == -1) return true;
        if(i == -1) {
            if(ptrn[j] == '*') return solveRecursiveIsMatch(i, j-1, src, ptrn);
            else return false;
        } 
        if(j == -1) return false;

        if(ptrn[j] == '*') 
            return solveRecursiveIsMatch(i-1, j, src, ptrn) || 
                   solveRecursiveIsMatch(i-1, j-1, src, ptrn) || 
                   solveRecursiveIsMatch(i, j-1, src, ptrn);

        if(ptrn[j] == '?' || src[i] == ptrn[j])
            return solveRecursiveIsMatch(i-1, j-1, src, ptrn);
        
        return false;
    }

    public static boolean recursiveIsMatch(String s, String p){
        char[] src = s.toCharArray();
        char[] ptrn = p.toCharArray();

        return solveRecursiveIsMatch(src.length-1, ptrn.length-1, src, ptrn);
    }

    // memoized soln TC -> O(m*n), SC ->  O(m*n + (m+n))
    public static boolean solveMemoizedIsMatch(int i, int j, char[] src, char[] ptrn, int[][] dp){
        if(i == -1 && j == -1) return true;
        if(i == -1){
            if(ptrn[j] == '*') return solveMemoizedIsMatch(i, j-1, src, ptrn, dp);
            return false;
        }
        if(j == -1) return false;

        if(dp[i][j] != -1){
            if(dp[i][j] == 1) return true;
            return false;
        } 

        if(ptrn[j] == '*'){
            if(solveMemoizedIsMatch(i-1, j, src, ptrn, dp)  ||
               solveMemoizedIsMatch(i-1, j-1, src, ptrn, dp) || 
               solveMemoizedIsMatch(i, j-1, src, ptrn, dp)) {
                dp[i][j] = 1;
                return true;
            } 
        }

        if(ptrn[j] == '?' || src[i] == ptrn[j]){
            if(solveMemoizedIsMatch(i-1, j-1, src, ptrn, dp)){
                dp[i][j] = 1;
                return true;
            } 
        }
        dp[i][j] = 0;
        return false;
    }

    public static boolean memoizedIsMatch(String s, String t){
        char[] src = s.toCharArray();
        char[] ptrn = t.toCharArray();

        int[][] dp = new int[src.length+1][ptrn.length+1];
        for(int i = 0; i <= src.length; i++){
            for(int j = 0; j <= ptrn.length; j++)
                dp[i][j] = -1;
        }

        return solveMemoizedIsMatch(src.length-1, ptrn.length-1, src, ptrn, dp);
    }

    // tabulated soln TC -> O(m*n), SC ->  O(m*n)
    public static boolean tabulatedIsMatch(String s, String p){
        char[] src = s.toCharArray();
        char[] ptrn = p.toCharArray();

        boolean[][] dp = new boolean[src.length+1][ptrn.length+1];
        dp[0][0] = true;
        for(int i = 1; i <= src.length; i++){
            dp[i][0] = false;
        }

        for(int j = 1; j <= ptrn.length; j++){
            if(ptrn[j-1] == '*'){
                dp[0][j] = dp[0][j-1];
            } else dp[0][j] = false;
        }

        for(int i = 1; i <= src.length; i++){
            for(int j = 1; j <= ptrn.length; j++){
                if(ptrn[j-1] == '*'){
                    dp[i][j] = dp[i-1][j] || dp[i][j-1] || dp[i-1][j-1];
                } else if(ptrn[j-1] == '?' || src[i-1] == ptrn[j-1]){
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = false;
                }       
            }
        }
        return dp[src.length][ptrn.length];
    }

    // tabulated soln TC -> O(m*n), SC ->  O(2 * n)
    public static boolean spaceOptimizedTabulatedIsMatch(String s, String p){
        char[] src = s.toCharArray();
        char[] ptrn = p.toCharArray();

        boolean[] dp = new boolean[ptrn.length+1];
        dp[0] = true;
        for(int i = 1; i <= ptrn.length; i++){
            if(ptrn[i-1] == '*'){
                dp[i] = dp[i-1];
            } else dp[i] = false;
        }

        for(int i = 1; i <= src.length; i++){
            boolean[] temp = new boolean[ptrn.length+1];
            temp[0] = false;
            for(int j = 1; j <= ptrn.length; j++){
                if(ptrn[j-1] == '*'){
                    temp[j] = dp[j-1] || dp[j] || temp[j-1];
                } else if(ptrn[j-1] == '?' || src[i-1] == ptrn[j-1]){
                    temp[j] = dp[j-1];
                } else {
                    temp[j] = false;
                }
            }
            dp = temp;
        }
        return dp[ptrn.length];
    }


    public static void main(String[] args) {
        String s = "cattle";
        String p = "ca*le";

        System.out.println(recursiveIsMatch(s, p));
        System.out.println(memoizedIsMatch(s, p));
        System.out.println(tabulatedIsMatch(s, p));
        System.out.println(spaceOptimizedTabulatedIsMatch(s, p));
    }
    
}