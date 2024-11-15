import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


/**c
 * B_Binomial_Coefficients_Kind_Of
 */
public class B_Binomial_Coefficients_Kind_Of {

    public static int MOD = 1000000007;

    public static int[][] C;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int t = fs.nextInt();

        int[] ks = new int[t];
        
        for(int i = 0; i < t; i++) {
            fs.nextInt(); 
        }

        for(int i = 0; i < t; i++) {
            ks[i] = fs.nextInt();
        }
        
        for(int i = 0; i < t; i++) {
            long ans = ((long) Math.pow(2, ks[i])) %  MOD;
            fs.writer().write(ans + "\n");
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