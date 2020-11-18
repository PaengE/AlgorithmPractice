import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * No.1197: 최소 스패닝 트리(MST: Minimum Spanning Tree)
 * URL: https://www.acmicpc.net/problem/1197
 * Hint: Kruskal Algorithm 이용
 * --- 간선의 개수가 적으면 크루스칼이 빠르고, 정점의 개수가 적다면 프림이 빠르다 ---
 *
 * - 탐욕적인 방법(greedy method) 을 이용하여 그래프의 모든
 *   정점을 최소 비용으로 연결하는 최소 비용을 구하는 것이다.
 *
 * 1. 그래프의 간선들을 가중치의 오름차순으로 정렬한다.
 *
 * 2. 정렬된 간선 리스트에서 순서대로 사이클을 형성하지 않는 간선을 선택한다.
 *
 * 3. 해당 간선을 현재의 MST(최소 비용 신장 트리)의 집합에 추가한다.
 *
 *  << UnionFind 를 이용하여 서로 다른 집합이면서 사이클이 형성되지 않는다면
 *  간선을 추가하고 같은 집합으로 만든다. >>
 *
 */

public class BOJ1197_Kruskal {
    static int[] p;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        p = new int[v + 1];
        pq = new PriorityQueue<>();

        for (int i = 1; i <= v; i++) {
            p[i] = i;
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(v1, v2, weight));
        }

        long answer = kruskal(v);

        bw.write(String.valueOf(answer));
        bw.flush();
        br.close();
        bw.close();
    }

    static long kruskal(int nodeCount) {
        long res = 0, cnt = 0;

        while (!pq.isEmpty()) {
            // 우선순위 큐를 이용하여 최소 가중치 간선을 꺼냄
            Edge edge = pq.poll();


            if (findSet(p, edge.v1) != findSet(p, edge.v2)) {
                // 연결된 weight 를 총 비용에 더함
                res += edge.weight;

                // 모두 연결됐으면 break
                if (++cnt == nodeCount) {
                    break;
                }

                // v1 set 과 v2 set 을 합침
                union(p, edge.v1, edge.v2);
            }

        }
        return res;
    }

    //x가 속한 집합의 부모를 찾는다.
    public static int findSet(int[] p, int x) {
        if (p[x] == x)
            return x;
        else
            return p[x] = findSet(p, p[x]);
    }
    //x와 y를 같은 집합으로 합친다.(노드 번호가 작은 것이 위로 가게끔)
    public static void union(int[] p, int x, int y) {
        if (x < y){
            p[findSet(p, y)] = findSet(p, x);
        }
        else{
            p[findSet(p, x)] = findSet(p, y);
        }

    }

    static class Edge implements Comparable<Edge> {
        int v1, v2, weight;

        Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
