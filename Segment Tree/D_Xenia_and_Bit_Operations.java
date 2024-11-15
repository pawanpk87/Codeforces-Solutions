import java.util.*;
import java.io.*;

/**
 * D_Xenia_and_Bit_Operations
 */
public class D_Xenia_and_Bit_Operations {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        /*
            2 4
            
            nums: 1 6 3 5

            queries:
                        1 4
                        3 4
                        1 2
                        1 2

            initial
                    1 6 3 5
                    1|6 3|5
                        7 ^ 7
                        
            v = 0


            q = 1 4
                    4 6 3 5
                    4|6 3|5
                        6  ^ 7
            v = 1
            
            
        */

        int tn = fs.nextInt();
        int n = (int) Math.pow(2, tn);
        int m = fs.nextInt();

        int[] arr = fs.readIntArray(n);

        SegmentTree st = new SegmentTree(arr, tn);

        int i, v;
        while (m-- > 0) {
            i = fs.nextInt() - 1;
            v = fs.nextInt();

            st.update(i, v);

            fs.writer().write(st.getRootValue() + "\n");
        }

        fs.close();
    }

    static class SegmentTree {
        private int[] tree;
        private int n;
        private int tn;

        public SegmentTree(int[] arr, int tn) {
            this.tn = tn;
            this.n = arr.length;

            this.tree = new int[(4 * n)];

            build(1, tn%2 == 0 ? 1 : 0, 0, n - 1, arr);
        }

        private void build(int v, int level, int tl, int tr, int[] arr) {
            if (tl == tr) {
                tree[v] = arr[tl];
            } else {
                int tm = (tl + tr) / 2;

                build(2 * v, level + 1, tl, tm, arr);
                build(2 * v + 1, level + 1, tm + 1, tr, arr);

                if (level % 2 == 0) {
                    tree[v] = tree[2 * v] | tree[2 * v + 1];
                } else {
                    tree[v] = tree[2 * v] ^ tree[2 * v + 1];
                }
            }
        }

        private void update(int v, int level, int tl, int tr, int index, int value) {
            if (tl == tr) {
                tree[v] = value;
            } else {
                int tm = (tl + tr) / 2;

                if (index <= tm) {
                    update(2 * v, level + 1, tl, tm, index, value);
                } else {
                    update(2 * v + 1, level + 1, tm + 1, tr, index, value);
                }

                if (level % 2 == 0) {
                    tree[v] = tree[2 * v] | tree[2 * v + 1];
                } else {
                    tree[v] = tree[2 * v] ^ tree[2 * v + 1];
                }
            }
        }

        public void update(int index, int value) {
            update(1, tn%2 == 0 ? 1 : 0, 0, n - 1, index, value);
        }

        public int getRootValue() {
            return tree[1];
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