import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *  No.14938: 서강그라운드
 *  URL: https://www.acmicpc.net/problem/14938
 *  Hint: 다익스트라 or 플로이드와샬
 */

public class BOJ14938 {
    static int n, m, r; // 지역 개수, 수색 범위, 길의 개수
    static int[] dist, item;    // 시작점으로부터 각 지역까지의 거리, 각 지역의 아이템 개수
    static boolean[] visited;   // 방문 여부를 판단
    static ArrayList<ArrayList<Node>> graph;    // 인접리스트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        item = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 양방향 인접리스트 구성
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(v1).add(new Node(v2, cost));
            graph.get(v2).add(new Node(v1, cost));
        }

        dist = new int[n + 1];
        visited = new boolean[n + 1];

        int ans = 0;
        // 각 지역에서부터 다익스트라를 수행
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dijkstra(i));
        }

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    static int dijkstra(int start) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(visited, false);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (!visited[cur.to]) {
                visited[cur.to] = true;

                for (Node next : graph.get(cur.to)) {
                    if (!visited[next.to] && dist[next.to] > dist[cur.to] + next.cost) {
                        dist[next.to] = dist[cur.to] + next.cost;
                        pq.offer(new Node(next.to, dist[next.to]));
                    }
                }
            }
        }

        // 도달할 수 있는 곳 중 수색범위 안에 있는 곳의 아이템개수를 모두 카운팅
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] <= m) {
                res += item[i];
            }
        }

        return res;
    }

    static class Node implements Comparable<Node> {
        int to, cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
}
