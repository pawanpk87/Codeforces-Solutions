import java.util.*;
import java.io.*;

public class E_Kachina_s_Favorite_Binary_String {

    public static long query(int l, int r, FastScanner fs) {
        System.out.println("? " + l + " " + r + "\n");
        System.out.flush();
        long ans = fs.nextInt();
        return ans;
    }

    public static void solve(int n, FastScanner fs) throws Exception {
        /*
         * 
         * If f(1,r) < f(1,r+1), then s(r+1) ​= 1 (a new '1' adds subsequences "01").
         * If f(1,r) = f(1,r+1), then s(r+1) = 0 (no new subsequences "01" are added).
         * 
         */
        long[] queryResult = new long[n + 1];

        boolean isImpossible = true;

        for (int i = 1; i < n; i++) {
            queryResult[i] = query(1, i + 1, fs);
            if (queryResult[i] > 0) {
                isImpossible = false;
            }
        }

        if (isImpossible) {
            System.out.println("! IMPOSSIBLE \n");
            System.out.flush();
            return;
        }

        int i = 0;

        while (queryResult[i] == 0) {
            i++;
        }

        char[] zeros = new char[n];
        Arrays.fill(zeros, '0');
        StringBuilder res = new StringBuilder(new String(zeros));

        for (int j = 0; j < (i - queryResult[i]); j++) {
            res.setCharAt(j, '1');
        }

        for (int j = i; j < n; j++) {
            res.setCharAt(j, queryResult[j] > queryResult[j - 1] ? '1' : '0');
        }

        System.out.println("! " + res + " \n");
        System.out.flush();
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int t = fs.nextInt();

        while (t-- > 0) {
            int n = fs.nextInt();

            solve(n, fs);
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