import java.util.*;
import java.io.*;

public class LCA {
    public static int n;
    public static int[] heigh;
    public static int[] first;
    public static int[] segtree;
    public static boolean[] visited;

    public static List<Integer> euler;
    
    public static Node[] nodes;

    public static void dfs(int currNode, int h) {
        visited[currNode] = true;
        
        first[currNode] = euler.size();
        
        heigh[currNode] = h;
        
        euler.add(currNode);

        for(Node adjNode : nodes[currNode].list) {
            if(!visited[adjNode.node]) {
                dfs(adjNode.node, h + 1);
                euler.add(currNode);
            }
        }
    }

    public static void buildSegmentTree(int node, int tl, int tr) {
        if(tl == tr) {
            segtree[node] = euler.get(tl);
        } else {
            int tm = (tl + tr)/2;

            buildSegmentTree(2*node, tl, tm);
            buildSegmentTree((2*node) + 1, tm + 1, tr);
            
            int l = segtree[2*node];
            int r = segtree[(2*node) + 1];

            segtree[node] = (heigh[l] < heigh[r]) ? l : r;
        }
    }

    public static int query(int node, int tl, int tr, int nodeL, int nodeR) {
        if(tl > nodeR || tr < nodeL) {
            return -1;
        }
        
        if(tl >= nodeL && tr <= nodeR) {
            return segtree[node];
        }

        int mid = (tl + tr) / 2;

        int left = query(2 * node, tl, mid, nodeL, nodeR);
        int right = query((2 * node) + 1, mid + 1, tr, nodeL, nodeR);

        if(left == -1) {
            return right;
        }

        if(right == -1) {
            return left;
        }

        return heigh[left] < heigh[right] ? left : right;
    }

    public static void solve() {
        heigh = new int[n + 1];
        euler = new ArrayList<>();
        first = new int[n + 1];
        visited = new boolean[n + 1];
        Arrays.fill(visited, false);
        
        // DFS + Euler path
        dfs(1, 0);

        // Segment tree construction
        int m = euler.size();
        segtree = new int[(4 * m)];
        buildSegmentTree(1, 0, m-1);
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int t = fs.nextInt();

        while (t-- > 0) {
            n = fs.nextInt();
            
            // Adj list
            nodes = new Node[n + 1];
            for(int i = 1; i <= n; i++) {
                Node newNode = new Node();
                newNode.node = i;

                nodes[i] = newNode;
            }
            for(int i = 1; i <= n; i++) {
                int c = fs.nextInt();
                while(c-- > 0) {
                    int adjNode = fs.nextInt();
                    nodes[i].list.add(nodes[adjNode]);
                }
            }

            solve();

            // Queries + Segment tree
            int q = fs.nextInt();

            fs.writer().write("Case " + (t+1) + ":\n");
            for(int i = 1; i <= q; i++){
                int nodeL = fs.nextInt();
                int nodeR = fs.nextInt();

                if(first[nodeL] > first[nodeR]) {
                    int temp = nodeL;
                    nodeL = nodeR;
                    nodeR = temp;
                }

                int lca = query(1, 0, euler.size()-1, nodeL, nodeR);

                fs.writer().write(lca+"\n");
            }
        }

        fs.close();
    }

    static class  Node {
        int node;
        ArrayList<Node> list = new ArrayList<>();
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
            for(int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        long[] readLongArray(int n) {
            long[] a = new long[n];
            for(int i = 0; i < n; i++) {
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
