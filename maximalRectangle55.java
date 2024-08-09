import java.util.Stack;

public class maximalRectangle55 {

    public static int largestRectangularArea(int[] heights){
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        int area = 0;

        for(int i = 0; i <= heights.length; i++){
            while(!stack.isEmpty() && (i==n || heights[stack.peek()] > heights[i])){
                int h = heights[stack.pop()];
                int w = 0;
                if(stack.isEmpty()) w = i;
                else w = i - stack.peek() - 1;
                area = Math.max(area, h * w);
            }
            stack.push(i);
        }
        return area;
    }

    public static int maximalRectangle(char[][] matrix) {
        int[] heights = new int[matrix[0].length];
        int area = 0;

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == '1') heights[j]++;
                else heights[j] = 0;
            }
            area = Math.max(area, largestRectangularArea(heights));
        }
        return area;
    }
    
    public static void main(String[] args) {
        char[][] matrix = {{'1','0','1','0','0'},
                           {'1','0','1','1','1'},
                           {'1','1','1','1','1'},
                           {'1','0','0','1','0'}};

        System.out.println(maximalRectangle(matrix));
    }

}
