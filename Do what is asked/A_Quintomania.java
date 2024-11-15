import java.util.*;
import java.io.*;

public class A_Quintomania {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        
        int t = fs.nextInt();

        while (t-- > 0) {
            int n = fs.nextInt();

            int[] arr = fs.readIntArray(n);

            boolean isPerfect = true;

            for(int i = 1; i < n; i++) {
                int diff = Math.abs(arr[i] - arr[i-1]);
                if(diff == 5 || diff == 7) {
                    
                } else {
                    isPerfect = false;
                    break;
                }
            }

            if(isPerfect) {
                fs.writer().write("YES\n");
            } else {
                fs.writer().append("NO\n");
            }
        }

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
