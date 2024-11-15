import java.util.*;
import java.io.*;

public class A_Ice_Skating {

    public static void dfs(int currNode, int[][] coordinates, boolean[] visited, int n) {
        visited[currNode] = true;

        int currNodeX = coordinates[currNode][0];
        int currNodeY = coordinates[currNode][1];

        int adjNodeX,  adjNodeY; 
        for(int i = 1; i <= n; i++) {
            adjNodeX = coordinates[i][0];
            adjNodeY = coordinates[i][1];

            if(visited[i] == false && 
               (currNodeX == adjNodeX || currNodeY == adjNodeY)) {
                dfs(i, coordinates, visited, n);
            }
        }
    }

    public static int solve(int[][] coordinates, int n) {
        /*
                ex1:
                    2
                    x = 2 y = 1
                    x = 1 y = 2


                                      3 |
                                        |
                                      2 |  *(1, 2)
                                        |
                                      1 |  ans  *(2, 1)
                                        |
                    ------------------------------------------------
                                        |  1    2    3
                                        |
                                        |
                                        |
                                        |


                ex2:-

                    2
                    x = 2 y = 1
                    x = 4 y = 1
                    

                                      3 |
                                        |
                                      2 |               
                                        |
                                      1 |       *(2, 1)    *(4, 1)
                                        |
                    ------------------------------------------------
                                        |  1    2    3     4
                                        |
                                        |
                                        |
                                        |



                1.) making sure that Bajtek can travel between all snow drifts on a 
                    grid using only horizontal (east-west) and vertical (north-south) moves.

                

                Approach:

                    adList = {
                        1 -> {x, y}
                        2 -> {x, y}
                        3 -> {x, y}
                        .
                        .
                        .
                    }
        */

        boolean[] visited = new boolean[n + 1];
        
        int connectedComp = 0;

        for(int i = 1; i <= n; i++) {
            if(visited[i] == false) {
                dfs(i, coordinates, visited, n);
                connectedComp++;
            }
        }

        return connectedComp - 1;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
  
        int n = fs.nextInt();

        int[][] coordinates = new int[n + 1][2];

        int x, y;
        for(int i = 1; i <= n; i++) {
            x = fs.nextInt();
            y = fs.nextInt();
            coordinates[i] = new int[]{x, y};    
        }

        fs.writer().write(solve(coordinates, n) + "\n");

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
