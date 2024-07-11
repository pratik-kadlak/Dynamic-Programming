import java.util.Arrays;

public class countPartitionWithDiffK18 {

    // tabulated soln (TC -> O(n*sum + n log n), SC -> O(n * sum) )
    public static int tabulatedCountPartitions(int diff, int[] nums){
        Arrays.sort(nums);
        int low = 0, high = nums.length-1;
        while(low < high){
            int temp = nums[low];
            nums[low++] = nums[high];
            nums[high--] = temp;
        }

        int sum = 0;
        for(int it: nums) sum += it;
        
        int[][] dp = new int[nums.length][sum+1];
        for(int i = 0; i < nums.length; i++)
            dp[i][0] = 1;
        if(nums[0] <= sum) dp[0][nums[0]]++;

        for(int index = 1; index < nums.length; index++){
            for(int target = 0; target <= sum; target++){
                int notPick = dp[index-1][target];
                int pick = 0;
                if(target >= nums[index]) 
                    pick = dp[index-1][target-nums[index]];

                dp[index][target] = pick + notPick;
            }
        }
        
        if((sum-diff)%2==1 || (sum-diff) < 0) return 0;
        int toFind = (sum - diff) / 2;
        return dp[nums.length-1][toFind];
    }
    
    public static void main(String[] args) {
        // int d = 0;
        // int[] nums = {0, 0, 0};

        int d = 17;
        int[] nums = {0, 1, 3, 2, 3, 0, 1, 2, 2, 3};

        System.out.println(tabulatedCountPartitions(d, nums));
    }

}
