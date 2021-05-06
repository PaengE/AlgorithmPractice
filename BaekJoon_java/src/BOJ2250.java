import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.2250: 트리의 높이와 너비
 *  URL: https://www.acmicpc.net/problem/2250
 *  Hint: Tree + inorder
 */

public class BOJ2250 {
    static int n, maxLevel, loc = 1;    // loc: 노드 방문 시마다 +1
    static Node[] nodes;
    static int[] levelMax, levelMin;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        nodes = new Node[n + 1];
        levelMax = new int[n + 1];
        levelMin = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(-1, i, -1, -1);
            levelMax[i] = 0;
            levelMin[i] = n;
        }

        // 트리 구성
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            nodes[cur].left = left;
            nodes[cur].right = right;
            if (left != -1) {
                nodes[left].parent = cur;
            }
            if (right != -1) {
                nodes[right].parent = cur;
            }
        }

        // 루트 노드를 찾음
        int root = 0;
        for (int i = 1; i <= n; i++) {
            if (nodes[i].parent == -1) {
                root = i;
                break;
            }
        }

        // 중위 순회
        inorder(root, 1);

        int ans = 0;
        int ansIdx = 0;
        // 각 레벨 별로 너비를 구함
        for (int i = 1; i <= maxLevel; i++) {
            int diff = levelMax[i] - levelMin[i] + 1;
            if (ans < diff) {
                ans = diff;
                ansIdx = i;
            }
        }

        bw.write(ansIdx + " " + ans);
        bw.close();
        br.close();
    }

    // 중위 순회
    static void inorder(int root, int level) {
        Node cur = nodes[root];

        // 최대 깊이를 갱신
        maxLevel = Math.max(maxLevel, level);

        // 왼쪽 노드
        if (cur.left != -1) {
            inorder(cur.left, level + 1);
        }

        // 각 레벨에서 가장 왼쪽노드와 오른쪽 노드를 기록
        levelMin[level] = Math.min(levelMin[level], loc);
        levelMax[level] = loc++;

        // 오른쪽 노드
        if (cur.right != -1) {
            inorder(cur.right, level + 1);
        }
    }


    static class Node{
        int parent, cur, left, right;

        Node(int parent, int cur, int left, int right) {
            this.parent = parent;
            this.cur = cur;
            this.left = left;
            this.right = right;
        }
    }
}
