import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * E_Maximum_Subsequence
 */
public class E_Maximum_Subsequence {
    public static int[] subsets(int[] nums, int mod) {
        int n = nums.length;
        
        int totalSubsets = (int) Math.pow(2, n);
        int[] allSubsets = new int[totalSubsets];

        for(int k = 0; k < totalSubsets; k++) {
            int sum = 0;
            
            for(int i = 0; i < n; i++) {
                if(((1 << i) & k) > 0) { 
                    sum = (sum + nums[i]) % mod;
                }
            }

            allSubsets[k] = sum;
        }

        return allSubsets;
    }

    public static int solve(int[] nums, int k) {
        int n = nums.length;

        int[] subsets1 = subsets(Arrays.copyOfRange(nums, 0, (n + 1)/2), k);
        int[] subsets2 = subsets(Arrays.copyOfRange(nums, (n + 1)/2, n), k);

        Arrays.sort(subsets2);

        int maxValue = Integer.MIN_VALUE;

        for(int a : subsets1) {
            int b = k - 1 - a;
            
            int index = Arrays.binarySearch(subsets2, b);

            index = index < 0 ? -(index + 1) : index;
            
            if(index < subsets2.length) {
                maxValue = Math.max(maxValue, (a + subsets2[index]) % k);
            }

            if(index > 0) {
                maxValue = Math.max(maxValue, (a + subsets2[index - 1]) % k);
            }
        }

        return maxValue;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        /*

n = 4   k = 4
5 2 4 1


        {5}             -> 5%4 = 1
        {5, 2}          -> 7%4 = 3
        {5, 2, 4}       -> 11%4 = 3
        {5, 2, 4, 1}    -> 12%4 = 0
        {2}             -> 2%4 = 2
        {2, 4}          -> 6%4 = 2
        {2, 4, 1}       -> 7%4 = 3
        {4}             -> 4%5 = 0
        {4, 1}          -> 5%4 = 1


        ans = {5, 2} -> 3



Solutions: Meet In the Middle

        5 2 4 1

        {5, 2}  -> subsets sums -> 0, 1, 2, 3

        {4, 1}  -> subsets sums -> 0, 0, 1, 1


        0, 1, 2, 3
                 |


         x1,x2,x3....xn + y1,y2.y3.....yn
            |                   |       
            a                   b

            a+b % mod = rem (it should be as big as possible)


            a = 3
            b = ?
            k = 4

            (a + b) % k = rem 

            k-1 - a

        */

        String[] input = br.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        int[] nums = new int[n];

        input = br.readLine().split(" ");

        for(int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }

        bw.write(solve(nums, k) + "\n");

        bw.flush();

        br.close();
        bw.close();
    }
}