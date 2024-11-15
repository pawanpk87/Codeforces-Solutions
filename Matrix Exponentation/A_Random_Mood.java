import java.util.*;
import java.io.*;

/**
 * A_Random_Mood
 */
public class A_Random_Mood {

    public static int MOD = 1000000007;

    private static double binaryExponentation(long n, double p) {
        /**
         *  p1 = probability of flips at second 1      
         *  p2 = probability of flips at second 2 ?
         *  
         *  p2 = p1 * (1 - p) + (1 - p) * p1
         *        |         \
         *    he flip(happ)  \
         *                  he doesn't flip(sad)
         * 
         */
        

        double res = 1.0;

        while (n > 0) {
            if ((n & 1) != 0) {
                res = res * (1 - p) + (1 - res) * p;
            }
            p = p * (1 - p) + (1 - p) * p;
            n = n >> 1;
        }

        return res;
    }

    private static void multiply(double[][] A, double[][] B) {
        double[][] C = new double[2][2];
        
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                for(int k = 0; k < 2; k++) {
                    C[i][k] += A[i][j] * B[j][k];
                }
            }
        }

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                A[i][j] = C[i][j];
            }
        }
    }


    private static double[][] exponentatinPower(double[][] M, int k) {
        double[][] res = new double[2][2];
        // set diagonal element to 1
        res[0][0] = 1.0;
        res[0][1] = 0.0;
        res[1][0] = 0.0;
        res[1][1] = 1.0;

        while (k > 0) {
            if((k & 1) != 0) {
                multiply(res, M);
            }
            multiply(M, M);
            k = k >> 1;
        }

        return res;
    }

    private static double matrixExponentation(long n, double p) {
        /**
         *  p1 = probability of flips at second 1      
         *  p2 = probability of flips at second 2 ?
         *  
         *  p2 = p1 * (1 - p) + (1 - p) * p1
         *        |         \
         *    he flip(happ)  \
         *                  he doesn't flip(sad)
         * 
         */

        double[][] M = new double[2][2];

        M[0][0] = 1 - p; // happy to happy
        M[0][1] = p; // happy to sad
        M[1][0] = p;
        M[1][1] = 1 -p;


        double[][] res = exponentatinPower(M, (int) n);

        return res[0][0];


        // double[] dp = new double[] {1.0, 0.0};
        // //                  happy = 1 , sad = 0

        // for(int i = 0; i < n; i++) {
        //     double newHappy = dp[0] * (1 - p)   +   dp[1] * p;
        //                     //      |
        //                  //   happy(flip) * not happy(not flip)

        //     double newSad = dp[0] * p + dp[1] * (1 - p);
            
        //     dp = new double[] {newHappy, newSad};
        // }


        // return dp[0];
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        double p = fs.nextDouble();

        // Using Binary Exponentation
        // fs.writer().write(String.format("%.10f", binaryExponentation(n, p)) + "\n");

        // Using Matrix Exponentation
        fs.writer().write(String.format("%.10f", matrixExponentation(n, p)) + "\n");

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