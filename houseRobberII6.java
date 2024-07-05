public class houseRobberII6 { 

    public static int spaceOptimizedTabulatedRob2(int[] nums){
        if(nums.length == 1) return nums[0];

        int pick0 = 0, notPick0 = 0;
        int pick1 = 0, notPick1 = 0;

        for(int i = 0; i < nums.length-1; i++){
            int rob0 = notPick0 + nums[i];
            int noRob0 = Math.max(pick0, notPick0);
            
            pick0 = rob0;
            notPick0 = noRob0;

            int rob1 = notPick1 + nums[i+1];
            int noRob1 = Math.max(pick1, notPick1);

            pick1 = rob1;
            notPick1 = noRob1;
        }

        return Math.max(Math.max(pick0, notPick0), Math.max(pick1, notPick1));

    }
    
    public static void main(String[] args) {
        int[] nums = {2, 1, 1, 2};
        System.out.println(spaceOptimizedTabulatedRob2(nums));
    }

}
