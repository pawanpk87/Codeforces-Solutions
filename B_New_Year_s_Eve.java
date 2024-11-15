import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B_New_Year_s_Eve {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");

        /**
         * input: n = 4 k = 3
         * 
         * 0 0 0 1 -> 1 <-
         * 0 0 1 0 -> 2
         * 0 0 1 1 -> 3 <-
         * 0 1 0 0 -> 4
         * 0 1 0 1 -> 5
         * 0 1 1 0 -> 6
         * 0 1 1 1 -> 7 <-
         * 
         * 
         */

        long n = Long.parseLong(input[0]);
        long k = Long.parseLong(input[1]);

        long ans = 0;

        if (k == 1) {
            ans = n;
        } else {
            while (ans < n) {
                ans = 2 * ans + 1;
            }
        }

        bw.write(ans + "\n");

        bw.close();
        br.close();
    }
}