public class numWaysToEvaluateBooleanExpresseionToTrue52 {

    public static int solveRecursiveEvaluateExp(int i, int j, char[] exp, int isTrue){
        if(i > j) return 0;
        if(i == j){
            if(isTrue == 1) return exp[i] == 'T' ? 1 : 0;
            else return exp[i] == 'F' ? 1 : 0;
        }
        
        int ways = 0;
        for(int k = i+1; k <= j-1; k+=2){
            int lt = solveRecursiveEvaluateExp(i, k-1, exp, 1);
            int lf = solveRecursiveEvaluateExp(i, k-1, exp, 0);
            int rt = solveRecursiveEvaluateExp(k+1, j, exp, 1);
            int rf = solveRecursiveEvaluateExp(k+1, j, exp, 0);
            
            if(exp[k] == '&'){
                if(isTrue == 1) ways += (lt * rt);
                else ways += ((lt * rf) + (lf * rt) + (lf * rf));
            } else if(exp[k] == '|') {
                if(isTrue == 1) ways += ((lt * rt) + (lt * rf) + (lf * rt));
                else ways += (lf * rf);
            } else if(exp[k] == '^') {
                if(isTrue == 1) ways += ((lt * rf) + (lf * rt));
                else ways += ((lt * rt) + (lf * rf));
            } 
        }

        return ways;
    }
    
    public static int recursiveEvaluateExp(String exp){
        char[] ch = exp.toCharArray();
        return solveRecursiveEvaluateExp(0, exp.length()-1, ch, 1);
    }

    public static int solveMemoizedEvaluateExp(int i, int j, char[] ch, int isTrue, int[][][] dp){
        if(i > j) return 0;
        if(i == j){
            if(isTrue == 1) return dp[i][j][1] = ch[i] == 'T' ? 1 : 0;
            else return dp[i][j][0] = ch[i] == 'F' ? 1 : 0;
        }
        if(dp[i][j][isTrue] != -1) return dp[i][j][isTrue];
        
        int ways = 0;
        for(int k = i+1; k <= j-1; k+=2){
            int lt = solveMemoizedEvaluateExp(i, k-1, ch, 1, dp);
            int lf = solveMemoizedEvaluateExp(i, k-1, ch, 0, dp);
            int rt = solveMemoizedEvaluateExp(k+1, j, ch, 1, dp);
            int rf = solveMemoizedEvaluateExp(k+1, j, ch, 0, dp);

            if(ch[k] == '&') {
                if(isTrue == 1) ways += (lt * rt);
                else ways += ((lt * rf) + (lf * rt) + (lf * rf));
            } else if(ch[k] == '|') {
                if(isTrue == 1) ways += ((lt * rt) + (lt * rf) + (lf * rt));
                else ways += (lf * rf);
            } else if(ch[k] == '^') {
                if(isTrue == 1) ways += ((lt * rf) + (lf * rt));
                else ways += ((lt * rt) + (lf * rf));
            }
        }
        return dp[i][j][isTrue] = ways;
    }

    public static int memoizedEvaluateExp(String exp){
        char[] ch = exp.toCharArray();
        int n = ch.length;
        int[][][] dp = new int[n][n][2];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < 2; k++)
                    dp[i][j][k]= -1;
            }
        }
        
        return solveMemoizedEvaluateExp(0, n-1, ch, 1, dp);
    }

    public static int tabulatedEvaluateExp(String exp){
        char[] ch = exp.toCharArray();
        int n = ch.length;
        int[][][] dp = new int[n][n][2];

        for(int i = 0; i < ch.length; i++){
            if(ch[i] == 'T') {
                dp[i][i][1] = 1;
                dp[i][i][0] = 0;
            } else if(ch[i] == 'F') {
                dp[i][i][1] = 0;
                dp[i][i][0] = 1;
            }
        }

        for(int i = n-3; i >= 0; i-=2){
            for(int j = i+2; j < n; j+=2){
                for(int k = i+1; k <= j-1; k+=2){

                    int lt = dp[i][k-1][1]; 
                    int lf = dp[i][k-1][0]; 
                    int rt = dp[k+1][j][1]; 
                    int rf = dp[k+1][j][0]; 

                    if(ch[k] == '&'){
                        dp[i][j][1] += (lt * rt); 
                        dp[i][j][0] += (lt * rf + lf * rt + lf * rf);                   
                    } else if(ch[k] == '|'){
                        dp[i][j][1] += (lt * rt + lt * rf + lf * rt);
                        dp[i][j][0] += (lf * rf);
                    } else if(ch[k] == '^'){
                        dp[i][j][1] += (lf * rt + lt * rf);
                        dp[i][j][0] += (lf * rf + lt * rt);
                    }
                }
            }
        }
        return dp[0][n-1][1];
    }

    public static void main(String[] args) {
        String exp = "F|T^F";
        // String exp = "F^F^F^F&T|T|F|T|F|F|T|T|T|T&T|T|T&T|F&T|F|T|T|T^T|F^T|T&F^T|F|T|F|T&T|T^F|F^T&T^T&T^T&T^T&F&T^F|F^T|T|F|F^F|F&F|F|T&F&F";

        System.out.println(recursiveEvaluateExp(exp));
        System.out.println(memoizedEvaluateExp(exp));
        System.out.println(tabulatedEvaluateExp(exp));
    }
    
}