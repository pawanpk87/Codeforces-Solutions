import java.util.*;
import java.io.*;

public class B_Startup {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int t = fs.nextInt();

        while (t-- > 0) {
            int n = fs.nextInt();
            int k = fs.nextInt();

            
            Map<Integer, Integer> mp = new HashMap<>();
            int b, c;
            for (int i = 0; i < k; i++) {
                b = fs.nextInt();
                c = fs.nextInt();
                mp.put(b, mp.getOrDefault(b, 0) + c);
            }

            int tempK = mp.size();
            int[] cost = new int[tempK];
            int index = 0;
            for(int bc : mp.values()) {
                cost[index] = bc;
                index++;
            }
 
            Arrays.sort(cost);

            long sum = 0;

            int max = Math.min(tempK, n);
            for (int i = 0; i < max; i++) {
                sum += cost[tempK - i - 1];
            }

            fs.writer().write(sum + "\n");
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
