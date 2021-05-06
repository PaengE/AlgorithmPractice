import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  No.2146: 다리 만들기
 *  URL: https://www.acmicpc.net/problem/2146
 *  Hint: BFS in 행렬
 */

public class BOJ2146 {
    static int n, index;
    static int[][] map, group;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        group = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬을 인덱싱
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    index++;
                    indexing(i, j);
                }
            }
        }

        // 각 섬을 기준으로 가장 가까운 섬과의 거리를 구함
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= index; i++) {
            ans = Math.min(ans, bfs(i));
        }

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    // start 번호인 섬들을 시작점으로 bfs
    static int bfs(int start) {
        Queue<Point> q = new ArrayDeque<>();
        visited = new boolean[n][n];

        // 시작점들을 q에 offer
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (group[i][j] == start) {
                    q.offer(new Point(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (inRange(nx, ny) && !visited[nx][ny]) {
                    // 번호가 다른 섬을 만났으면 거리를 리턴
                    if (group[nx][ny] != 0 && group[nx][ny] != start) {
                        return cur.dist;
                    }

                    visited[nx][ny] = true;
                    q.offer(new Point(nx, ny, cur.dist + 1));
                }
            }
        }

        return 0;
    }

    // 섬을 인덱싱 (BFS)
    static void indexing(int sx, int sy) {
        Queue<Point> q = new ArrayDeque<>();

        q.offer(new Point(sx, sy));
        visited[sx][sy] = true;
        group[sx][sy] = index;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (inRange(nx, ny) && map[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    group[nx][ny] = index;
                    q.offer(new Point(nx, ny));
                }
            }
        }
    }

    // 올바른 범위인지를 판단
    static boolean inRange(int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < n) {
            return true;
        }
        return false;
    }

    static class Point {
        int x, y, dist;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
