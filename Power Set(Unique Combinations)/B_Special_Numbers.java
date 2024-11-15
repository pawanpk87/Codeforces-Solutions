import java.util.*;
import java.io.*;

public class B_Special_Numbers {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int t = fs.nextInt();

        while (t-- > 0) {
            long n = fs.nextLong();
            long k = fs.nextLong();

            long kthSpecialNumber = 0;
            long currentPower = 1;

            while (k > 0) {
                if ((k & 1) == 1) {
                    kthSpecialNumber = (kthSpecialNumber + currentPower) % MOD;
                }
                currentPower = (currentPower * n) % MOD;
                k = k >> 1;
            }

            fs.writer().write(kthSpecialNumber + "\n");
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
