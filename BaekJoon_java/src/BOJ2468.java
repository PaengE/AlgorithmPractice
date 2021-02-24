import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  No.2468: 안전 영역
 *  URL: https://www.acmicpc.net/problem/2468
 *  Hint: BFS
 */

public class BOJ2468 {
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        int minH = Integer.MAX_VALUE;
        int maxH = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                minH = Math.min(minH, arr[i][j]);
                maxH = Math.max(maxH, arr[i][j]);
            }
        }


        int ans = 1;
        for (int h = minH; h < maxH; h++) {
            boolean[][] visited = new boolean[n][n];

            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] > h && !visited[i][j]) {
                        bfs(i, j, n, h, visited);
                        cnt++;
                    }
                }
            }
            ans = Math.max(ans, cnt);
        }

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();

    }

    static void bfs(int sx, int sy, int n, int limit, boolean[][] visited) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{sx, sy});
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (arr[nx][ny] > limit && !visited[nx][ny]) {
                        q.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }
}
