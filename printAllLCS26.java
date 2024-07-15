import java.util.*;

public class printAllLCS26 {
    
    // this prints all lcs
    public static void recursiveBacktrack(int i, int j, char[] str1, char[] str2, int index, char[] curr, int[][] dp, Set<String> set){
        if(i == 0 || j == 0){
            set.add(new String(curr));
            return;
        }

        if(str1[i-1] == str2[j-1]){
            curr[index] = str1[i-1];
            recursiveBacktrack(i-1, j-1, str1, str2, index-1, curr, dp, set);
        } else {
            if(dp[i][j] == dp[i-1][j])
                recursiveBacktrack(i-1, j, str1, str2, index, curr, dp, set);
            
            if(dp[i][j] == dp[i][j-1])
                recursiveBacktrack(i, j-1, str1, str2, index, curr, dp, set);
        }   

    }

    public static void printLCS(String text1, String text2){
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        
        int[][] dp = new int[str1.length+1][str2.length+1];

        for(int i = 1; i <= str1.length; i++){
            for(int j = 1; j <= str2.length; j++){
                if(str1[i-1] == str2[j-1]) dp[i][j] = 1 + dp[i-1][j-1];
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        // for printing any one of the lcs
        int i = str1.length;
        int j = str2.length;
        int len = dp[i][j];
        int index = len-1;
        char[] lcs = new char[len];

        while(i > 0 && j > 0){
            if(str1[i-1] == str2[j-1]){
                lcs[index] = str1[i-1];
                i--;
                j--;
                index--;
            } else if(dp[i][j] == dp[i][j-1]){
                j--;
            } else{
                i--;
            }
        }

        System.out.println("Just One LCS: " + new String(lcs));

        // for printing all lcs
        Set<String> set = new HashSet<>();
        int l = dp[str1.length][str2.length];
        char[] curr = new char[l];
        recursiveBacktrack(str1.length, str2.length, str1, str2, l-1, curr, dp, set);

        System.out.print("Using Recursion: ");
        for(String s: set){ 
            System.out.print(s + " ");
        }

    }
    
    public static void main(String[] args) {
        String text1 = "abaabaaaaa ";
        String text2 = "aabbaaaaaabb";
        
        printLCS(text1, text2);

    }
}
