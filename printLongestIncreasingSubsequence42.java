import java.util.Arrays;

public class printLongestIncreasingSubsequence42 {
    
    // Optimimal Tabulated Soln: TC -> O(n^2), SC -> O(n)
    public static int optimalTabulatedLengthOfLIS(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];
        for(int i = 0; i < n; i++)
            dp[i] = 1;

        int lis = 1;
        for(int index = 1; index < nums.length; index++){
            for(int prevIndex = 0; prevIndex < index; prevIndex++){
                if(nums[prevIndex] < nums[index])
                    dp[index] = Math.max(dp[index], dp[prevIndex]+1);
            }
            lis = Math.max(lis, dp[index]);
        }
        return lis;
    }

    public static void printLIS(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];
        int[] hash = new int[n]; // for storing parents 
        
        for(int i = 0; i < n; i++){
            dp[i] = 1;
            hash[i] = i;
        }

        int lis = 1;
        int lastIndex = 0;
        for(int index = 0; index < n; index++){
            for(int prevIndex = 0; prevIndex < index; prevIndex++){
                if(nums[prevIndex] < nums[index]){
                    if(dp[prevIndex] + 1 > dp[index]){
                        dp[index] = dp[prevIndex] + 1;
                        hash[index] = prevIndex;
                    }
                }
            }
            if(dp[index] > lis){
                lis = dp[index];
                lastIndex = index;
            }
        }

        int[] LIS = new int[lis];
        int index = lastIndex;
        while(hash[index] != index){
            LIS[lis-1] = nums[index];
            index = hash[index];
            lis--;
        } 
        LIS[lis-1] = nums[index];
        System.out.println(Arrays.toString(LIS));

    }
 
    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};

        System.out.println(optimalTabulatedLengthOfLIS(nums));
        printLIS(nums);
    }


}
