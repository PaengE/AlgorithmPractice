import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  No.10026: 적록색약
 *  URL: https://www.acmicpc.net/problem/10026
 *  Hint: BFS
 */

public class BOJ10026 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        char[][] map = new char[n][n];
        char[][] mapGR = new char[n][n];
        boolean[][] visited = new boolean[n][n];
        boolean[][] visitedGR = new boolean[n][n];
        int area = 0;
        int areaGR = 0;

        for (int i = 0; i < n; i++) {
            char[] t = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                map[i][j] = t[j];
                if (t[j] == 'G') {
                    mapGR[i][j] = 'R';
                } else {
                    mapGR[i][j] = t[j];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, map, visited);
                    area++;
                }

                if (!visitedGR[i][j]) {
                    bfs(i, j, mapGR, visitedGR);
                    areaGR++;
                }
            }
        }

        bw.write(area + " " + areaGR);
        bw.close();
        br.close();
    }

    static void bfs(int x, int y, char[][] m, boolean[][] v) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{x, y});
        v[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (m[cur[0]][cur[1]] == m[nx][ny] && !v[nx][ny]) {
                        q.offer(new int[]{nx, ny});
                        v[nx][ny] = true;
                    }
                }
            }
        }
    }
}
