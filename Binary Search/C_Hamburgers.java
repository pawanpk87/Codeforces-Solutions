import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * C_Hamburgers
 */
public class C_Hamburgers {
    public static long calculateCost(long burgers, int cb, int cs, int cc, long b, long s, long c, long pb, long ps, long pc) {
        long extraB = Math.max(0, burgers * cb - b);
        long extraS = Math.max(0, burgers * cs - s);
        long extraC = Math.max(0, burgers * cc - c);
        return extraB * pb + extraS * ps + extraC * pc;
    }

    public static long solve(String recipe, long b, long s, long c, long pb, long ps, long pc, long rubles) {
        int n = recipe.length();

        int cb = 0, cs = 0, cc = 0;
        
        for(int i = 0; i < n; i++) {
            if(recipe.charAt(i) == 'B') {
                cb++;
            } else if(recipe.charAt(i) == 'S') {
                cs++;
            } else {
                cc++;
            }
        }
        
        long low = 0, high = (long) 1e13;
        long res = 0;

        while (low <= high) {
            long mid = (low + high) / 2;
            if(calculateCost(mid, cb, cs, cc, b, s, c, pb, ps, pc) <= rubles) {
                res = Math.max(res, mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return high;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String recipe = bf.readLine();

        String[] input;

        input = bf.readLine().split(" ");

        long b = Long.parseLong(input[0]);
        long s = Long.parseLong(input[1]);
        long c = Long.parseLong(input[2]);

        input = bf.readLine().split(" ");

        long pb = Long.parseLong(input[0]);
        long ps = Long.parseLong(input[1]);
        long pc = Long.parseLong(input[2]);
        
        long rubles = Long.parseLong(bf.readLine());

        long ans = solve(recipe, b,s,c, pb, ps, pc, rubles);

        bw.write(ans+"");

        bw.flush();

        bf.close();
        bw.close();
    }
}