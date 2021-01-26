import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

public class ConnectIsland {
    static int[] parent;
    public int solution(int n, int[][] costs) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        // 노드 i 의 부모노드 초기화
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 모든 간선을 cost 오름차순으로 priority queue 에 삽입
        for (int i = 0; i < costs.length; i++) {
            pq.offer(new Edge(costs[i][0], costs[i][1], costs[i][2]));
        }

        // 크루스칼 알고리즘 수행
        return kruskal(pq, n);
    }

    // 크루스칼 알고리즘
    private int kruskal(PriorityQueue<Edge> pq, int nodeCount) {
        int res = 0, cnt = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            // 두 노드의 부모가 같지 않다면 간선 cost 를 더하고 하나로 묶음(union)
            if (findSet(edge.v1) != findSet(edge.v2)) {
                res += edge.cost;

                if (++cnt == nodeCount) {
                    break;
                }
                union(edge.v1, edge.v2);
            }
        }

        // 간선 cost 의 합을 리턴
        return res;
    }

    // 노드 x의 부모를 찾고, 저장
    private int findSet(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            return parent[x] = findSet(parent[x]);
        }
    }

    // 두 노드 x, y를 합침(더 작은 숫자가 부모가 되게)
    private void union(int x, int y) {
        if (x < y) {
            parent[findSet(y)] = parent[findSet(x)];
        } else {
            parent[findSet(x)] = parent[findSet(y)];
        }
    }

    // Edge 클래스 (cost 오름차순)
    private class Edge implements Comparable<Edge> {
        private int v1, v2, cost;
        Edge(int s, int e, int cost) {
            this.v1 = s;
            this.v2 = e;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    @Test
    public void test() {
        Assertions.assertEquals(4
                , solution(4, new int[][]{{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}}));
    }
}
