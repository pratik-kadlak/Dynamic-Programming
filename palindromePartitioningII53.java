public class palindromePartitioningII53 {

    public static boolean isPalindrome(int i, int j, char[] ch){
        while(i <= j){
            if(ch[i++] != ch[j--]) return false;
        }
        return true;
    }

    // My Soln

    public static void solveRecursiveMinCut(int index, char[] ch, int currCuts, int[] ans){
        if(index >= ch.length){
            ans[0] = Math.min(ans[0], currCuts-1);
            return;
        }
        
        for(int i = index; i < ch.length; i++){
            if(isPalindrome(index, i, ch)){
                solveRecursiveMinCut(i+1, ch, currCuts+1, ans);
            }
        }
    }

    public static int recursiveMinCut0(String s){
        char[] ch = s.toCharArray();
        int[] ans = {s.length()-1};
        solveRecursiveMinCut(0, ch, 0, ans);
        return ans[0];
    }


    // Striver Soln

    // Recursive Soln: TC ->  O(2^n), SC -> O(n)
    public static int solveRecursiveMinCut(int index, char[] ch){
        if(index == ch.length) return 0;
        int mnCost = (int) 1e9;

        for(int i = index; i < ch.length; i++){
            if(isPalindrome(index, i, ch)){
                mnCost = Math.min(mnCost, 1 + solveRecursiveMinCut(i+1, ch));
            }
        }
        return mnCost;
    }

    public static int recursiveMinCut(String s){
        char[] ch = s.toCharArray();
        return solveRecursiveMinCut(0, ch);
    }

    // Memoized Soln: TC ->  O(n^3), SC -> O(n+n)
    public static int solveMemoizedMinCut(int index, char[] ch, int[] dp){
        if(index == ch.length) return 0;
        if(dp[index] != -1) return dp[index];

        int mnCost = (int) 1e9;
        for(int i = index; i < ch.length; i++){
            if(isPalindrome(index, i, ch)){
                mnCost = Math.min(mnCost, 1 + solveMemoizedMinCut(i+1, ch, dp));
            }
        }
        return dp[index] = mnCost;
    }

    public static int memoizedMinCut(String s){
        char[] ch = s.toCharArray();
        int[] dp = new int[s.length()];
        for(int i = 0; i < dp.length; i++)
            dp[i] = -1;

        return solveMemoizedMinCut(0, ch, dp)-1;

    }

    // Tabulated Soln: TC ->  O(n^3), SC -> O(n)
    public static int tabulatedMinCut(String s){
        int n = s.length();
        char[] ch = s.toCharArray();
        int[] dp = new int[n+1];
        dp[n] = 0;

        for(int i = n-1; i >= 0; i--){
            dp[i] = (int) 1e9;
            for(int j = i; j < n; j++){
                if(isPalindrome(i, j, ch)){
                    dp[i] = Math.min(dp[i], 1 + dp[j+1]);
                }
            }
        }

        return dp[0]-1;

    }


    public static void main(String[] args) {
        String s = "aab";
        // String s = "ababababababababababababcbabababababababababababa";

        System.out.println(recursiveMinCut0(s));

        System.out.println(recursiveMinCut(s)-1);
        System.out.println(memoizedMinCut(s));
        System.out.println(tabulatedMinCut(s));
    }

}
