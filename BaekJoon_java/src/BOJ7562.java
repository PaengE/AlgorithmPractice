import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  no.7562: 나이트의 이동
 *  url: https://www.acmicpc.net/problem/7562
 *  hint: BFS + DP
 */

public class BOJ7562 {
    static int l, endX, endY;
    static int[][] chess, dp;
    static boolean[][] visited;
    static int[] dx = {-2, -1, -2, -1, 1, 2, 1, 2};
    static int[] dy = {1, 2, -1, -2, 2, 1, -2, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (t-- > 0) {
            l = Integer.parseInt(br.readLine());
            chess = new int[l][l];
            dp = new int[l][l];
            visited = new boolean[l][l];

            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());

            bfs(startX, startY);

            bw.write(dp[endX][endY] + "\n");
        }

        bw.close();
        br.close();
    }

    static void bfs(int sx, int sy) {
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();

        qx.offer(sx);
        qy.offer(sy);

        while (!qx.isEmpty()) {
            int cx = qx.poll();
            int cy = qy.poll();

            if (cx == endX && cy == endY) {
                break;
            }

            for (int i = 0; i < 8; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (isPossible(nx, ny) && !visited[nx][ny]) {
                    qx.offer(nx);
                    qy.offer(ny);
                    dp[nx][ny] = dp[cx][cy] + 1;
                    visited[nx][ny] = true;
                }
            }
        }
    }

    static boolean isPossible(int x, int y) {
        if (x < 0 || x >= l || y < 0 || y >= l) {
            return false;
        }

        return true;
    }
}
