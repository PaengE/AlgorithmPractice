import java.io.*;
import java.util.StringTokenizer;

public class BOJ3109 {
    static int r, c, ans;
    static int[] dx = {-1, 0, 1};
    static char[][] map;
    static boolean[][] visited;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray().clone();
        }

        for (int i = 0; i < r; i++) {
            if (map[i][0] == '.') {
                flag = false;
                dfs(i, 1);
            }
        }

        bw.write(String.valueOf(ans));
        br.close();
        bw.close();
    }

    static void dfs(int x, int y) {
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            if (isPossible(nx, y) && map[nx][y] == '.' && !visited[nx][y]) {
                // 마지막 열일 경우
                if (y == c - 1) {
                    visited[nx][y] = true;
                    ans++;
                    flag = true;    // 도착했음을 표시
                    return;
                }

                visited[nx][y] = true;
                dfs(nx, y + 1);
                if (flag) {
                    return;
                }
            }
        }
    }

    static boolean isPossible(int x, int y) {
        if (x >= 0 && x < r && y >= 0 && y < c) {
            return true;
        } else {
            return false;
        }
    }
}
