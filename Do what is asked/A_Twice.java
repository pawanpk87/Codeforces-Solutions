import java.util.*;
import java.io.*;

public class A_Twice {

    public static int solve(int[] arr, int n) {
        Map<Integer, Integer> mp = new HashMap<>();

        for(int num : arr) {
            mp.put(num, mp.getOrDefault(num, 0 ) + 1);
        }

        int count = 0;
        
        for(int val : mp.values()) {
            count += (val/2);
        }

        return count;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
  
        int k = fs.nextInt();
        
        while (k-- > 0) {
            int n = fs.nextInt();
            int[] arr = fs.readIntArray(n);

            fs.writer().write(solve(arr, n) + "\n");
        }

        fs.close();
    }

    public static int MOD = 1000000007;

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