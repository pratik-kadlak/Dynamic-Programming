import java.util.Arrays;
import java.util.Comparator;

public class longestStringChain45 {

    static class Pair{
        int len; String word;
        Pair(int len, String word){
            this.len = len;
            this.word = word;
        }
    }

    public static boolean check(String s1, String s2){
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        
        if(c1.length != c2.length-1) return false;

        boolean flag = false;
        int i = 0, j = 0;

        while(i < c1.length){
            if(c1[i] == c2[j]){
                i++;
                j++;
            } else {
                if(flag == true) return false;
                flag = true;
                j++;
            }
        }
        return true;
    }

    public static int longestStrChain(String[] words) {
        int n = words.length;
        Pair[] pairs = new Pair[n];

        for(int i = 0; i < n; i++){
            pairs[i] = new Pair(words[i].length(), words[i]);
        }

        Arrays.sort(pairs, Comparator.comparingInt(p->p.len));

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int longestStringChain = 1;

        for(int index = 1; index < n; index++){
            for(int prevIndex = 0; prevIndex < index; prevIndex++){
                if(check(pairs[prevIndex].word, pairs[index].word)){
                    dp[index] = Math.max(dp[index], dp[prevIndex] + 1);
                }
            }
            if(dp[index] > longestStringChain)
                longestStringChain = dp[index];
        }

        return longestStringChain;
    }

    
    public static void main(String[] args) {
        String[] words = {"a","b","ba","bca","bda","bdca"};
        System.out.println(longestStrChain(words));
    }
}
