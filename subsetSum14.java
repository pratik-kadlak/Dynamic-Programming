public class subsetSum14 {

    // my soln recursive (TC->O(2^n), SC->O(n))
    public static boolean solveRecursiveIsSubsetSum(int index, int sum, int[] arr){
        if(sum < 0) return false;
        if(index == arr.length){
            if(sum == 0) return true;
            return false;
        }

        if(solveRecursiveIsSubsetSum(index+1, sum-arr[index], arr)) return true;
        if(solveRecursiveIsSubsetSum(index+1, sum, arr)) return true;
        return false;
    }

    public static boolean recursiveIsSubsetSum(int sum, int[] arr){
        return solveRecursiveIsSubsetSum(0, sum, arr);
    }
    
    // my soln memoized (TC->O(n . target), SC->O(n . target + n))
    public static boolean solveMemoizedIsSubsetSum(int index, int sum, int[] arr, int[][] dp){
        if(sum < 0) return false;
        if(index == arr.length-1){
            if(sum == 0 || sum == arr[index]){
                dp[index][sum] = 1;
                return true;
            } 
            dp[index][sum] = -1;
            return false;
        }
        if(dp[index][sum] != 0) return false;
        

        if(solveMemoizedIsSubsetSum(index+1, sum-arr[index], arr, dp)) return true;
        if(solveMemoizedIsSubsetSum(index+1, sum, arr, dp)) return true;
        dp[index][sum] = -1;
        return false;
    }   

    public static boolean memoizedIsSubsetSum(int sum, int[] arr){
        int[][] dp = new int[arr.length][sum+1];
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < sum; j++){
                dp[i][j] = 0;
            }
        }
        return solveMemoizedIsSubsetSum(0, sum, arr, dp);
    }

    // recursive (TC->O(2^n), SC->O(n))
    public static boolean solveRecursiveIsSubsetSum1(int index, int target, int[] nums){
        if(target == 0) return true;
        if(index == 0){ 
            if(nums[0] == target) return true;
            return false;
        }

        boolean notPick = solveRecursiveIsSubsetSum1(index-1, target, nums);
        boolean pick = false;
        if(target >= nums[index])
            pick = solveRecursiveIsSubsetSum1(index-1, target-nums[index], nums);

        return notPick || pick;
    }

    public static boolean recursiveIsSubsetSum1(int target, int[] nums){
        return solveRecursiveIsSubsetSum1(nums.length-1, target, nums);
    }

    // memoized (TC->O(n . target), SC->O(n . target + n))
    public static boolean solveMemoizedIsSubsetSum1(int index, int target, int[] nums, int[][] dp){
        if(target == 0){
            dp[index][target] = 1;
            return true;
        }
        if(index == 0){
            if(target == nums[index]){
                dp[index][target] = 1;
                return true;
            }
            dp[index][target] = 0;
            return false;
        }

        if(dp[index][target] != -1){
            if(dp[index][target]==1) return true;
            return false;
        }

        boolean notPick = solveMemoizedIsSubsetSum1(index-1, target, nums, dp);
        boolean pick = false;
        if(target >= nums[index])
            pick = solveMemoizedIsSubsetSum1(index-1, target-nums[index], nums, dp);

        if(pick || notPick) {
            dp[index][target] = 1;
            return true;
        }

        dp[index][target] = 0;
        return false;
    }

    public static boolean memoizedIsSubsetSum1(int target, int[] nums){
        int[][] dp = new int[nums.length][target+1];
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j <= target; j++){
                dp[i][j] = -1;
            }
        }
        return solveMemoizedIsSubsetSum1(nums.length-1, target, nums, dp);
    }

    // tabulated soln ( TC->O(n*target), SC->O(n*target) )
    public static boolean tabulatedIsSubsetSum(int target, int[] nums){
        boolean[][] dp = new boolean[nums.length][target+1];

        for(int i = 0; i < nums.length; i++)
            dp[i][0] = true;
        
        if(nums[0] >= target)
            dp[0][nums[0]] = true;

        for(int index = 1; index < nums.length; index++){
            for(int k = 1; k <= target; k++){
                boolean notPick = dp[index-1][k];
                boolean pick = false;
                if(k >= nums[index]) pick = dp[index-1][k-nums[index]];

                dp[index][k] = pick ||  notPick;
            }
        }
        
        return dp[nums.length-1][target];
    }

    // tabulated soln ( TC->O(n*target), SC->O(target) )
    public static boolean spaceOptimizedTabulatedIsSubsetSum(int sum, int[] nums){
        boolean[] dp = new boolean[sum+1];

        dp[0] = true;
        if(nums[0] <= sum) dp[nums[0]] = true;

        for(int index = 1; index < nums.length; index++){
            boolean[] temp = new boolean[sum+1];
            temp[0] = true;
            for(int target = 1; target <= sum; target++){
                boolean notPick = dp[target];
                boolean pick = false;
                if(target >= nums[index])
                    pick = dp[target - nums[index]];
                
                temp[target] = pick || notPick;
            }
            dp = temp;
        }
        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        int sum = 9;
        int[] arr = {3, 34, 4, 12, 5, 2};

        System.out.println(recursiveIsSubsetSum(sum, arr));
        System.out.println(memoizedIsSubsetSum(sum, arr));

        System.out.println(recursiveIsSubsetSum1(sum, arr));
        System.out.println(memoizedIsSubsetSum1(sum, arr));
        System.out.println(tabulatedIsSubsetSum(sum, arr));
        System.out.println(spaceOptimizedTabulatedIsSubsetSum(sum, arr));
    }

}
