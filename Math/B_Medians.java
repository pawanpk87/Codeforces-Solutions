package Math;
import java.util.*;
import java.io.*;

public class B_Medians {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int t = fs.nextInt();

        while (t-- > 0) {
            int n = fs.nextInt();
            int k = fs.nextInt();

            int l = k, r = k;

            boolean ans = false;

            if (n > 1) {
                while (l > 0 && r < n) {
                    int leftLen = l - 1;
                    int rightLen = n - r;
                    if (leftLen % 2 == 0 || rightLen % 2 == 0) {
                        l--;
                        r++;
                    } else {
                        ans = true;
                        fs.writer().write("3\n");
                        fs.writer().write("1 " + l + " " + (r + 1) + "\n");
                        break;
                    }
                }
            } else if (k == 1) {
                ans = true;
                fs.writer().write("1\n");
                fs.writer().write("1\n");
            }

            if (ans == false) {
                fs.writer().write("-1\n");
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
