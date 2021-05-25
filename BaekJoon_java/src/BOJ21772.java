import java.io.*;
import java.util.StringTokenizer;

public class BOJ21772 {
    static int r, c, t, sx, sy, ans;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'G') {
                    sx = i;
                    sy = j;
                }
            }
        }

        visited[sx][sy] = true;
        solve(sx, sy, 0, 0);

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    static void solve(int cx, int cy, int time, int cnt) {
        if (time == t) {
            ans = Math.max(ans, cnt);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (inRange(nx, ny) && map[nx][ny] != '#') {
                if (map[nx][ny] == 'S') {
                    map[nx][ny] = '.';
                    solve(nx, ny, time + 1, cnt + 1);
                    map[nx][ny] = 'S';
                } else {
                    solve(nx, ny, time + 1, cnt);
                }
            }
        }
    }

    static boolean inRange(int x, int y) {
        if (x >= 0 && x < r && y >= 0 && y < c) {
            return true;
        }
        return false;
    }
}
