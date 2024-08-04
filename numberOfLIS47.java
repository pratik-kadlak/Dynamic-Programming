public class numberOfLIS47 {

    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] freq = new int[n];
        for(int i = 0; i < n; i++){
            dp[i] = 1;
            freq[i] = 1;
        }
        int lisLen = 1;

        for(int index = 1; index < n; index++){
            for(int prevIndex = 0; prevIndex < index; prevIndex++){
                if(nums[prevIndex] < nums[index]){
                    if(dp[prevIndex]+1 > dp[index]){
                        dp[index] = dp[prevIndex] + 1;
                        freq[index] = freq[prevIndex];
                    } else if(dp[prevIndex] + 1 == dp[index]){
                        freq[index] += freq[prevIndex];
                    }
                }
            }
            lisLen = Math.max(lisLen, dp[index]);
        }

        int cnt = 0;
        for(int i = 0; i < n; i++)
            if(dp[i] == lisLen) cnt += freq[i];

        return cnt;
    }
    
    public static void main(String[] args) {
        int[] nums = {1,2,4,3,5,4,7,2};
        System.out.println(findNumberOfLIS(nums));
    }

}
