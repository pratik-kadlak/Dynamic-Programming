public class longestIncreasingSubsequence43 {

    // Most Optimal Soln: TC -> O(n log n), SC -> O(n)
    public static int lowerBound(int num, int[] lis){
        int low = 0, high = lis.length-1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if(lis[mid] < num) low = mid+1;
            else high = mid-1;
        }
        return low;
    }

    public static int lengthOfLIS(int[] nums){
        int n = nums.length;
        int[] lis = new int[n];
        for(int i = 0; i < n; i++)
            lis[i] = Integer.MAX_VALUE;

        for(int index = 0; index < n; index++){
            int lb = lowerBound(nums[index], lis);
            lis[lb] = nums[index];
        }

        int index = 0;
        while(index < n && lis[index] != Integer.MAX_VALUE){
            index++;
        }
        return index;
    }
    
    public static void main(String[] args) {
        int[] nums = {3, 10, 2, 1, 20};

        System.out.println(lengthOfLIS(nums));
    }

}
