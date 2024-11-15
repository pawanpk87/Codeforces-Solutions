import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * A_Bus_to_Pénjamo
 */
public class A_Bus_to_Penjamo {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int t = fs.nextInt();

        while (t-- > 0) {
            int n = fs.nextInt();
            int r = fs.nextInt();

            int[] members = new int[n];

            int sum = 0;

            for (int i = 0; i < n; i++) {
                members[i] = fs.nextInt();
                sum += members[i];
            }

            int gotSeat = 0;
            int haventGotSeat = 0;
            int rowAcquired = 0;

            for (int i = 0; i < n; i++) {
                if (members[i] % 2 == 0) {
                    gotSeat += members[i];
                    rowAcquired += members[i] / 2;
                } else {
                    gotSeat += members[i] - 1;
                    haventGotSeat++;
                    rowAcquired += (members[i] - 1) / 2;
                }
            }

            // how many row will require to assign seat for people who haven't got seat
            int rowRequired = 0;
            // how many rows are left
            int rowLeft = 0;

            if (haventGotSeat % 2 == 0) {
                rowRequired = haventGotSeat / 2;
                rowAcquired += rowRequired;
                rowLeft = r - rowAcquired;
                gotSeat += 2 * rowLeft;
            } else {
                rowRequired = (haventGotSeat - 1) / 2;
                rowRequired++;
                gotSeat++;
                rowAcquired += rowRequired;
                rowLeft = r - rowAcquired;
                gotSeat += 2 * rowLeft;
            }

            int ans = Math.min(sum, gotSeat);

            fs.writer().write(ans + "\n");
        }
        
        fs.close();
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