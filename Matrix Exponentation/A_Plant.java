import java.util.*;
import java.io.*;

/**
 * A_Plant
 */
public class A_Plant {

    public static int MOD = 1000000007;

    private static void multiply(long[][] A, long[][] B) {
        long[][] C = new long[2][2];
        
        for(int i = 0; i < 2; i++) {
            Arrays.fill(C[i], 0);
        }

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                for(int k = 0; k < 2; k++) {
                    C[i][k] = (C[i][k] + A[i][j] * B[j][k]) % MOD;
                }
            }
        }

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                A[i][j] = C[i][j];
            }
        }
    }

    private static long[][] binaryExponentation(long[][] M, long n) {
        long[][] res = new long[2][2];

        /*
         
         When performing matrix exponentiation, we aim to multiply the matrix 
         by itself a certain number of times. Just like multiplying numbers, 
         where multiplying by 1 leaves the number unchanged, matrices have an 
         equivalent concept known as the identity matrix.
        
         */

        for(int i = 0; i < 2; i++) {
            res[i][i] = 1;
        }

        // res[0][0] = 1;
        // res[0][1] = 0;
        // res[1][0] = 0;
        // res[1][1] = 1;

        while (n > 0) {
            if((n & 1) != 0) {
                multiply(res, M);
            }

            multiply(M, M);

            n = n >> 1;
        }

        return res;
    }

    private static long solve(long n) {
        /*
            Simple Recursive Approach:

                private static long countUpwordTraingles(long n) {
                    if(n == 0) {
                        return 1;
                    }

                    long prevUpwordTraingles = countUpwordTraingles(n - 1);
                    long prevDownwordTraingles = countDownwordTraingles(n - 1);

                    return (3 * prevUpwordTraingles + prevDownwordTraingles) % MOD;
                }

                private static long countDownwordTraingles(long n) {
                    if(n == 0) {
                        return 0;
                    }

                    long prevUpwordTraingles = countUpwordTraingles(n - 1);
                    long prevDownwordTraingles = countDownwordTraingles(n - 1);

                    return (prevUpwordTraingles + 3 * prevDownwordTraingles) % MOD;
                }

                long ans = countUpwordTraingles(n)

        */

        long[][] M = new long[2][2];

        M[0][0] = 3;
        M[0][1] = 1;
        M[1][0] = 1;
        M[1][1] = 3;

        long[][] res = binaryExponentation(M, n);

        return res[0][0];
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        long n = fs.nextLong();

        fs.writer().write(solve(n) + "\n");

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