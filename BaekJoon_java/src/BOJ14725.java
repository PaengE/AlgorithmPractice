import java.io.*;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *  No.14725: 개미굴
 *  URL: https://www.acmicpc.net/problem/14725
 *  Hint: Trie 자료구조
 */

public class BOJ14725 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Trie trie = new Trie();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 노드 추가
        for (int i = 0; i < n; i++) {
            int m = sc.nextInt();
            String[] words = new String[m];
            for (int j = 0; j < m; j++) {
                words[j] = sc.next();
            }
            trie.insert(words);
        }

        TrieNode curNode = trie.rootNode;
        for (String key : curNode.child.keySet()) {
            sb.append(key + "\n");
            print(curNode.child.get(key), 2);
        }

        bw.write(sb.toString());
        bw.close();
    }

    static class TrieNode{
        TreeMap<String, TrieNode> child = new TreeMap<>();
    }

    static class Trie{
        TrieNode rootNode;
        Trie() {
            rootNode = new TrieNode();
        }

        void insert(String[] words) {
            TrieNode thisNode = rootNode;
            for (String word : words) {
                thisNode = thisNode.child.computeIfAbsent(word, c -> new TrieNode());
            }
        }
    }

    static void print(TrieNode curNode, int depth) {
        for (String key : curNode.child.keySet()) {
            for (int i = 1; i < depth; i++) {
                sb.append("--");
            }
            sb.append(key + "\n");
            print(curNode.child.get(key), depth + 1);
        }
    }
}
