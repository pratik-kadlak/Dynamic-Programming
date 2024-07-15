public class longestSubstring27 {

    public static int tabulatedLongestSubstring(String text1, String text2){
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();

        int[][] dp = new int[str1.length+1][str2.length+1];

        for(int i = 1; i <= str1.length; i++){
            for(int j = 1; j <= str2.length; j++){
                if(str1[i-1] == str2[j-1]) dp[i][j] = 1 + dp[i-1][j-1];
                else dp[i][j] = 0;
            }
        }

        int maxLenSubstr = 0;

        for(int i = 1; i <= str1.length; i++){
            for(int j = 1; j <= str2.length; j++){
                maxLenSubstr = Math.max(maxLenSubstr, dp[i][j]);
            }
        }

        return maxLenSubstr;
    }
    
    public static void main(String[] args) {
        String text1 = "abcdgh";
        String text2 = "acdghr";

        System.out.println(tabulatedLongestSubstring(text1, text2));
    }

}
