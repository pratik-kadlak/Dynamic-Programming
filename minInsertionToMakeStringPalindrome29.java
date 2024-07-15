public class minInsertionToMakeStringPalindrome29 {

    // tabulated soln TC -> O(n * n), SC -> O(n * n)
    public static int minInsertions(String s){
        char[] str1 = s.toCharArray();
        char[] str2 = new char[str1.length];

        int index = 0;
        while(index < str1.length){
            str2[index] = str1[str1.length-1-index];
            index++;
        }

        int[][] dp = new int[str1.length+1][str2.length+1];

        for(int i = 1; i <= str1.length; i++){
            for(int j = 1; j <= str2.length; j++){
                if(str1[i-1] == str2[j-1])
                    dp[i][j] = 1 + dp[i-1][j-1];
                else 
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        return str1.length - dp[str1.length][str2.length];
    }

    public static void main(String[] args) {
        String s = "mbadm";

        System.out.println(minInsertions(s));
    }
}