import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1261 {
    static int n, m;
    static int[][] map, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[m][n];
        dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(str[j]);
            }
        }

        bfs(0, 0);

        bw.write(String.valueOf(dp[m-1][n-1]));
        bw.close();
        br.close();

    }

    static void bfs(int sx, int sy) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Deque<P> deq = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];
        deq.offerFirst(new P(sx, sy, 0));
        visited[sx][sy] = true;

        while (!deq.isEmpty()) {
            P cur = deq.poll();
            if (cur.x == m - 1 && cur.y == n - 1) {
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        if (map[nx][ny] == 0) {
                            dp[nx][ny] = dp[cur.x][cur.y];
                            deq.offerFirst(new P(nx, ny, 0));
                        } else {
                            dp[nx][ny] = dp[cur.x][cur.y] + 1;
                            deq.offerLast(new P(nx, ny, 1));
                        }
                    }
                }
            }
        }
    }

    static class P {
        int x, y, flag;
        P(int x, int y, int flag) {
            this.x = x;
            this.y = y;
            this.flag = flag;
        }
    }
}
