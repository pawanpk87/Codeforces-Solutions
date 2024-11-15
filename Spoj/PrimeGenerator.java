import java.io.*;
import java.util.*;

public class PrimeGenerator {
    public static List<Integer> primes = new ArrayList<>();

    public static void sieveAlgo(int limit) {
        boolean[] isPrimeNumber = new boolean[limit + 1];

        Arrays.fill(isPrimeNumber, true);
        
        isPrimeNumber[0] = isPrimeNumber[1] = false;

        for(int i = 2; i*i <= limit; i++) {
            if(isPrimeNumber[i]) {
                primes.add(i);
                for(int j = i * i; j <= limit; j+= i) {
                    isPrimeNumber[j] = false;
                }
            }
        }
    }

    public static void segmentedSieve(int n, int m, BufferedWriter bw) throws Exception {
        boolean[] isPrimeSegment = new boolean[m - n + 1];
        Arrays.fill(isPrimeSegment, true);

        for(int prime : primes) {
            int start = Math.max(prime * prime, ((n + prime - 1) / prime) * prime);

            if(start > m) continue;

            for(int j = start; j <= m; j += prime) {
                isPrimeSegment[j - n] = false;
            }
        }

        if(n == 1) {
            isPrimeSegment[0] = false;
        }

        for(int i = n; i <= m; i++) {
            if(isPrimeSegment[i - n]) {
                bw.write(i+"\n");
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        int limit = (int) Math.sqrt(1000000000);
        sieveAlgo(limit);

        while(t-- > 0) {
            String[] input = br.readLine().split(" ");

            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);
            
            segmentedSieve(n, m, bw);

            if(t > 0) {
                bw.write("\n");
            }
        }

        bw.flush();

        bw.close();
        br.close();
    }
}