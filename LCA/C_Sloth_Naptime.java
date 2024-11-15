package LCA;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * C_Sloth_Naptime
 */
public class C_Sloth_Naptime {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i + 1);
        }

        int u, v;
        for (int i = 1; i < n; i++) {
            u = fs.nextInt() - 1;
            v = fs.nextInt() - 1;

            nodes[u].adjList.add(nodes[v]);
            nodes[v].adjList.add(nodes[u]);
        }

        nodes[0].dfs(null, 0);

        for (int p = 1; p < 20; p++) {
            for (Node node : nodes) {
                if (node.up[p - 1] != null) {
                    node.up[p] = node.up[p - 1].up[p - 1];
                }
            }
        }

        int q = fs.nextInt();

        int c;
        while (q-- > 0) {
            Node a = nodes[fs.nextInt() - 1];
            Node b = nodes[fs.nextInt() - 1];
            c = fs.nextInt();

            Node lca = a.lca(b, 19);

            int totalPathLength = a.depth + b.depth - 2 * lca.depth;

            if(totalPathLength <= c) {
                fs.writer().write(b.id +"\n");
                continue;
            }

            int distanceFromAToLCANode = a.depth - lca.depth;

            if(distanceFromAToLCANode >= c) {
                fs.writer().write(a.goUp(c).id + "\n");
            } else {
                int bUp = totalPathLength - c;
                fs.writer().write(b.goUp(bUp).id + "\n");
            }
        }

        fs.close();
    }

    static class Node {
        Node[] up = new Node[20];
        ArrayList<Node> adjList = new ArrayList<>();
        int depth;
        int id;

        public Node(int id) {
            this.id = id;
        }

        public void dfs(Node parentNode, int depth) {
            this.depth = depth;

            up[0] = parentNode;

            for (Node adjNode : adjList) {
                if (adjNode == parentNode) {
                    continue;
                }

                adjNode.dfs(this, depth + 1);
            }
        }

        public Node goUp(int nSteps) {
            if (nSteps == 0) {
                return this;
            }

            int largestPower = 1;
            while (largestPower * 2 <= nSteps) {
                largestPower = 2 * largestPower;
            }

            Node nextNode = up[(int) (Math.log(largestPower) / Math.log(2))];

            return nextNode.goUp(nSteps - largestPower);
        }

        public Node lca(Node b, int maxJumps) {
            if (this == b) {
                return this;
            }

            if (depth != b.depth) {
                if (depth > b.depth) {
                    return goUp(depth - b.depth).lca(b, 19);
                } else {
                    return lca(b.goUp(b.depth - depth), 19);
                }
            }

            if (up[0] == b.up[0]) {
                return up[0];
            }

            while (up[maxJumps] == b.up[maxJumps]) {
                maxJumps--;
            }

            return up[maxJumps].lca(b.up[maxJumps], maxJumps);
        }

        public String toString() {
            return String.valueOf(id);
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