import java.util.*;
import java.io.*;

public class A_Party {

    public static int depth(int node, List<List<Integer>> adjList) {
        // if (adjList.get(node).size() == 0) {
        //     return 0;
        // }

        int maxDepth = 0;

        for (int adjNode : adjList.get(node)) {
            maxDepth = Math.max(maxDepth, depth(adjNode, adjList));
        }

        return maxDepth + 1;
    }

    public static int solve(int[] manager, int n) {
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int u = 0; u < n; u++) {
            int v = manager[u];
            if (v != -1) {
                adjList.get(v).add(u);
            }
        }

        int maxDepth = 0;
        for (int i = 0; i < n; i++) {
            if (manager[i] == -1) {
                maxDepth = Math.max(maxDepth, depth(i, adjList));
            }
        }

        return maxDepth;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();

        int[] manager = new int[n];

        for (int i = 0; i < n; i++) {
            int mng = fs.nextInt() - 1;
            mng = mng < 0 ? -1 : mng;
            manager[i] = mng;
        }

        fs.writer().write(solve(manager, n) + "\n");

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
