import java.io.*;
import java.util.PriorityQueue;

/**
 * no.2667: 단지번호붙이기
 * description: 2차원 배열을 그래프로 표현해 DFS 로 순회하는 문제
 * hint: DFS 를 이용해 현재 정점 기준 상하좌우를 방문하며 순회 (아직 방문하지 않았으면)
 */

public class BOJ2667_DFS {
    static boolean[][] visited;
    static int[][] map;
    static int dx[] = {0, 0, 1, -1};
    static int dy[] = {1, -1, 0, 0};
    static int n, count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        String[] s;
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            s = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(s[j]);
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    count = 0;
                    dfs(i, j);
                    pq.offer(count);
                }
            }
        }

        bw.write(pq.size() + "\n");

        while (!pq.isEmpty()) {
            bw.write(pq.poll() + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
    static void dfs(int x, int y) {
        visited[x][y] = true;
        count++;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                if (map[nx][ny] == 1 && !visited[nx][ny]) {
                    dfs(nx, ny);
                }
            }
        }
    }
}
