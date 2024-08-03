import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class largestDivisibleSubset44 {

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);

        int[] dp = new int[nums.length];
        int[] hash = new int[nums.length];

        for(int i = 0; i < nums.length; i++){
            dp[i] = 1;
            hash[i] = i;
        }

        dp[0] = 1;

        for(int index = 1; index < nums.length; index++){
            for(int prevIndex = 0; prevIndex < index; prevIndex++){
                if(nums[index] % nums[prevIndex] == 0){
                    if(dp[prevIndex] + 1 > dp[index]){
                        dp[index] = dp[prevIndex] + 1;
                        hash[index] = prevIndex;
                    }
                }
            }
        }

        int index = 0;
        for(int i = 1; i < dp.length; i++){
            if(dp[i] > dp[index])
                index = i;
        }

        List<Integer> ans = new ArrayList<>();

        while(hash[index] != index){
            ans.add(nums[index]);
            index = hash[index];
        }
        ans.add(nums[index]);
        return ans;
    }



    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7,8};
        System.out.println(largestDivisibleSubset(nums));

    }   
}
