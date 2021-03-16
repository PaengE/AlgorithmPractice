import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *  No.14425: 문자열집합
 *  URL: https://www.acmicpc.net/problem/14425
 *  Hint: Set or Trie, but 시간초과..
 */
public class BOJ14425 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Trie trie = new Trie();

        String[] in = new String[n];

        for (int i = 0; i < n; i++) {
            in[i] = br.readLine();
        }
        trie.insert(in);

        int ans = 0;
        for (int i = 0; i < m; i++) {
            if (trie.find(br.readLine())) {
                ans++;
            }
        }

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    static class TrieNode{
        HashMap<Character, TrieNode> child = new HashMap<>();
        boolean lastChar;

        boolean isLastChar() {
            return lastChar;
        }

        void setLastChar() {
            this.lastChar = true;
        }
    }

    static class Trie{
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        void insert(String[] s) {
            for (String str : s) {
                TrieNode thisNode = root;
                for (Character c : str.toCharArray()) {
                    thisNode = thisNode.child.computeIfAbsent(c, tn -> new TrieNode());
                }
                thisNode.setLastChar();
            }
        }

        boolean find(String s) {
            TrieNode thisNode = root;
            for (Character c : s.toCharArray()) {
                if (thisNode.child.get(c) != null) {
                    thisNode = thisNode.child.get(c);
                } else {
                    return false;
                }
            }
            return thisNode.isLastChar();
        }
    }
}
