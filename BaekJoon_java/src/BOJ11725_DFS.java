import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *  No.11725: 트리의 부모 찾기
 *  URL: https://www.acmicpc.net/problem/11725
 *  Description: 루트가 1인 트리가 주어질 때, 각 노드의 부모를 구하는 문제
 *  Hint: DFS 활용
 */

public class BOJ11725_DFS {
    static ArrayList<Integer>[] list;
    static int n;
    static int[] pNode;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        // 간선 저장
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        pNode = new int[n + 1];

        dfs(1, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            sb.append(pNode[i]).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    // DFS
    static void dfs(int cur, int parent) {
        pNode[cur] = parent;
        for (int child : list[cur]) {
            if (child != parent) {
                dfs(child, cur);
            }
        }
    }
}
