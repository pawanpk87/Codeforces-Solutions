import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * A_Circumference_of_a_Tree
 */
public class A_Circumference_of_a_Tree {
     public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();

        Node[] nodes = new Node[n];

        for(int i = 0; i < n; i++){
            nodes[i] = new Node();
        }

        int u,v;
        for(int i = 1; i < n; i++) {
            u = fs.nextInt() - 1;
            v = fs.nextInt() - 1;

            nodes[u].list.add(nodes[v]);
            nodes[v].list.add(nodes[u]);
        }

        bfs(nodes[0], nodes);

        Node farthestNode = findFarthestNode(nodes);

        bfs(farthestNode, nodes);

        fs.writer().write((3 * findFarthestNode(nodes).dist) + "\n");

        fs.close();
    }

    public static Node findFarthestNode(Node[] nodes) {
        Node fatrthesNode = nodes[0];
        for(int i = 0; i < nodes.length; i++) {
            if(fatrthesNode.dist < nodes[i].dist) {
                fatrthesNode = nodes[i];
            }
        }
        return fatrthesNode;
    }

    public static void bfs(Node startingNode, Node[] nodes) {
        for(int i = 0; i < nodes.length; i++) {
            nodes[i].dist = -1;
        }

        startingNode.dist = 0;

        ArrayDeque<Node> bfs = new ArrayDeque<>();

        bfs.add(startingNode);

        while(!bfs.isEmpty()) {
            Node currNode = bfs.remove();
            for (Node adjNode : currNode.list) {
                if(adjNode.dist == -1) {
                    adjNode.dist = currNode.dist + 1;
                    bfs.add(adjNode);
                }
            }
        }
    }

    static class  Node {
        int dist;
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