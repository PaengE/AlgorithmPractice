import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *  No.9944: NxM 보드 완주하기
 *  Hint: BruteForce + 구현
 */

public class BOJ9944 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n, m, ans;
    static int size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        int tc = 1;
        String s = "";
        while ((s = br.readLine()) != null && !s.isEmpty()) {
            StringTokenizer st = new StringTokenizer(s);
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            boolean[][] visited = new boolean[n][m];
            List<Point> startPoints = new ArrayList<>();
            ans = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                s = br.readLine();
                for (int j = 0; j < m; j++) {
                    if (s.charAt(j) == '*') {
                        visited[i][j] = true;
                    } else {
                        startPoints.add(new Point(i, j, 0));
                    }
                }
            }

            size = startPoints.size() - 1;
            for (Point start : startPoints) {
                boolean[][] tmp = copyArrays(visited);
                tmp[start.x][start.y] = true;
                solve(start, tmp, 0);
            }

            ans = ans == Integer.MAX_VALUE ? -1 : ans;
            sb.append("Case " + tc++ + ": " + ans + "\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();

    }

    static void solve(Point cur, boolean[][] visited, int cnt) {
        if (cur.fillCount == size) {
            ans = Math.min(ans, cnt);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = cur.x + dx[i];
            int ny = cur.y + dy[i];

            if (inRange(nx, ny) && !visited[nx][ny]) {
                boolean[][] tmp = copyArrays(visited);
                Point nextStartPoint;
                switch (i) {
                    case 0:
                        nextStartPoint = goUp(cur, tmp);
                        solve(nextStartPoint, tmp, cnt + 1);
                        break;
                    case 1:
                        nextStartPoint = goDown(cur, tmp);
                        solve(nextStartPoint, tmp, cnt + 1);
                        break;

                    case 2:
                        nextStartPoint = goLeft(cur, tmp);
                        solve(nextStartPoint, tmp, cnt + 1);
                        break;

                    case 3:
                        nextStartPoint = goRight(cur, tmp);
                        solve(nextStartPoint, tmp, cnt + 1);
                        break;
                }
            }
        }
    }

    // 왼쪽으로 방문하지 않은 최대 위치를 반환
    static Point goLeft(Point cur, boolean[][] visited) {

        for (int i = cur.y - 1; i >= 0; i--) {
            if (visited[cur.x][i]) {
                return new Point(cur.x, i + 1, cur.fillCount + (cur.y - (i + 1)));
            }
            visited[cur.x][i] = true;
        }

        return new Point(cur.x, 0, cur.fillCount + cur.y);
    }

    // 오른쪽으로 방문하지 않은 최대 위치를 반환
    static Point goRight(Point cur, boolean[][] visited) {

        for (int i = cur.y + 1; i < m; i++) {
            if (visited[cur.x][i]) {
                return new Point(cur.x, i - 1, cur.fillCount + (i - 1) - cur.y);
            }
            visited[cur.x][i] = true;
        }

        return new Point(cur.x, m - 1, cur.fillCount + (m - 1) - cur.y);
    }

    // 위쪽으로 방문하지 않은 최대 위치를 반환
    static Point goUp(Point cur, boolean[][] visited) {

        for (int i = cur.x - 1; i >= 0; i--) {
            if (visited[i][cur.y]) {
                return new Point(i + 1, cur.y, cur.fillCount + cur.x - (i + 1));
            }
            visited[i][cur.y] = true;
        }

        return new Point(0, cur.y, cur.fillCount + cur.x);
    }

    // 아래쪽으로 방문하지 않은 최대 위치를 반환
    static Point goDown(Point cur, boolean[][] visited) {

        for (int i = cur.x + 1; i < n; i++) {
            if (visited[i][cur.y]) {
                return new Point(i - 1, cur.y, cur.fillCount + ((i - 1) - cur.x));
            }
            visited[i][cur.y] = true;
        }

        return new Point(n - 1, cur.y, cur.fillCount + (n - 1) - cur.x);
    }

    static boolean inRange(int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < m) {
            return true;
        }
        return false;
    }

    static boolean[][] copyArrays(boolean[][] arr) {
        boolean[][] copy = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = arr[i][j];
            }
        }
        return copy;
    }

    static class Point{
        int x, y, fillCount;

        Point(int x, int y, int fillCount) {
            this.x = x;
            this.y = y;
            this.fillCount = fillCount;
        }
    }
}
