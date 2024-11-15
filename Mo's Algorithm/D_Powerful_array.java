import java.io.*;
import java.util.*;

/**
 * D_Powerful_array
 */
public class D_Powerful_array {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        int q = fs.nextInt();

        int[] arr = fs.readIntArray(n);

        List<int[]> queries = new ArrayList<>();
        int s, e;
        for (int i = 0; i < q; i++) {
            s = fs.nextInt() - 1;
            e = fs.nextInt() - 1;
            queries.add(new int[] { s, e, i});
        }

        // ===================== Mo's Algo start ====================
        Collections.sort(queries, new Comparator<int[]>() {
            public int compare(int[] q1, int[] q2) {
                int q1BlockNum = q1[0] / MyData.BLOCKS;
                int q2BlockNum = q2[0] / MyData.BLOCKS;

                if (q1BlockNum != q2BlockNum) {
                    return Integer.compare(q1[0], q2[0]);
                }

                // return Integer.compare(q1[1], q2[1]);

                /**
                    
                Mo's algorithm sorts the queries by blocks, but within each block, 
                sorting based on the end of the range (R) to minimize the distance 
                moved between consecutive queries. 
                
                The addition I made essentially orders the queries within each block 
                in a “zig-zag” pattern:

                    Even-numbered blocks (0, 2, 4, …) sort R values in ascending order.
                    Odd-numbered blocks (1, 3, 5, …) sort R values in descending order.

                    This zig-zag order reduces the amount of movement between consecutive 
                    queries, especially for large input sizes, which speeds up the algorithm.
                    
                */
                if (q1BlockNum % 2 == 0) {
                    return Integer.compare(q1[1], q2[1]);
                } else {
                    return Integer.compare(q2[1], q1[1]);
                }
            }
        });

        MyData myData = new MyData();
        myData.setArray(arr);

        long[] ans = new long[q];

        int currL = 0;
        int currR = 0;

        for (int i = 0; i < q; i++) {
            int[] querie = queries.get(i);

            int L = querie[0];
            int R = querie[1];
            int index = querie[2];

            while (currL < L) {
                myData.subtract(currL);
                currL++;
            }

            while (currL > L) {
                myData.add(currL - 1);
                currL--;
            }

            while (currR <= R) {
                myData.add(currR);
                currR++;
            }

            while (currR > R + 1) {
                myData.subtract(currR - 1);
                currR--;
            }

            ans[index] = myData.getProduct();
        }

        // ===================== Mo's Algo end ====================

        FastPrinter.printArrNewLine(ans, fs);

        fs.close();
    }

    static class MyData {
        public static int MAX_SIZE = 1000005;
        public static int BLOCKS = 500;

        int[] arr;
        long[] count;
        long product;

        public MyData() {
            arr = new int[MAX_SIZE];
            count = new long[MAX_SIZE];
            product = 0;
        }

        public void setArray(int[] arr) {
            this.arr = arr;
        }

        public void add(int index) {
            int num = arr[index];
            long prevNumCount = count[num];

            product -= (prevNumCount * prevNumCount * num);

            count[num]++;

            product += (count[num] * count[num] * num);
        }

        public void subtract(int index) {
            int num = arr[index];
            long prevNumCount = count[num];

            product -= (prevNumCount * prevNumCount * num);

            count[num]--;

            product += (count[num] * count[num] * num);
        }

        public long getProduct() {
            return product;
        }
    }

    /*
     ========================= TLE =================================

        public static void main(String[] args) throws Exception {
            FastScanner fs = new FastScanner();

            int n = fs.nextInt();
            int q = fs.nextInt();

            int[] arr = fs.readIntArray(n);

            List<int[]> queries = new ArrayList<>();
            int s, e;
            for (int i = 0; i < q; i++) {
                s = fs.nextInt() - 1;
                e = fs.nextInt() - 1;
                queries.add(new int[] { s, e, i});
            }

            // ===================== Mo's Algo start ====================
            Collections.sort(queries, new Comparator<int[]>() {
                public int compare(int[] q1, int[] q2) {
                    int q1BlockNum = q1[0] / MyData.BLOCKS;
                    int q2BlockNum = q2[0] / MyData.BLOCKS;

                    if (q1BlockNum != q2BlockNum) {
                        return Integer.compare(q1[0], q2[0]);
                    }

                    return Integer.compare(q1[1], q2[1]);
                }
            });

            MyData myData = new MyData();
            myData.setArray(arr);

            long[] ans = new long[q];

            int currL = 0;
            int currR = 0;

            for (int i = 0; i < q; i++) {
                int[] querie = queries.get(i);

                int L = querie[0];
                int R = querie[1];
                int index = querie[2];

                while (currL < L) {
                    myData.subtract(currL);
                    currL++;
                }

                while (currL > L) {
                    myData.add(currL - 1);
                    currL--;
                }

                while (currR <= R) {
                    myData.add(currR);
                    currR++;
                }

                while (currR > R + 1) {
                    myData.subtract(currR - 1);
                    currR--;
                }

                ans[index] = myData.getProduct();
            }

            // ===================== Mo's Algo end ====================

            FastPrinter.printArrNewLine(ans, fs);

            fs.close();
        }

        static class MyData {
            public static int MAX_SIZE = 1000005;
            public static int BLOCKS = 318;

            int[] arr;
            long[] count;
            long product;

            public MyData() {
                arr = new int[MAX_SIZE];
                count = new long[MAX_SIZE];
                product = 0;
            }

            public void setArray(int[] arr) {
                for (int i = 0; i < arr.length; i++) {
                    this.arr[i] = arr[i];
                }
            }

            public void add(int index) {
                int num = arr[index];
                long prevNumCount = count[num];

                product -= (prevNumCount * prevNumCount * num);

                count[num]++;

                product += (count[num] * count[num] * num);
            }

            public void subtract(int index) {
                int num = arr[index];
                long prevNumCount = count[num];

                product -= (prevNumCount * prevNumCount * num);

                count[num]--;

                product += (count[num] * count[num] * num);
            }

            public long getProduct() {
                return product;
            }
        }
        
    */

    static class FastPrinter {
        public static void printArrNewLine(int[] arr, FastScanner fs) throws Exception {
            for (int num : arr) {
                fs.writer().write(num + "\n");
            }
        }

        public static void printArrNewLine(long[] arr, FastScanner fs) throws Exception {
            for (long num : arr) {
                fs.writer().write(num + "\n");
            }
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