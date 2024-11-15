import java.io.*;
import java.util.*;

/**
 * C_Little_Alawn_s_Puzzle
 */
public class C_Little_Alawn_s_Puzzle {
    public static int MOD = 1000000007;

    public static List<List<Integer>> adjList = null;

    private static void dfs(int currNode, boolean[] visited) {
        visited[currNode] = true;
        for (int adjNode : adjList.get(currNode)) {
            if (visited[adjNode] == false) {
                dfs(adjNode, visited);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int t = fs.nextInt();

        while (t-- > 0) {
            adjList = new ArrayList<>();

            int n = fs.nextInt();

            int[] a = fs.readIntArray(n);
            int[] b = fs.readIntArray(n);

            for (int i = 0; i <= n; i++) {
                adjList.add(new ArrayList<>());
            }

            int u, v;
            for (int i = 0; i < n; i++) {
                u = a[i];
                v = b[i];
                adjList.get(u).add(v);
                adjList.get(v).add(u);
            }

            boolean[] visited = new boolean[n + 1];
            Arrays.fill(visited, false);

            long ans = 1;
            for (int i = 1; i <= n; i++) {
                if (visited[i] == false) {
                    ans = (ans * 2) % MOD;
                    dfs(i, visited);
                }
            }

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