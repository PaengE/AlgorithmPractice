import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * No.1197: 최소 스패닝 트리(MST: Minimum Spanning Tree)
 * URL: https://www.acmicpc.net/problem/1197
 * Hint: Prim Algorithm 이용
 * --- 간선의 개수가 적으면 크루스칼이 빠르고, 정점의 개수가 적다면 프림이 빠르다 ---
 *
 * - 트리에 연결되지 않은 정점들은 큐에 add되어 있다.
 *
 * - 각 정점들은인접한 정점 중 최소 비용으로 이동가능한 정점을 선택하여 추가한다.
 *
 * 1. 한 정점에서 시작하여 인접한 정점을 잇는 간선 중 가중치가 가장 낮은 간선을 선택한다.
 *  - 선택하여 연결 후 간선을 다시 탐색한다.
 * 2. 정점들이 이어져 있는 간선들을 반복해서 비교하며 가중치가 가장 낮은 간선을 추가한다.
 *
 * 3. 모든 요소가 이어졌다면 총 dis를 반환한다.
 *
 */

public class BOJ1197_Prim {
    static ArrayList<ArrayList<Edge>> graph;
    static boolean[] visited;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();
        visited = new boolean[v + 1];
        graph = new ArrayList<>();

        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(v1).add(new Edge(v2, weight));
            graph.get(v2).add(new Edge(v1, weight));
        }

        long answer = prim(1, v);

        bw.write(String.valueOf(answer));
        bw.flush();
        br.close();
        bw.close();
    }

    static long prim(int start, int nodeCount) {
        long res = 0, cnt = 0;

        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            // 우선순위 큐를 이용하여 최소 가중치 간선을 꺼냄
            Edge edge = pq.poll();

            // 방문했다면(연결이 돼있다면) continue
            if (visited[edge.node]) {
                continue;
            }

            // 방문표시를 하고(연결을 하고) 연결된 weight 를 총 비용에 더함
            visited[edge.node] = true;
            res += edge.weight;

            // 새로이 연결된 노드에 연결된 노드들 중 방문하지 않은(연결하지 않은) 노드들을 모두 추가
            for (Edge next : graph.get(edge.node)) {
                if (!visited[next.node]) {
                    pq.offer(next);
                }
            }

            // 모두 연결됐으면 break
            if (++cnt == nodeCount) {
                break;
            }
        }
        return res;
    }

    static class Edge implements Comparable<Edge> {
        int node, weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
