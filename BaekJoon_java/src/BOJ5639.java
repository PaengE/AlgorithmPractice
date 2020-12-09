import java.io.*;

/**
 *  No.5639: 이진 검색 트리
 *  URL: https://www.acmicpc.net/step/23
 *  Description: 이진 검색 트리의 전위 순회가 주어졌을 때 후위 순회를 구하는 문제
 *  Hint: 트리를 만든 후 후위 순회
 */

public class BOJ5639 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        BinaryTree tree = new BinaryTree(Integer.parseInt(br.readLine()));

        String s = "";
        while ((s = br.readLine()) != null && s.length() != 0) {
            tree = tree.buildTree(tree, Integer.parseInt(s));
        }

        postOrder(tree);

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    // 후위 순위
    static void postOrder(BinaryTree tree) {
        if (tree.left != null) {
            postOrder(tree.left);
        }
        if (tree.right != null) {
            postOrder(tree.right);
        }
        sb.append(tree.data).append("\n");
    }

    // 이진트리
    static class BinaryTree {
        int data;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        // 이진트리 생성
        public BinaryTree buildTree(BinaryTree tree, int val) {
            BinaryTree curTree = null;

            if (tree == null) {
                return new BinaryTree(val);
            }

            if (val < tree.data) {
                curTree = buildTree(tree.left, val);
                tree.left = curTree;
            } else if (val > tree.data) {
                curTree = buildTree(tree.right, val);
                tree.right = curTree;
            }

            return tree;
        }
    }
}
