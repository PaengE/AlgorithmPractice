import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *  No.1238: 파티
 *  URL: https://www.acmicpc.net/problem/1238
 *  Hint: 다익스트라(플로이드 와샬은 시간초과)
 *        목표 정점 x에서 다른 정점으로 가는 최단거리 = 시작점을 x로 하는 일반적인 다익스트라
 *        다른 정점에서 목표 정점 x로 가는 최던거리 = 간선을 모두 뒤집은 뒤 시작점을 x로 하는 다익스트라
 *
 */

public class BOJ1238 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m, x;
    static final int INF = 1000 * 100 + 1;
    static ArrayList<ArrayList<P>> graph, reverseGraph;

    public static void main(String[] args) throws IOException {
        initGraph();

        int[] distance = dijkstra(graph);
        int[] reverseDistance = dijkstra(reverseGraph);

        int ans = -1;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, distance[i] + reverseDistance[i]);
        }

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    // 다익스트라 수행
    static int[] dijkstra(ArrayList<ArrayList<P>> way) {
        PriorityQueue<P> pq = new PriorityQueue<>();
        pq.offer(new P(x, 0));

        boolean[] visited = new boolean[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[x] = 0;

        while (!pq.isEmpty()) {
            P cur = pq.poll();

            if (!visited[cur.to]) {
                visited[cur.to] = true;

                for (P next : way.get(cur.to)) {
                    if (!visited[next.to] && dist[next.to] > dist[cur.to] + next.time) {
                        dist[next.to] = dist[cur.to] + next.time;
                        pq.offer(new P(next.to, dist[next.to]));
                    }
                }
            }
        }

        return dist;
    }

    // 그래프 초기화
    static void initGraph() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        reverseGraph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph.get(s).add(new P(e, t));
            reverseGraph.get(e).add(new P(s, t));
        }
    }

    static class P implements Comparable<P> {
        int to, time;

        P(int to, int time) {
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(P o) {
            return Integer.compare(this.time, o.time);
        }
    }
}
