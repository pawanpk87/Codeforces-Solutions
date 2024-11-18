import java.util.*;
import java.io.*;

public class C_Superultra_s_Favorite_Permutation {

    public static String solve(int n) {
        if (n < 5) {
            return null;
        }

        StringBuilder res = new StringBuilder();

        for (int num = 2; num <= n; num += 2) {
            if (num != 4) {
                res.append(num + " ");
            }
        }

        res.append(4 + " " + 5 + " ");

        for (int num = 1; num <= n; num += 2) {
            if (num != 5) {
                res.append(num + " ");
            }
        }

        return res.toString().trim();
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int t = fs.nextInt();

        while (t-- > 0) {
            int n = fs.nextInt();

            String ans = solve(n);

            if (ans == null) {
                fs.writer().write("-1\n");
            } else {
                fs.writer().write(ans + "\n");
            }
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