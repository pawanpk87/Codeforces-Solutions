import java.util.*;
import java.io.*;

public class D_Sharky_Surfing {

    public static int solve(int[][] hurdles, int[][] powerUps, int L) {
        StringBuilder res = new StringBuilder();

        /*

        Testcase 1:

            n = 2 
            m = 5 
            L = 50

            hurdles = [[7, 14], [30, 40]]

            powerUps = {
                2 -> 2
                3 -> 1, 5
                18 -> 2
                22 -> 32
            }

            Ans = 4


                she can jump 
                    curr pos x
                    curr power = k
                    
                    next pos = [x, x + k]



                x = 1
                k = 1
                    next pos = {1, 2}
                
                x = 2
                k = 1
                    get power

                x = 2
                k = 3
                    next pos = {2, 5}
                
                
                x = 3
                k = 3
                    get power

                x = 3
                k = 4
                    next pos = {3, 7}
                

                x = 3
                k = 9
                    next pos = {3, 12}
                
                but how 18 ?

                x = 3
                k = 9
                |
                x = 4
                k = 9
                |
                x = 5
                k = 9
                |
                x = 6
                k = 9


                then 
                x = 15
                k = 9

                .
                .
                .
        */
        
        int n = hurdles.length;
        int m = powerUps.length;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int powerIndex = 0;

        long powerCanHave = 1;

        for(int i = 0; i < n; i++) {
            while (powerIndex < m && powerUps[powerIndex][0] < hurdles[i][0]) {
                maxHeap.add(powerUps[powerIndex][1]);
                powerIndex++;
            }

            long powerNeedToJumpCurrentHurdle = hurdles[i][1] - hurdles[i][0] + 2;

            while (!maxHeap.isEmpty() && powerCanHave < powerNeedToJumpCurrentHurdle) {
                powerCanHave += maxHeap.poll();
            }

            if(powerCanHave < powerNeedToJumpCurrentHurdle) {
                return -1;
            }
        }

        while (powerIndex < m ) {
            maxHeap.add(powerUps[powerIndex][1]);
            powerIndex++;
        }

        return m - maxHeap.size();
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int t = fs.nextInt();

        while (t-- > 0) {
            int n = fs.nextInt();
            int m = fs.nextInt();
            int L = fs.nextInt();

            int[][] hurdles = new int[n][2];
            int l, r;
            for (int i = 0; i < n; i++) {
                l = fs.nextInt();
                r = fs.nextInt();
                hurdles[i] = new int[] { l, r };
            }

            int[][] powerUps = new int[m][2];
            int x, v;
            for (int i = 0; i < m; i++) {
                x = fs.nextInt();
                v = fs.nextInt();
                powerUps[i] = new int[]{x, v};
            }

            fs.writer().write(solve(hurdles, powerUps, L) + "\n");
        }

        fs.close();
    }

    public static int MOD = 1000000007;

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