package LCA;
import java.util.*;
import java.io.*;

public class D_Cycle_Free_Flow {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        int m = fs.nextInt();

        // ============= Binary Lifting & LCA Start ================
        Node[] nodes = new Node[n];

        for(int i = 0; i < n; i++) {
            nodes[i] = new Node();
        }

        int u, v, wt;
        for (int i = 0; i < m; i++) {
            u = fs.nextInt() - 1;
            v = fs.nextInt() - 1;
            wt = fs.nextInt();

            nodes[u].adjList.add(nodes[v]);
            nodes[u].weights.add(wt);

            nodes[v].adjList.add(nodes[u]);
            nodes[v].weights.add(wt);
        }

        nodes[0].dfs(-1, null, 0);

        /*  
            Binary Lifting

            We store the 2^0-th ancestor (1-step up, or the immediate parent),

            The 2^1-th ancestor (2 steps up),

            The 2^2-th ancestor (4 steps up),


            so why  i < 20 
            ans: It is enough for 10^5 nodes 
        */
        for (int i = 1; i < 20; i++) {
            for(Node node : nodes) {
                // check wheter binary lifted value for 2^(power - 1)  is present or not
                if(node.binaryLiftedNode[i - 1] != null) { 
                    // 2 ^ i lifted node 
                    node.binaryLiftedNode[i] = node.binaryLiftedNode[i - 1].binaryLiftedNode[i - 1];
                    
                    // 2 ^ 1 node lifted value 
                    node.binaryLiftedNodeValue[i] = Math.min(
                        node.binaryLiftedNodeValue[i - 1], 
                        node.binaryLiftedNode[i - 1].binaryLiftedNodeValue[i - 1]
                    );
                }
            }
        }

        int q = fs.nextInt();
        while (q-- > 0) {
            Node nodeU = nodes[fs.nextInt() - 1];
            Node nodeV = nodes[fs.nextInt() - 1];

            /**

                Okay now we have binary lifted nodes & values now what

                how will we find the greates weight between two nodes ?

                So whenever we have tree then "There exists unique path between two nodes".

                That means we can break the path between two nodes a and b into two segments:

                    From a to their LCA
                    From b to their LCA


                once we have LCA 

                    we can calculate minimum weight from nodeA to nodeLCA
                    then calculate minimum weight from nodeB to node LCA
            */
            
            Node lca = nodeU.lca(nodeV, 19);
            
            int min = Math.min(
                nodeU.getMinimumWeightFromNodeUToDDepth(nodeU.depth - lca.depth),
                nodeV.getMinimumWeightFromNodeUToDDepth(nodeV.depth - lca.depth)
            );

            fs.writer().write(min + "\n");
        }

        fs.close();
    }

    static class Node {
        int node;
        int depth;

        List<Node> adjList = new ArrayList<>();
        List<Integer> weights = new ArrayList<>();

        // binary lifted values
        Node[] binaryLiftedNode = new Node[20];
        int[] binaryLiftedNodeValue = new int[20];

        // ============================================ DFS ========================================
        void dfs(int binaryLiftedValue, Node parentNode, int depth) {
            this.depth = depth;

            binaryLiftedNode[0] = parentNode;
            binaryLiftedNodeValue[0] = binaryLiftedValue;

            for (int i = 0; i < this.adjList.size(); i++) {
                if (this.adjList.get(i) == parentNode) {
                    continue;
                }

                adjList.get(i).dfs(this.weights.get(i), this, depth + 1);
            }
        }

        // ============================================ LCA ========================================
        Node lca(Node node, int nJumps) {
            if(this == node) {
                return this;
            }

            if(this.depth != node.depth) {
                if(this.depth > node.depth) {
                    return goUp(this.depth - node.depth).lca(node, 19);
                } else {
                    return lca(node.goUp(node.depth - this.depth), 19);
                }
            }

            if(this.binaryLiftedNode[0] == node.binaryLiftedNode[0]) {
                return this.binaryLiftedNode[0];
            }

            /* 
                2^i, 2^i+1, 2^i+2 .....

                cause after the lca both node will have the same binaryLiftedNode value

                            1
                            |
                            2
                            |
                            3
                            |
                            4
                          /  \
                         9    6
                               \
                                7


                     LCA of 9 & 7 is 4

                    node 9's binaryLiftedNode[2^2] = 1
                    node 7's binaryLiftedNode[2^2] = 1
            */

            while (this.binaryLiftedNode[nJumps] == node.binaryLiftedNode[nJumps]) {
                nJumps--;
            }

            return this.binaryLiftedNode[nJumps].lca(node.binaryLiftedNode[nJumps], nJumps);
        }

        // ============================================ Go Up ========================================
        Node goUp(int steps) {
            if(steps == 0) {
                return this;
            }

            int largestPower = 1;
            while ((2 * largestPower) <= steps) {
                largestPower = 2 * largestPower;
            }

            /*
                The largestPower found represents the largest power of 2 
                jump we can take without exceeding 'steps'.

                If largestPower = 8, for example, then we can jump 
                to an ancestor that is 8 steps above the current node.
            */

            // Determine the exponent (i.e., log base 2) of 'largestPower'.
            // This tells us which precomputed ancestor to access in the binary lifting table.
            int twoToPower = (int) (Math.log(largestPower) / Math.log(2));

            // Get the ancestor node that is 2^twoToPower steps above the current node.
            Node largestReachableNode = binaryLiftedNode[twoToPower];

            // Recursively continue moving up to complete the remaining steps.
            return largestReachableNode.goUp(steps - largestPower);
        }

        // ============================================ Go Up Min ========================================
        int getMinimumWeightFromNodeUToDDepth(int depth) {
            if(depth == 0) {
                return Integer.MAX_VALUE;
            }

            int largestPower = 1;
            while ((2 * largestPower) <= depth) {
                largestPower = 2 * largestPower;
            }

            int twoToPower = (int) (Math.log(largestPower) / Math.log(2));
            
            return Math.min(
                binaryLiftedNodeValue[twoToPower],
                binaryLiftedNode[twoToPower].getMinimumWeightFromNodeUToDDepth(depth - largestPower)
            );
        }
    }    

    static class GreatestWeightOnPath_TLE {
        public static int greatestWeight = Integer.MAX_VALUE;

        public static void greatestWeightOnPath(int u, int parent, int greatestWeightSoFar, int targetNode,
                List<List<int[]>> adjList) {

            if (u == targetNode) {
                greatestWeight = Math.min(greatestWeight, greatestWeightSoFar);
                return;
            }

            int v, wt;
            for (int[] adjNode : adjList.get(u)) {
                v = adjNode[0];
                wt = adjNode[1];

                if (v == parent) {
                    continue;
                }

                greatestWeightOnPath(v, u, Math.min(greatestWeightSoFar, wt), targetNode, adjList);
            }
        }

        public static int greatestWeightOnPath(int src, int target, List<List<int[]>> adjList) {
            greatestWeight = Integer.MAX_VALUE;
            greatestWeightOnPath(src, -1, Integer.MAX_VALUE, target, adjList);
            return greatestWeight;
        }
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
