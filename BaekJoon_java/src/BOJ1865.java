import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  No.1865: 웜홀
 *  URL: https://www.acmicpc.net/problem/1865
 *  Hint: 벨만포드 알고리즘(음의 사이클 찾기)
 */

public class BOJ1865 {
    static final int INF = 500 * 10000;
    static ArrayList<ArrayList<Edge>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m + w; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                if (i < m) {
                    graph.get(e).add(new Edge(s, t));
                    graph.get(s).add(new Edge(e, t));
                } else {
                    graph.get(s).add(new Edge(e, t * -1));
                }
            }

            boolean minusCycle = false;
            for (int i = 1; i <= n; i++) {
                if (bellmanFord(i, n)) {
                    minusCycle = true;
                    sb.append("YES\n");
                    break;
                }
            }
            if (!minusCycle) {
                sb.append("NO\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static boolean bellmanFord(int start, int nodeCount) {
        int[] dist = new int[nodeCount + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        boolean update = false;

        // (정점개수)번 동안 수행
        for (int i = 1; i <= nodeCount; i++) {
            update = false;

            // 최단거리 초기화화
            for (int j = 1; j <= nodeCount; j++) {
                for (Edge cur : graph.get(j)) {
                    if (dist[j] != INF && dist[cur.e] > dist[j] + cur.t) {
                        dist[cur.e] = dist[j] + cur.t;
                        update = true;
                    }
                }
            }
            if (!update) {
               break;
            }
        }
        return update;
    }

    static class Edge{
        int e, t;

        Edge(int e, int t) {
            this.e = e;
            this.t = t;
        }
    }
}
