import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * A_Bit
 */
public class A_Bit {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String input = null;

        int num = 0;
        while (n-- > 0) {
            //br.readLine();
            input = br.readLine();

            if(input.charAt(0) == '+' || input.charAt(1) == '+' || input.charAt(2) == '+') {
                num++;
            } else {
                num--;
            }
        }
            
        bw.write(String.valueOf(num));

        br.close();
        bw.flush();
        bw.close();
    }
}