import java.io.*;
import java.util.*;

/**
 *  No.4803: 트리
 *  url: https://www.acmicpc.net/problem/4803
 *  hint: 트리의 경우 노드개수 = 엣지개수 + 1
 */

public class BOJ4803 {
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testcase = 1;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) {
                break;
            }

            // 그래프 초기화
            graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            // 그래프 구성
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            visited = new boolean[n + 1];

            int count = 0;
            // 여러 트리가 존재할 수 있으므로 모든 노드를 검사해봄
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    count += checkTree(i);
                }
            }

            if (count == 0) {
                sb.append("Case " + testcase++ + ": No trees.");
            } else if (count == 1) {
                sb.append("Case " + testcase++ + ": There is one tree.");
            } else {
                sb.append("Case " + testcase++ + ": A forest of " + count + " trees.");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();

    }

    /**
     * 트리의 경우 노드개수 = 엣지개수 + 1 을 활용
     * 양방향 간선으로 그래프를 구성하였으므로 node = (edge / 2) + 1 이면 트리이다.
     *
     * @param start 시작점
     * @return      트리이면 1, 아니면 0
     */
    static int checkTree(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        int node = 0, edge = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            node += 1;
            visited[cur] = true;

            for (int next : graph.get(cur)) {
                edge += 1;
                if (!visited[next]) {
                    q.offer(next);
                }
            }
        }
        return (edge / 2) + 1 == node ? 1 : 0;
    }
}
