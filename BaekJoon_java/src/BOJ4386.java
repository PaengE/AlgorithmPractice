import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * No.4386: 별자리 만들기
 * Description: 좌표 평면에서 MST 를 만드는 문제
 * URL: https://www.acmicpc.net/problem/4386
 * Hint: Kruskal or Prim 사용
 */

public class BOJ4386 {
    static int n;
    static double[][] stars;
    static boolean[] visited;
    static ArrayList<ArrayList<Edge>> graph;
    static PriorityQueue<Edge> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        stars = new double[n][2];
        graph = new ArrayList<>();
        visited = new boolean[n];
        pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());

            graph.add(new ArrayList<>());
        }

        // 한 정점에서 각 정점까지의 거리 계산
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double distance = Math.sqrt(Math.pow(stars[i][0] - stars[j][0], 2) + Math.pow(stars[i][1] - stars[j][1], 2));

                graph.get(i).add(new Edge(j, distance));
                graph.get(j).add(new Edge(i, distance));
            }
        }

        double ans = prim(0, n);

        bw.write(String.valueOf(ans));
        bw.flush();
        br.close();
        bw.close();
    }

    // 프림 알고리즘
    static double prim(int start, int nodeCount) {
        double res = 0.0;
        int cnt = 0;

        pq.offer(new Edge(start, 0.0));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (visited[edge.node]) {
                continue;
            }

            visited[edge.node] = true;
            res += edge.weight;

            for (Edge next : graph.get(edge.node)) {
                if (!visited[next.node]) {
                    pq.offer(next);
                }
            }

            if (++cnt == nodeCount) {
                break;
            }
        }
        return res;
    }

    static class Edge implements Comparable<Edge> {
        int node;
        double weight;

        Edge(int node, double weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }
}
