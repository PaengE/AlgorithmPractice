import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Delivery {
    public int solution(int n, int[][] road, int k) {
        final int M = 500001;
        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    map[i][j] = M;
                }
            }
        }

        for (int[] t : road) {
            map[t[0] - 1][t[1] - 1] = Math.min(map[t[0] - 1][t[1] - 1], t[2]);
            map[t[1] - 1][t[0] - 1] = Math.min(map[t[1] - 1][t[0] - 1], t[2]);
        }

        // dijkstra
        int[] dist = new int[n];
        Arrays.fill(dist, M);
        boolean[] visited = new boolean[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> Integer.compare(dist[o1], dist[o2])));

        dist[0] = 0;
        pq.offer(0);
        while (!pq.isEmpty()) {
            int cur = pq.poll();

            if (visited[cur]) {
                continue;
            }

            visited[cur] = true;
            for (int i = 0; i < n; i++) {
                if (map[cur][i] != M && dist[i] > dist[cur] + map[cur][i]) {
                    dist[i] = dist[cur] + map[cur][i];
                    pq.offer(i);
                }
            }
        }

        int answer = 0;
        for (int t : dist) {
            if (t <= k) {
                answer++;
            }
        }
        return answer;
    }

    @Test
    public void test() {
        Assertions.assertEquals(4, solution(5, new int[][]{{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}}, 3));
        Assertions.assertEquals(4, solution(6, new int[][]{{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}}, 4));
    }
}
