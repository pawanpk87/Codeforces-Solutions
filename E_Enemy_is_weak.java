import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


/**
 * E_Enemy_is_weak
 */
public class E_Enemy_is_weak {

    public static int MOD = 1000000007;

    public static void main(String[] args) throws Exception {
        /**

            count of triplets i < j < k  & arr[i] > arr[j] > arr[k]

            ex:
                3 2 1

                    3 > 2 > 1

                
                10 8 3 1
                    10 > 8 > 3
                    10 > 8 > 1
                    10 > 3 > 1
                    8  > 3 > 1


            Segment tree:

                        0  1 2 3
                        10 8 3 1
                        |
                            sort it
                        
                        0   1   2   3
                        1   3   8   10
                        0   0   1   1
                        |
                        .
                        .
                        .
                        ans = 1 * 2 + 2 * 1 
                        ans = 4

         */

        FastScanner fs = new FastScanner();

        int n = fs.nextInt();

        SegmentTree st = new SegmentTree(n);

        int[] arr = fs.readIntArray(n);
        
        int[] sortedArr = Arrays.copyOfRange(arr, 0, n);
        Arrays.sort(sortedArr);
        Map<Integer, Integer> index = new HashMap<>();
        for(int i = 0; i < n; i++) {
            index.put(sortedArr[i], i);
        }

        long count = 0;

        for(int i = 0; i < n; i++) {
            long numberOfGreaterElementOnLeft = st.query(index.get(arr[i]) + 1, n-1);

            long numberOfSmallerElementOnLeft = i - numberOfGreaterElementOnLeft;

            long numberOfSmallerElementOnRight = index.get(arr[i]) - numberOfSmallerElementOnLeft;
            
            count += (long) (numberOfGreaterElementOnLeft * numberOfSmallerElementOnRight);

            st.update(index.get(arr[i]), 1);
        }

        fs.writer().write(count + "\n");
        
        fs.close();
    }

    static class SegmentTree {
        int[] tree;
        int n;
    
        public SegmentTree(int n) {
            this.n = n;
            this.tree = new int[(4 * n)];
        }
    
        public void update(int index, int value) {
            update(1, 0, n-1, index, value);
        }
    
        public long query(int l, int r) {
            return query(1, 0, n-1, l, r);
        }
    
        private long query(int v, int tl, int tr, int l, int r) {
            if(tr < l || tl > r) {
                return 0;
            }
    
            if(tl >= l && tr <= r) {
                return tree[v];
            }
    
            int tmid = (tl + tr)/2;
    
            long left = query(2*v, tl, tmid, l, r);
            long right = query(2*v + 1, tmid + 1, tr, l, r);
    
            return left + right;
        }
    
        private void update(int v, int tl, int tr, int index, int value) {
            if(tl == tr) {
                tree[v] += value;
                return;
            }
    
            int tmid = (tl + tr)/2;
    
            if(index <= tmid) {
                update(2*v, tl, tmid, index, value);
            } else {
                update(2*v + 1, tmid + 1, tr, index, value);
            }
    
            tree[v] = tree[2*v] + tree[2*v + 1];
        }
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