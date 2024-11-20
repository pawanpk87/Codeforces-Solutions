import java.util.*;
import java.io.*;

public class C_Journey {

    public static double answer = 0.0;

    public static void dfs(int depth, double probability, int currNode, int parentNode, List<List<Integer>> adjList) {

        int children = 0;
        for (int adjNode : adjList.get(currNode)) {
            if (adjNode != parentNode) {
                children++;
            }
        }

        for (int adjNode : adjList.get(currNode)) {
            if (adjNode != parentNode) {
                dfs(depth + 1, probability / children, adjNode, currNode, adjList);
            }
        }

        if (children == 0) {
            answer += probability * depth;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();

        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        int u, v;
        for (int i = 1; i < n; i++) {
            u = fs.nextInt();
            v = fs.nextInt();

            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        answer = 0;

        dfs(0, 1.0, 1, -1, adjList);

        fs.writer().write(String.format("%.15f", answer) + "\n");

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

        String readLine() {
            try {
                return br.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
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