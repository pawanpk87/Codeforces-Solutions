import java.io.*;
import java.util.*;

/**
 * D_Vasiliy_s_Multiset
 */
public class D_Vasiliy_s_Multiset {
    public static int MOD = 1000000007;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int q = fs.nextInt();

        Trie trie = new Trie();
        trie.insert(0);

        while (q-- > 0) {
            String currQ = fs.next();
            if (currQ.charAt(0) == '+') {
                trie.insert(fs.nextInt());
            } else if (currQ.charAt(0) == '-') {
                trie.remove(fs.nextInt());
            } else {
                int ans = trie.findMaxXor(fs.nextInt());
                fs.writer().write(ans + "\n");
            }
        }

        fs.close();
    }

    // ================== Trie Start =======================
    static class TrieNode {
        TrieNode[] children = new TrieNode[2];
        int count = 0;
    }

    static class Trie {
        private TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(int num) {
            TrieNode currNode = root;
            for (int i = 31; i >= 0; i--) {
                int currBit = (num >> i) & 1;
                if (currNode.children[currBit] == null) {
                    currNode.children[currBit] = new TrieNode();
                }
                currNode = currNode.children[currBit];
                currNode.count++;
            }
        }

        public void remove(int num) {
            TrieNode currNode = root;
            TrieNode[] path = new TrieNode[32];
            int[] bits = new int[32];

            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                bits[i] = bit;
                path[i] = currNode;
                currNode = currNode.children[bit];
                if (currNode == null) {
                    return;
                }
            }

            for (int i = 31; i >= 0; i--) {
                int bit = bits[i];
                TrieNode parent = path[i];
                parent.children[bit].count--;
                if (parent.children[bit].count == 0) {
                    parent.children[bit] = null;
                }
            }
        }

        public int findMaxXor(int num) {
            if (isEmpty()) {
                return num;
            }
        
            TrieNode currNode = root;
            int maxXor = 0;
        
            for (int i = 31; i >= 0; i--) {
                int currBit = (num >> i) & 1;
                int oppositeBit = currBit == 0 ? 1 : 0;
        
                if(currNode.children[oppositeBit] != null) {
                    maxXor = maxXor | (1 << i);
                    currNode = currNode.children[oppositeBit];
                } else {
                    currNode = currNode.children[currBit];
                }
        
                if (currNode == null) {
                    return num;
                }
            }
        
            return maxXor;
        }

        private boolean isEmpty() {
            return root.children[0] == null && root.children[1] == null;
        }
    }
    // ================== Trie End =======================

    static class Power {

        private static long mutlti(long num1, long num2) {
            num1 = num1 % MOD;
            num2 = num2 % MOD;
            return (num1 * num2) % MOD;
        }

        public static long power(long a, long b) {
            if (b == 0) {
                return 1;
            }

            if (b % 2 != 0) {
                return mutlti(a, power(a, b - 1));
            }

            return power(mutlti(a, a), b / 2);
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