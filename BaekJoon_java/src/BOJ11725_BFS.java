import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  No.11725: 트리의 부모 찾기
 *  URL: https://www.acmicpc.net/problem/11725
 *  Description: 루트가 1인 트리가 주어질 때, 각 노드의 부모를 구하는 문제
 *  Hint: BFS 활용
 */

public class BOJ11725_BFS {
    static ArrayList<ArrayList<Integer>> list;
    static int n;
    static int[] pNode;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        // 간선 저장
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }

        pNode = new int[n + 1];
        visited = new boolean[n + 1];

        bfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            sb.append(pNode[i]).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    // BFS
    static void bfs(int root) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (visited[cur]) {
                continue;
            }
            visited[cur] = true;

            for (int next : list.get(cur)) {
                if (!visited[next]) {
                    q.offer(next);
                    pNode[next] = cur;
                }
            }
        }
    }
}