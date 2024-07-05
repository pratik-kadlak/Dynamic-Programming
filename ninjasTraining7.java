import java.util.Arrays;

public class ninjasTraining7 {

    // My Soln (TC->O(2^n), SC->O(n))
    public static void solveRecursiveNinjaTraining(int day, int currPoints, int lastActivity, int n, int[] ans, int[][] points){
        if(day == n){
            ans[0] = Math.max(ans[0], currPoints);
            return;
        }

        for(int i = 0; i < 3; i++){
            if(i != lastActivity){
                currPoints += points[day][i];
                solveRecursiveNinjaTraining(day+1, currPoints, i, n, ans, points);
                currPoints -= points[day][i];
            }
        }
    }

    public static int recursiveNinjaTraining(int n, int[][] points){
        int[] ans = new int[1];
        solveRecursiveNinjaTraining(0, 0, -1,  n, ans, points);
        return ans[0];
    }

    // Recursive Soln (TC->O(2^n), SC->O(n))
    public static int solveRecursiveNinjaTraining2(int day, int lastActivity, int[][] points){
        if(day == 0){
            int maxPoints = 0;
            for(int i = 0; i < 3; i++){
                if(i!=lastActivity && points[day][i] > maxPoints)
                    maxPoints = points[day][i];
            }
            return maxPoints;
        }

        int maxPoints = 0;
        for(int i = 0; i < 3; i++){
            if(i != lastActivity){
                int currPoints = points[day][i] + solveRecursiveNinjaTraining2(day-1, i, points);
                maxPoints = Math.max(maxPoints, currPoints);
            }
        }
        return maxPoints;
    }

    public static int recursiveNinjaTraining2(int n, int[][] points){
        return solveRecursiveNinjaTraining2(n-1, 3, points);
    }

    // memoized soln (TC->O(n), SC->O(n*4)+O(n))
    public static int solveMeoizedNinjaTraining(int day, int lastActivity, int[][] dp, int[][] points){
        if(day == 0){
            int maxPoints = 0;
            for(int i = 0; i < 3; i++){
                if(i != lastActivity && points[day][i] > maxPoints)
                    maxPoints = points[day][i];
            }
            return dp[day][lastActivity] = maxPoints;
        }
        if(lastActivity!= 3 && dp[day][lastActivity] != -1) return dp[day][lastActivity];

        int maxPoints = 0;
        for(int i = 0; i < 3; i++){
            if(i != lastActivity){
                int currPoints = points[day][i] + solveMeoizedNinjaTraining(day-1, i, dp, points);
                maxPoints = Math.max(maxPoints, currPoints);
            }
        }
        return dp[day][lastActivity] = maxPoints;
    }

    public static int memoizedNinjaTraining(int n, int[][] points){
        int[][] dp = new int[n][4];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 3; j++)
                dp[i][j] = -1;
        }

        int ans = solveMeoizedNinjaTraining(n-1, 3, dp, points);
        return ans;
    }

    // tabulated soln (TC->O(n), SC->O(n*4))
    public static int tabulatedNinjaTraining(int n, int[][] points){
        int[][] dp = new int[n][4];
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for(int day = 1; day < n; day++){
            for(int lastActivity = 0; lastActivity <= 3; lastActivity++){
                int maxPoints = 0;
                for(int i = 0; i < 3; i++){
                    if(i != lastActivity){
                        int currPoints = points[day][i] + dp[day-1][i];
                        maxPoints = Math.max(maxPoints, currPoints);
                    }
                }
                dp[day][lastActivity] = maxPoints;
            }
        }
        return dp[n-1][3];
    }

    // space optimized tabulated soln (TC->O(n), SC->O(4))
    public static int spaceOptimizedTabulatedNinjaTraining(int n, int[][] points){
        int[] dp = new int[4];
        dp[0] = Math.max(points[0][1], points[0][2]);
        dp[1] = Math.max(points[0][0], points[0][2]);
        dp[2] = Math.max(points[0][0], points[0][1]);
        dp[3] = Math.max(points[0][0], dp[0]);

        for(int day = 1; day < n; day++){
            int[] temp = new int[4];
            for(int lastActivity = 0; lastActivity < 4; lastActivity++){
                int maxPoints = 0;
                for(int i = 0; i < 3; i++){
                    if(i != lastActivity){
                        int currPoints = points[day][i] + dp[i];
                        maxPoints = Math.max(maxPoints, currPoints);
                    }
                }
                temp[lastActivity] = maxPoints;
            }
            dp = temp;
        }
        return dp[3];
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] points = {{1, 2, 5}, {3, 1, 1}, {3, 3, 3}};
        
        System.out.println(recursiveNinjaTraining(n, points));
        System.out.println(recursiveNinjaTraining2(n, points));
        System.out.println(memoizedNinjaTraining(n, points)); 
        System.out.println(tabulatedNinjaTraining(n, points));
        System.out.println(spaceOptimizedTabulatedNinjaTraining(n, points));
    }
}
