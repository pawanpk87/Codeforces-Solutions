import java.io.*;
import java.util.*;

/**
 * B_Two_Buttons
 */
public class B_Two_Buttons {
    public static long solve(int num, int targetNum) {
        int time = 0;
        while (num != targetNum) {
            if (targetNum > num) {
                if (targetNum % 2 == 0) {
                    targetNum = targetNum / 2;
                } else {
                    targetNum++;
                }
            } else {
                targetNum++;
            }
            time++;
        }
        return time;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        /**
         * red = 2 blue = -1
         * 
         * num = 4 targetNum = 6
         * 
         * BFS:
         * / \
         * 8 3
         * / \
         * 6 2
         */

        int num = fs.nextInt();
        int targetNum = fs.nextInt();

        long ans = solve(num, targetNum);

        fs.writer().write(ans + "\n");

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