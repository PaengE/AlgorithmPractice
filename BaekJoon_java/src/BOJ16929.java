import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.16929: Two Dots
 *  URL: https://www.acmicpc.net/problem/16929
 *  Hint: DFS + 사이클 체크
 */

public class BOJ16929 {
    static int n, m;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static String answer = "No";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 모든 점을 탐색
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    dfs(-1, -1, i, j);
                }
            }
        }

        bw.write(answer);
        bw.close();
        br.close();
    }

    // DFS
    static void dfs(int px, int py, int cx, int cy) {
        if (answer.equals("Yes")) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            // 범위 안에 있고, 왔던 길이 아니고, 같은 문자인 경우
            if (inRange(nx, ny) && (px != nx || py != ny) && map[cx][cy] == map[nx][ny]) {
                if (visited[nx][ny]) {  // 방문한 적이 있다면 cycle이 있다는 소리
                    answer = "Yes";
                    return;
                } else {
                    visited[nx][ny] = true; // 방문한 적이 없다면 계속 탐색
                    dfs(cx, cy, nx, ny);
                }
            }
        }
    }

    // 주어진 범위를 벗어나는지 판단
    static boolean inRange(int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < m) {
            return true;
        }
        return false;
    }
}
