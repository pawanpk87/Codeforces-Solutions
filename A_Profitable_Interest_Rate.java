import java.util.*;
import java.io.*;

/**
 * A_Profitable_Interest_Rate
 */
public class A_Profitable_Interest_Rate {

    public static int MOD = 1000000007;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int t = fs.nextInt();

        while (t-- > 0) {
            int a = fs.nextInt();
            int b = fs.nextInt();

            int ans = 0;

            if(a < b) {
                int diff = b - a;
                int newA = a - diff;
                int newB = b - 2*diff;
                if(newA > 0 && newB <= newA) {
                    ans = newA;
                }
            } else {
                ans = a;
            }

            fs.writer().write(ans+"\n");
        }

        fs.close();
    }

    static class Power {

        private static long mutlti(long num1, long num2) {
            num1 = num1 % MOD;
            num2 = num2 % MOD;
            return (num1 * num2) % MOD;
        }

        public static long power(long a, long b) {
            if (b == 0) {
                return 1;
            }

            if (b % 2 != 0) {
                return mutlti(a, power(a, b - 1));
            }

            return power(mutlti(a, a), b / 2);
        }
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