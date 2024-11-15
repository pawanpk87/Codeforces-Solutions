import java.util.*;
import java.io.*;

class Digit_Sum {

    public static long[][][] digitDP;

    public static void resetDigitDP() {
        int n = 19;
        int ext = 170;
        int tight = 2;

        digitDP = new long[n][ext][tight];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < ext; j++) {
                for (int l = 0; l < tight; l++) {
                    digitDP[i][j][l] = -1;
                }
            }
        }
    }

    public static long solve(int pos, int sum, int tight, int[] digits, int n) {
        if (pos == n) {
            return sum;
        }

        if (digitDP[pos][sum][tight] != -1) {
            return digitDP[pos][sum][tight];
        }

        long currSum = 0;

        int limit = tight == 1 ? digits[pos] : 9;

        for (int digit = 0; digit <= limit; digit++) {
            int newTight = tight;

            if (digit < limit) {
                newTight = 0;
            }

            currSum = (currSum + solve(pos + 1, sum + digit, newTight, digits, n)) % MOD;
        }

        return digitDP[pos][sum][tight] = currSum;
    }

    public static long solveDigitSum(long num) {
        int[] digits = MyUtils.toNumIntArr(String.valueOf(num));

        resetDigitDP();
        return solve(0, 0, 1, digits, digits.length);
    }

    public static long solve(long L, long R) {
        if(L > 0) {
            return solveDigitSum(R) - solveDigitSum(L - 1);
        } else {
            return solveDigitSum(R);
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int t = fs.nextInt();

        while (t-- > 0) {
            long L = fs.nextLong();
            long R = fs.nextLong();

            fs.writer().write(solve(L, R) + "\n");
        }

        fs.close();
    }

    public static int MOD = 1000000007;

    static class MyUtils {

        public static int[] toNumIntArr(String str) {
            int n = str.length();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = str.charAt(i) - '0';
            }
            return nums;
        }

        public static long power10(int exp) {
            long result = 1;
            while (exp-- > 0) {
                result = (result * 10) % MOD;
            }
            return result;
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