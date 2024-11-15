import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * A_Ichihime_and_Triangle
 */
public class A_Ichihime_and_Triangle {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        String[] input = null;

        while (t-- > 0) {
            //br.readLine();
            input = br.readLine().split(" ");

            bw.write(input[1] + " " + input[2] + " " + input[2] + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}