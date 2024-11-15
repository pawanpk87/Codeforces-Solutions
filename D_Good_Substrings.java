import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * D_Good_Substrings
 */
public class D_Good_Substrings {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        String badChar = br.readLine();
        int k = Integer.parseInt(br.readLine());

        int n = input.length();

        long mod = Long.MAX_VALUE;
        int badCount = 0;
        long prime = 1000000009;
        long primePower = 1;
        long hashValue = 0;

        Set<Long> st = new HashSet<>();

        for (int i = 0; i < n; i++) {
            badCount = 0;
            prime = 1000000009;
            primePower = 1;
            hashValue = 0;

            for (int j = i; j < n; j++) {
                badCount += badChar.charAt(input.charAt(j) - 'a') == '1' ? 0 : 1;

                if (badCount > k) {
                    break;
                }

                hashValue = (hashValue * primePower + (input.charAt(j) - 'a' + 1)) % mod;
                if(hashValue < 0) {
                    hashValue  = (hashValue + mod) % mod;
                }

                st.add(hashValue);

                primePower = primePower * prime;
            }
        }

        bw.write(st.size()+"\n");

        bw.flush();

        br.close();
        bw.close();
    }
}