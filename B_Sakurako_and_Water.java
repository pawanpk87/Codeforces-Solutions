import java.io.*;
import java.util.*;


/**
 * B_Sakurako_and_Water
 */
public class B_Sakurako_and_Water {
    
    public static int[][] arr;

    public static long maxSum1() {
        int n = arr.length;

        long ans = 0;

        for(int i = 0; i < n; i++) {
            int minNum = Integer.MAX_VALUE;
            for(int r = 0, c = i; c < n; r++, c++) {
                minNum = Math.min(minNum, arr[r][c]);
            }
            if(minNum < 0) {
                ans = ans + (-1 * minNum);
            }
        }

        for(int i = 1; i < n; i++) {
            int minNum = Integer.MAX_VALUE;
            for(int r = i, c = 0; r < n; r++, c++) {
                minNum = Math.min(minNum, arr[r][c]);
            }
            if(minNum < 0) {
                ans = ans + (-1 * minNum);
            }
        }

        return ans;
    }

    public static long maxSum2() {
        int n = arr.length;

        long ans = 0;

        for(int i = 0; i < n; i++) {
            int minNum = Integer.MAX_VALUE;
            for(int r = 0, c = i; c >= 0; r++, c--) {
                minNum = Math.min(minNum, arr[r][c]);
            }
            if(minNum < 0) {
                ans = ans + (-1 * minNum);
            }
        }

        for(int i = 1; i < n; i++) {
            int minNum = Integer.MAX_VALUE;
            for(int r = i, c = n-1; r < n; r++, c--) {
                minNum = Math.min(minNum, arr[r][c]);
            }
            if(minNum < 0) {
                ans = ans + (-1 * minNum);
            }
        }

        return ans;
    }

    public static long solve() {
        long ans1 = maxSum1();
        //long ans2 = maxSum2();
        return ans1;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int t = fs.nextInt();
       
        while(t-- > 0) {
            int n = fs.nextInt();

            arr = new int[n][n];

            for(int i = 0; i < n; i++) {
                int[] tempArr = fs.readIntArray(n);
                arr[i] = tempArr;
            }

            long ans = solve();
            
            fs.writer().write(ans + "\n");
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