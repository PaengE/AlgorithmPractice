import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class Network {
    static boolean[] visited;
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ans += bfs(i, computers);
            }
        }

        return ans;
    }

    private int bfs(int start, int[][] computers) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        while (!q.isEmpty()) {
            int cur = q.poll();
            visited[cur] = true;

            for (int i = 0; i < computers.length; i++) {
                if (computers[cur][i] == 1 && !visited[i]) {
                    q.offer(i);
                }
            }
        }
        return 1;
    }

    @Test
    public void test() {
        Assertions.assertEquals(2, solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        Assertions.assertEquals(1, solution(3, new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));
        Assertions.assertEquals(3, solution(3, new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}));
    }
}
