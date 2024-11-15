import java.io.*;
import java.util.*;
/**
 * F_Xor_Paths
 */
public class F_Xor_Paths {
    static final int N = 20;
    
    static Map<Long, Integer>[][] mp = new HashMap[N][N];

    public static long[][] grid;
    public static long halfPathLen;
    public static int n, m;
    public static long k;
    public static long ans = 0;

    public static void cal1(int i, int j, long currXorVal, int pathLen) {
        currXorVal = currXorVal ^ grid[i][j];

        if (pathLen == halfPathLen) {
            mp[i][j].put(currXorVal, mp[i][j].getOrDefault(currXorVal, 0) + 1);
            return;
        }

        if (i + 1 < n) {
            cal1(i + 1, j, currXorVal, pathLen + 1);
        }

        if (j + 1 < m) {
            cal1(i, j + 1, currXorVal, pathLen + 1);
        }
    }

    public static void cal2(int i, int j, long currXorVal, int pathLen) {
        if (pathLen == (n + m - 2) - halfPathLen) {
            if (mp[i][j].containsKey(k ^ currXorVal)) {
                ans += mp[i][j].get(k ^ currXorVal);
            }
            return;
        }

        currXorVal = currXorVal ^ grid[i][j];

        if (i > 0) {
            cal2(i - 1, j, currXorVal, pathLen + 1);
        }

        if (j > 0) {
            cal2(i, j - 1, currXorVal, pathLen + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        k = Long.parseLong(input[2]);

        grid = new long[n][m];

        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                grid[i][j] = Long.parseLong(input[j]);

                mp[i][j] = new HashMap<>();
            }
        }

        halfPathLen = ((n + m) - 2) / 2;

        cal1(0, 0, 0, 0);
        cal2(n - 1, m - 1, 0, 0);

        bw.write(ans + "\n");

        bw.flush();

        br.close();
        bw.close();
    }
}