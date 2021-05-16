import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  No.14442: 벽 부수고 이동하기 2
 *  URL: https://www.acmicpc.net/problem/14442
 *  Hint: BFS
 */

public class BOJ14442 {
    static int n, m, k;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m][k + 1];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        int ans = bfs();
        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    static int bfs() {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0, 0, 1));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            if (cur.x == n - 1 && cur.y == m - 1) {
                return cur.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (inRange(nx, ny)) {
                    if (map[nx][ny] == 0 && !visited[nx][ny][cur.broken]) {
                        visited[nx][ny][cur.broken] = true;
                        q.offer(new Point(nx, ny, cur.broken, cur.cnt + 1));
                    }

                    if (cur.broken + 1 > k) {
                        continue;
                    }

                    if (map[nx][ny] == 1 && !visited[nx][ny][cur.broken + 1]) {
                        visited[nx][ny][cur.broken + 1] = true;
                        q.offer(new Point(nx, ny, cur.broken + 1, cur.cnt + 1));
                    }
                }
            }
        }
        return -1;
    }

    static boolean inRange(int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < m) {
            return true;
        }
        return false;
    }

    static class Point{
        int x, y, broken, cnt;

        Point(int x, int y, int broken, int cnt) {
            this.x = x;
            this.y = y;
            this.broken = broken;
            this.cnt = cnt;
        }
    }
}
