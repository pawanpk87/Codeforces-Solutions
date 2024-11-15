import java.util.*;
import java.io.*;

public class D_I_Love_1543 {

    public static int count1543(String str) {
        int n = str.length();

        int count = 0;

        for (int i = 0; i < n; i++) {
            int num1 = str.charAt(i);
            int num5 = str.charAt((i + 1) % n);
            int num4 = str.charAt((i + 2) % n);
            int num3 = str.charAt((i + 3) % n);
            if (num1 == '1' && num5 == '5' && num4 == '4' && num3 == '3') {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            int m = fs.nextInt();

            String[] matrix = new String[n];

            for (int i = 0; i < n; i++) {
                matrix[i] = fs.nexString();
            }

            long count = 0;

            int lColLimit = 0, rColLimit = m - 1;
            int uRowLimit = 0, dRowLimit = n - 1;

            while (lColLimit <= rColLimit && uRowLimit <= dRowLimit) {
                StringBuilder res = new StringBuilder();

                for (int j = lColLimit; j <= rColLimit; j++) {
                    res.append(matrix[uRowLimit].charAt(j));
                }

                uRowLimit++;

                for (int i = uRowLimit; i <= dRowLimit; i++) {
                    res.append(matrix[i].charAt(rColLimit));
                }
                rColLimit--;

                if (uRowLimit <= dRowLimit) {
                    for (int j = rColLimit; j >= lColLimit; j--) {
                        res.append(matrix[dRowLimit].charAt(j));
                    }
                    dRowLimit--;
                }

                if (lColLimit <= rColLimit) {
                    for (int i = dRowLimit; i >= uRowLimit; i--) {
                        res.append(matrix[i].charAt(lColLimit));
                    }
                    lColLimit++;
                }

                count += count1543(res.toString());
            }

            fs.writer().write(count + "\n");
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
