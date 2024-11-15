import java.util.*;
import java.io.*;

public class D_1_Magic_Powder_1 {

    public static boolean isOk(long cookie, int[] ingredients, int[] ingredientsNeed, int n, int k) {
        for (int i = 0; i < n; i++) {
            long need = cookie * ingredientsNeed[i];
            long have = ingredients[i];
            if (have < need) {
                long extraNeed = need - have;

                if (k < extraNeed) {
                    return false;
                }

                k -= extraNeed;
            }
        }
        return true;
    }

    public static long solve(int[] ingredients, int[] ingredientsNeed, int n, int k) {
        long low = 0, high = 1000000009;
        long maxCoookie = 0;

        while (low <= high) {
            long mid = (low + high) / 2;
            if (isOk(mid, ingredients, ingredientsNeed, n, k)) {
                maxCoookie = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return maxCoookie;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        int k = fs.nextInt();

        int[] ingredientsNeed = fs.readIntArray(n);
        int[] ingredients = fs.readIntArray(n);

        fs.writer().write(solve(ingredients, ingredientsNeed, n, k) + "\n");

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
