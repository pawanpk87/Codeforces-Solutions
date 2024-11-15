import java.io.*;
import java.util.*;

public class A_Chewbaaca_and_Number {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        StringBuilder num = new StringBuilder(fs.nexString());
        
        long minNum = Long.parseLong(num.toString());

        for (int i = 0; i < num.length(); i++) {
            char prevChar = num.charAt(i);
            int newNum = 9 - (prevChar - '0');
            if((i == 0 && newNum > 0) || (i != 0 && newNum >= 0)) {
                num.setCharAt(i, (char) (newNum + '0'));
                if(minNum > Long.parseLong(num.toString())) {
                    minNum = Math.min(minNum, Long.parseLong(num.toString()));
                } else {
                    num.setCharAt(i, prevChar);
                }
            }
        }

        fs.writer().write(minNum + "\n");

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
