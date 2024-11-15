import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MatrixExponentation {
    public static int MOD = 1000000007;

    private void multiply(long[][] A, long[][] B) {
        long[][] C = new long[2][2];

        C[0][0] = (A[0][0] * B[0][0] + A[0][1] * B[1][0]) % MOD;
        C[0][1] = (A[0][0] * B[0][1] + A[0][1] * B[1][1]) % MOD;
        C[1][0] = (A[1][0] * B[0][0] + A[1][1] * B[1][0]) % MOD;
        C[1][1] = (A[1][0] * B[0][1] + A[1][1] * B[1][1]) % MOD;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                A[i][j] = C[i][j];
            }
        }
    }

    private long[][] power(long[][] M, int expo) {
        long[][] ans = { { 1, 0 }, { 0, 1 } };

        while (expo > 0) {
            if ((expo & 1) != 0) {
                multiply(ans, M);
            }
            multiply(M, M);
            expo = expo >> 1;
        }

        return ans;
    }

    public long nthFibonacci(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        long[][] M = { { 1, 1 }, { 1, 0 } };
        long[][] F = { { 1, 0 }, { 0, 0 } };

        long[][] res = power(M, n - 1);

        multiply(res, F);

        return res[0][0] % MOD;
    }

    public static void main(String[] args) {
        MatrixExponentation matrixExponentation = new MatrixExponentation();
        System.out.println(matrixExponentation.nthFibonacci(3));
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
