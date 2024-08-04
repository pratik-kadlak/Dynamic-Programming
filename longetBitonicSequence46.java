public class longetBitonicSequence46 {

    public static int longestBitonicSequence(int n, int[] nums) {
        // code here
        int[] lis = new int[n];
        int[] lds = new int[n];
        
        for(int i = 0; i < n; i++){
            lis[i] = 1;
            lds[i] = 1;
        }
        
        for(int index = 1; index < n; index++){
            for(int prevIndex = 0; prevIndex < index; prevIndex++){
                if(nums[prevIndex] < nums[index])
                    lis[index] = Math.max(lis[index], lis[prevIndex]+1);
            }
        }
        
        for(int index = n-2; index >= 0; index--){
            for(int prevIndex = n-1; prevIndex > index; prevIndex--){
                if(nums[prevIndex] < nums[index])
                    lds[index] = Math.max(lds[index], lds[prevIndex]+1);
            }
        }
        
        int lbsLen = 0;
        for(int i = 0; i < n; i++){
            if(lis[i] == 1 || lds[i] == 1) continue;
            lbsLen = Math.max(lbsLen, lis[i]+lds[i]-1);
        }

        return lbsLen;
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 11, 2, 10, 4, 5, 2, 1};
        System.out.println(longestBitonicSequence(nums.length, nums));
    }

}
