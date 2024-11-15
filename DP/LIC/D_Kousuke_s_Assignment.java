import java.util.*;
import java.io.*;

/**
 * D_Kousuke_s_Assignment
 */
public class D_Kousuke_s_Assignment {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int t = fs.nextInt();

        while (t-- > 0) {
            int n = fs.nextInt();
            /*
                prefix[i..........n]

                p[j0] p[j1] p[j2] ........ p[i]


                    prefix[j] == prefix[i] 
            


                dp[i] = maximum number of non-overlaping subarrays


                dp[j0] dp[j1] dp[j2] ...... dp[jk] ......... dp[n]
                                                        |
                                                        i
                
                                                        
                    12  -4  4   43  -3  -5  8
                p   12   8  12  55  52  47  55  
                dp  0    0   1   1   1   1   2


                    0   -4  0   3   0   1
                p   0   -4  -4  -1  -1  0
                dp  0    0   1

 



            */

            int[] tarr = fs.readIntArray(n);

            long[] arr = new long[n + 1];
            for(int i = 1; i <= n; i++) {
                arr[i] = tarr[i - 1];
            }
            
            long[] prefixArr = new long[n + 1];
            for(int i = 1; i <= n; i++) {
                prefixArr[i] = prefixArr[i - 1] + arr[i];
            }

            Map<Long, Integer> mp = new HashMap<>();

            long[] dp = new long[n + 1];
            dp[0] = 0;

            mp.put(0L, 0);

            for(int i = 1; i <= n; i++) {
                dp[i] = dp[i - 1];
                if(mp.containsKey(prefixArr[i])) {
                    dp[i] = Math.max(dp[i], dp[mp.get(prefixArr[i])] + 1);
                }
                mp.put(prefixArr[i], i);
            }

            fs.writer().write(dp[n] + "\n");
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