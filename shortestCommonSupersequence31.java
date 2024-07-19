public class shortestCommonSupersequence31 {

    // My Soln
    public static  String lcs(String text1, String text2){
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();

        int[][] dp = new int[str1.length+1][str2.length+1];

        for(int i = 1; i <= str1.length; i++){
            for(int j = 1; j <= str2.length; j++){
                if(str1[i-1] == str2[j-1])
                    dp[i][j] = 1 + dp[i-1][j-1];
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        int len = dp[str1.length][str2.length];
        int i = str1.length;
        int j = str2.length; 
        int index = len - 1;
        char[] ans = new char[len];

        while(i > 0 && j > 0 && index >= 0){
            if(str1[i-1] == str2[j-1]){
                ans[index] = str1[i-1];
                index--;
                i--;
                j--;
            }
            else if(dp[i][j] == dp[i-1][j]) i--;
            else j--;
        }

        return new String(ans);
    }

    public static String shortestCommonSupersequence(String str1, String str2) {
        String lcs = lcs(str1, str2);    
        char[] ch = lcs.toCharArray();
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int[] indices = new int[s2.length - ch.length];

        int i = 0, j = 0;
        int index = 0;
        while(i < ch.length && j < s2.length){
            if(ch[i] == s2[j]){
                i++;
                j++;
            } else {
                indices[index] = j;
                j++;
                index++;
            }
        }

        while(j < s2.length){
            indices[index] = j;
            j++;
            index++;
        }

        StringBuilder str = new StringBuilder();
        i = 0;
        j = 0;
        index = 0;

        while(i < s1.length && j < s2.length){
            if(s1[i] == s2[j]){
                str.append(s1[i]);
                i++;
                j++;
            } else if(index < indices.length && j == indices[index]){
                str.append(s2[j]);
                j++;
                index++;
            } else {
                str.append(s1[i]);
                i++;
            }
        }

        while(i < s1.length){
            str.append(s1[i]);
            i++;
        }

        while(index < indices.length){
            str.append(s2[indices[index]]);
            index++;
        }

        return str.toString();
    }

    // Striver Soln
    public static String shortestCommonSupersequence1(String str1, String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int[][] dp = new int[s1.length+1][s2.length+1];

        for(int i = 1; i <= s1.length; i++){
            for(int j = 1; j <= s2.length; j++){
                if(s1[i-1] == s2[j-1])
                    dp[i][j] = 1 + dp[i-1][j-1];
                else 
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        StringBuilder str = new StringBuilder();
        int i = s1.length;
        int j = s2.length;

        while(i > 0 && j > 0){
            if(s1[i-1] == s2[j-1]){
                str.append(s1[i-1]);
                i--;
                j--;
            } else if (dp[i-1][j] == dp[i][j]){
                str.append(s1[i-1]);
                i--;
            } else {
                str.append(s2[j-1]);
                j--;
            }
        }

        while(i > 0){
            str.append(s1[i-1]);
            i--;
        }

        while(j > 0){
            str.append(s2[j-1]);
            j--;
        }
            

        return str.reverse().toString();
    }

    public static void main(String[] args) {
        String str1 = "accabcba";
        String str2 = "aacbbbbbaa";

        System.out.println(shortestCommonSupersequence(str1, str2));
        System.out.println(shortestCommonSupersequence1(str1, str2));


    }
}