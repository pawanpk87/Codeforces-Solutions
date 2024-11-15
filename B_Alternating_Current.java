import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B_Alternating_Current {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        
        Stack<Character> st = new Stack<>();

        for(char ch : input.toCharArray()) {
            if(!st.isEmpty() && st.peek() == ch) {
                st.pop();
                continue;
            }
            st.push(ch);
        }

        if(st.isEmpty()) {
            bw.write("Yes\n");
        } else {
            bw.write("No\n");
        }

        bw.flush();
        
        bw.close();
        br.close();
    }
}