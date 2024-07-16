public class minOperationsOnStrings30 {

    // tabulated soln TC -> O(n * m), SC -> O(n * m)
    public static int tabulatedMinDistance(String word1, String word2){
        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();

        int[][] dp = new int[str1.length+1][str2.length+1];

        for(int i = 1; i <= str1.length; i++){
            for(int j = 1; j <= str2.length; j++){
                if(str1[i-1] == str2[j-1])
                    dp[i][j] = 1 + dp[i-1][j-1];
                else 
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        return str1.length + str2.length - 2 * dp[str1.length][str2.length];
    }
    public static void main(String[] args) {
        String word1 = "sea";
        String word2 = "eat";

        System.out.println(tabulatedMinDistance(word1, word2));
    }
}
