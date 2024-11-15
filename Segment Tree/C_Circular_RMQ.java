import java.util.*;
import java.io.*;

/**
 * C_Circular_RMQ
 */
public class C_Circular_RMQ {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();

        int[] arr = fs.readIntArray(n);

        int m = fs.nextInt();

        SegmentTreeLazyPropagation st = new SegmentTreeLazyPropagation(arr);

        String[] query = null;
        int l, r, v;
        while (m-- > 0) {
            query = fs.readLine().split(" ");
            l = Integer.parseInt(query[0]);
            r = Integer.parseInt(query[1]);

            if (query.length == 3) {
                v = Integer.parseInt(query[2]);

                if (l > r) {
                    st.increment(0, r, v);
                    st.increment(l, n - 1, v);
                } else {
                    st.increment(l, r, v);
                }
            } else {
                long minNum = Long.MAX_VALUE;

                if (l > r) {
                    minNum = Math.min(st.query(0, r), st.query(l, n - 1));
                } else {
                    minNum = Math.min(minNum, st.query(l, r));
                }

                fs.writer().write(minNum + "\n");
            }
        }

        fs.close();
    }

    static class SegmentTreeLazyPropagation {
        private long[] tree;
        private long[] lazy;
        private int n;

        public SegmentTreeLazyPropagation(int[] arr) {
            this.n = arr.length;

            this.tree = new long[(4 * n)];
            this.lazy = new long[(4 * n)];

            build(1, 0, n - 1, arr);
        }

        private void build(int v, int tl, int tr, int[] arr) {
            if (tl == tr) {
                tree[v] = arr[tl];
            } else {
                int tm = (tl + tr) / 2;

                build(2 * v, tl, tm, arr);
                build(2 * v + 1, tm + 1, tr, arr);

                tree[v] = Math.min(tree[(2 * v)], tree[(2 * v + 1)]);
            }
        }

        private void applyPendingUpdates(int v, int tl, int tr) {
            if (lazy[v] != 0) {
                tree[v] = tree[v] + lazy[v];

                if (tl != tr) {
                    lazy[(2 * v)] = lazy[(2 * v)] + lazy[v];
                    lazy[(2 * v + 1)] = lazy[(2 * v + 1)] + lazy[v];
                }

                lazy[v] = 0;
            }
        }

        private void increment(int v, int tl, int tr, int l, int r, int val) {
            applyPendingUpdates(v, tl, tr);

            if (l > r) {
                return;
            }

            if (tl >= l && tr <= r) {
                lazy[v] = lazy[v] + val;
                applyPendingUpdates(v, tl, tr);
            } else {
                int tm = (tl + tr) / 2;

                increment(2 * v, tl, tm, l, Math.min(r, tm), val);
                increment(2 * v + 1, tm + 1, tr, Math.max(tm + 1, l), r, val);

                tree[v] = Math.min(tree[(2 * v)], tree[(2 * v + 1)]);
            }
        }

        private long query(int v, int tl, int tr, int ql, int qr) {
            applyPendingUpdates(v, tl, tr);

            if (ql > qr) {
                return Integer.MAX_VALUE;
            }

            if (tl >= ql && tr <= qr) {
                return tree[v];
            } else {
                int tm = (tl + tr) / 2;

                long leftMin = query(2 * v, tl, tm, ql, Math.min(qr, tm));
                long righMin = query(2 * v + 1, tm + 1, tr, Math.max(ql, tm + 1), qr);

                return Math.min(leftMin, righMin);
            }
        }

        private void increment(int l, int r, int val) {
            increment(1, 0, n - 1, l, r, val);
        }

        private long query(int ql, int qr) {
            return query(1, 0, n - 1, ql, qr);
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

        String readLine() {
            String input = null;
            try {
                input = br.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return input;
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
};