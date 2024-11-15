import java.io.*;
import java.util.*;

/**
 * C_Sakurako_s_Field_Trip
 */
public class C_Sakurako_s_Field_Trip {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int t = fs.nextInt();

        while (t-- > 0) {
            int n = fs.nextInt();

            /**
                For each position i
                        No swap (dp[i][0]), where a[i] and its mirror a[n - i - 1] stay in place.
                        A swap (dp[i][1]), where a[i] and a[n - i - 1] are swapped.


                
                        Option 1 (dp[i - 1][0] + ...):

                            This option assumes no swap at the previous position i - 1 either, 
                            so we continue not swapping at both i - 1 and i.

                            dp[i - 1][0]: This is the minimum disturbance achieved without swapping up to position i - 1.

                            (a[i] == a[i - 1]): Checks if a[i] and a[i - 1] are the same, which would cause a disturbance.
                            
                            (a[n - i - 1] == a[n - i]): Checks if the symmetric elements a[n - i - 1] and a[n - i] are the same, which would also add to the disturbance.
                                |
                                let's say we did't swap at i-1 position so we have to check that the symemetric elements were the same or not
                        
                        Option 2 (dp[i - 1][1] + ...):

                            This option assumes a swap was done at the previous position i - 1, so we need to account for the swapped elements from the previous position.
                            
                            dp[i - 1][1]: This is the minimum disturbance achieved by swapping up to position i - 1.
                            
                            (a[i] == a[n - i]): Checks if a[i] matches with a[n - i] (the symmetric position of i if a swap had been done), potentially causing a disturbance.
                            
                            (a[n - i - 1] == a[i - 1]): Checks if a[n - i - 1] (the symmetric position of i - 1) is the same as a[i - 1].



             */

            int[] arr = fs.readIntArray(n);

            int len = (n + 1)/2;

            int[][]dp = new int[len][2];

            for(int i = 1; i < len; i++) {
                dp[i][0] = Math.min(
                    dp[i - 1][0] + (arr[i] == arr[i - 1] ? 1 : 0) + (arr[n - i] == arr[n - i - 1] ? 1 : 0),
                    dp[i - 1][1] + (arr[i] == arr[n - i] ? 1 : 0) + (arr[i - 1] == arr[n - i - 1] ? 1 : 0)
                );

                dp[i][1] = Math.min(
                    dp[i - 1][1] + (arr[i] == arr[i - 1] ? 1 : 0) + (arr[n - i] == arr[n - i - 1] ? 1 : 0),
                    dp[i - 1][0] + (arr[i] == arr[n - i] ? 1 : 0) + (arr[i - 1] == arr[n - i - 1] ? 1 : 0)
                );
            }

            int result = Math.min(dp[len - 1][0], dp[len - 1][1]);

            if(n%2 == 0) {
                result += (arr[n/2 - 1] == arr[n/2] ? 1 : 0);
            }

            fs.writer().write(result + "\n");
        }

        fs.close();
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer stk = new StringTokenizer("");

        String next() {
            while (!stk.hasMoreTokens()) {
                try {
                    stk = new StringTokenizer(br.readLine());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return stk.nextToken();
        }

        BufferedWriter writer() {
            return bw;
        }

        String nexString() {
            return next();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        int[] readIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        long[] readLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextLong();
            }
            return a;
        }

        void close() {
            try {
                bw.flush();

                bw.close();
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}