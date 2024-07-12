import java.util.Arrays;

public class targetSum21 {

    // Tabulated Soln: TC -> O(n*sum) SC -> O(n*sum)
    public static int tabulatedTargetSum(int target, int[] nums){
        Arrays.sort(nums);
        int low = 0, high = nums.length-1;
        while(low < high){
            int temp = nums[low];
            nums[low++] = nums[high];
            nums[high--] = temp;
        }

        int sum = 0;
        for(int it: nums) sum += it;

        int n = nums.length;
        int[][] dp = new int[n][sum+1];

        for(int i = 0; i < n; i++){
            dp[i][0] = 1;
        }
           
        if(nums[0] <= sum) dp[0][nums[0]]++;

        for(int index = 1; index < n; index++){
            for(int trg = 0; trg <= sum; trg++){
                int notPick = dp[index-1][trg];
                int pick = 0;
                if(trg >= nums[index])
                    pick = dp[index-1][trg-nums[index]];

                dp[index][trg] = pick + notPick;
            }
        }
        int toFind = (sum-target)/2;
        if((sum-target)%2==1 || (sum-target) < 0) return 0;
        if(toFind > sum) return 0;
        return dp[n-1][toFind];
    }

    public static void main(String[] args) {
        int target = 3;
        int[] nums = {1,1,1,1,1};

        System.out.println(tabulatedTargetSum(target, nums));

    }
    
}
