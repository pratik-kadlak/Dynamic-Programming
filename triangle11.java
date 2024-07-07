import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class triangle11 {

    // recursive soln (TC->O(2^((n*(n+1))/2)), SC->O(n))
    public static int solveRecursiveMinTotal(int index, int level, List<List<Integer>> triangle){
        if(level == 0) return triangle.get(0).get(0);

        int left = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;

        if(index > 0) left = solveRecursiveMinTotal(index-1, level-1, triangle);
        if(index < triangle.get(level-1).size()) right = solveRecursiveMinTotal(index, level-1, triangle);

        return triangle.get(level).get(index) + Math.min(left, right);
    }

    public static int recursiveMinTotal(List<List<Integer>> triangle){
        int ans = Integer.MAX_VALUE;
        int level = triangle.size();
        for(int i = 0; i < triangle.get(level-1).size(); i++){
            ans = Math.min(ans, solveRecursiveMinTotal(i, level-1, triangle));
        }
        return ans;
    }

    // memoized soln (TC->O(n^2)), SC->O(n^2)) where n is the no of rows
    public static int solveMemoizedMinTotal(int index, int level, List<List<Integer>> dp, List<List<Integer>> triangle){
        if(level == 0) {
            dp.get(0).set(0, triangle.get(0).get(0));
            return triangle.get(0).get(0);
        }
        if(dp.get(level).get(index) != -1) return dp.get(level).get(index);

        int left = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;

        if(index > 0) left = solveMemoizedMinTotal(index-1, level-1, dp, triangle);
        if(index < dp.get(level-1).size()) right = solveMemoizedMinTotal(index, level-1, dp, triangle);
    
        int minTotal = triangle.get(level).get(index) + Math.min(left, right);
        dp.get(level).set(index, minTotal);
        return minTotal;
    }

    public static int memoizedMinTotal(List<List<Integer>> triangle){
        List<List<Integer>> dp = new ArrayList<>();
        
        for(int i = 0; i < triangle.size(); i++){
            List<Integer> temp = new ArrayList<>();
            for(int j = 0; j < triangle.get(i).size(); j++){
                temp.add(-1);
            }
            dp.add(temp);
        }

        int ans = Integer.MAX_VALUE;
        int level = triangle.size();
        for(int i = 0; i < triangle.get(level-1).size(); i++)
            ans = Math.min(ans, solveMemoizedMinTotal(i, level-1, dp, triangle));

        return ans;
    }

    // tabulation soln  (TC->O(n^2)), SC->O(n^2))
    public static int tabulatedMinTotal(List<List<Integer>> triangle){
        List<List<Integer>> dp = new ArrayList<>();
        dp.add(triangle.get(0));

        int level = triangle.size();
        for(int i = 1; i < level; i++){
            List<Integer> temp = new ArrayList<>();
            for(int j = 0; j < triangle.get(i).size(); j++){
                int left = Integer.MAX_VALUE;
                int right = Integer.MAX_VALUE;

                if(j > 0) left = dp.get(i-1).get(j-1);
                if(j < dp.get(i-1).size()) right = dp.get(i-1).get(j);

                int minTotal = triangle.get(i).get(j) + Math.min(left, right);
                temp.add(minTotal);
            }
            dp.add(temp);
        }
        
        int minTotal = dp.get(level-1).get(0);
        for(int i = 1; i < dp.get(level-1).size(); i++){
            if(dp.get(level-1).get(i) < minTotal)
                minTotal = dp.get(level-1).get(i);
        }
        return minTotal;
    }

    // space optimized soln (TC->O(n^2)), SC->O(n))
    public static int spaceOptimizedTabulatedMinTotal(List<List<Integer>> triangle){
        List<Integer> dp = new ArrayList<>();
        dp.add(triangle.get(0).get(0));

        int level = triangle.size();
        for(int i = 1; i < level; i++){
            List<Integer> temp = new ArrayList<>();
            for(int j = 0; j < triangle.get(i).size(); j++){
                int left = Integer.MAX_VALUE;
                int right = Integer.MAX_VALUE;
                
                if(j > 0) left = dp.get(j-1);
                if(j < dp.size()) right = dp.get(j);

                int minTotal = triangle.get(i).get(j) + Math.min(left, right);
                temp.add(minTotal);
            }
            dp = temp;
        }

        int minTotal = dp.get(0);
        for(int i = 1; i < dp.size(); i++)
            minTotal = Math.min(minTotal, dp.get(i));

        return minTotal;
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4,1,8,3));
        
        System.out.println(recursiveMinTotal(triangle));
        System.out.println(memoizedMinTotal(triangle));
        System.out.println(tabulatedMinTotal(triangle));
        System.out.println(spaceOptimizedTabulatedMinTotal(triangle));
    }
}
