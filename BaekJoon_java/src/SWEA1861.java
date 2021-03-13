import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1861 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int start, count, n;
    static int[][] arr;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            start = Integer.MAX_VALUE;
            count = Integer.MIN_VALUE;

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    visited = new boolean[n][n];
                    bfs(i, j);
                }
            }

            sb.append("#" + tc + " " + start + " " + count + "\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void bfs(int r, int c) {
        Queue<Integer> qr = new LinkedList<>();
        Queue<Integer> qc = new LinkedList<>();
        qr.offer(r);
        qc.offer(c);
        visited[r][c] = true;
        int cur = arr[r][c];
        int cnt = 0;

        while (!qr.isEmpty()) {
            int cr = qr.poll();
            int cc = qc.poll();
            int cn = arr[cr][cc];
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if (inRange(nr, nc) && arr[nr][nc] - cn == 1 && !visited[nr][nc]) {
                    qr.offer(nr);
                    qc.offer(nc);
                    visited[nr][nc] = true;
                }
            }
        }

        if (cnt > count) {
            count = cnt;
            start = cur;
        } else if (cnt == count) {
            start = Math.min(start, cur);
        }
    }

    static boolean inRange(int r, int c) {
        if (r >= 0 && r < n && c >= 0 && c < n) {
            return true;
        } else {
            return false;
        }
    }
}
