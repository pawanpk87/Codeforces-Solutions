import java.util.*;
import java.io.*;

/**
 * C_Classy_Numbers
 */
public class C_Classy_Numbers {
    static long[][][] digitDP;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int t = fs.nextInt();
        while (t-- > 0) {
            long a = fs.nextLong();
            long b = fs.nextLong();

            fs.writer().write(countInRange(a, b) + "\n");
        }

        fs.close();
    }

    public static long countInRange(long a, long b) {
        restDigitDP();
        long res1 = countClassyNums(b);

        restDigitDP();
        long res2 = countClassyNums(a - 1);

        return res1 - res2;
    }

    public static long countClassyNums(long num) {
        int[] digits = getDigits(num);
        return solve(0, 0, 0, digits);
    }

    public static long solve(int pos, int count, int f, int[] digits) {
        if (pos == 20) {
            return count <= 3 ? 1 : 0;
        }

        if (digitDP[pos][count][f] != -1) {
            return digitDP[pos][count][f];
        }

        long res = 0;
        int limit = (f == 0) ? digits[pos] : 9;

        for (int digit = 0; digit <= limit; digit++) {
            int nF = (digit < limit) ? 1 : f;
            int nCount = count + (digit > 0 ? 1 : 0);

            if (nCount <= 3) {
                res += solve(pos + 1, nCount, nF, digits);
            }
        }

        return digitDP[pos][count][f] = res;
    }

    public static void restDigitDP() {
        digitDP = new long[20][4][2];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 4; j++) {
                Arrays.fill(digitDP[i][j], -1);
            }
        }
    }

    public static int[] getDigits(long num) {
        int[] digits = new int[20];
        Arrays.fill(digits, 0);
        int index = 19;
        while (num != 0) {
            int d = (int) (num % 10);
            digits[index] = d;
            index--;
            num /= 10;
        }
        return digits;
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