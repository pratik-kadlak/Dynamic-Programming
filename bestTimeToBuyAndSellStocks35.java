public class bestTimeToBuyAndSellStocks35 {

    // its not recursive but does all combinations 
    // TC -> O(n^2), SC -> O(1)
    public static int recursiveMaxProfit(int[] prices){
        int maxProfit = 0;
        for(int i = 0; i < prices.length-1; i++){
            int currProfit = prices[i+1] - prices[i];
            for(int j = i+1; j < prices.length; j++){
                currProfit = Math.max(currProfit, prices[j]-prices[i]);
            }
            maxProfit = Math.max(maxProfit, currProfit);
        }
        return maxProfit;
    }

    // TC -> O(n), SC -> O(1)
    public static int maxProfit(int[] prices){
        int maxProfit = 0;
        int minValue = prices[0];
        for(int i = 1; i < prices.length; i++){
            minValue = Math.min(minValue, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i]-minValue);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        // int[] prices = {7,6,4,3,1};

        System.out.println(recursiveMaxProfit(prices));

        System.out.println(maxProfit(prices));
    }
    
}