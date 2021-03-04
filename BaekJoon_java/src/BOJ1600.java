import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  No.1600:
 *  URL: https://www.acmicpc.net/problem/1600
 *  Hint: BFS + 큐의 데이터 타입은 int[]가 아닌 별도의 클래스로 해야 메모리초과가 안남.
 */

public class BOJ1600 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int[][] arr = new int[h][w];

        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = bfs(k, w, h, arr);
        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    static int bfs(int k, int w, int h, int[][] arr) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int[] hr = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] hc = {1, -1, 2, -2, 2, -2, 1, -1};

        boolean[][][] visited = new boolean[h][w][k + 1];
        Queue<Monkey> q = new LinkedList<>();
        q.offer(new Monkey(0, 0, 0, 0));
        visited[0][0][0] = true;
        int res = -1;

        while (!q.isEmpty()) {
            Monkey cur = q.poll();
            if (cur.r == h - 1 && cur.c == w - 1) {
                return cur.cnt;
            }

            // 상하좌우 이동
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (isPossible(nr, nc, h, w) && arr[nr][nc] == 0 && !visited[nr][nc][cur.k]) {
                    q.offer(new Monkey(nr, nc, cur.k, cur.cnt + 1));
                    visited[nr][nc][cur.k] = true;
                }
            }

            // 말 이동을 k번 했으면 더이상 말 이동은 불가능
            if (cur.k == k) {
                continue;
            }

            // 말 이동
            for (int i = 0; i < 8; i++) {
                int nr = cur.r + hr[i];
                int nc = cur.c + hc[i];
                if (isPossible(nr, nc, h, w) && arr[nr][nc] == 0 && !visited[nr][nc][cur.k + 1]) {
                    q.offer(new Monkey(nr, nc, cur.k + 1, cur.cnt + 1));
                    visited[nr][nc][cur.k + 1] = true;
                }
            }
        }
        return res;
    }

    static boolean isPossible(int x, int y, int h, int w) {
        if (x >= 0 && x < h && y >= 0 && y < w) {
            return true;
        } else {
            return false;
        }
    }

    static class Monkey {
        int r, c, k, cnt;

        Monkey(int r, int c, int k, int cnt) {
            this.r = r;
            this.c = c;
            this.k = k;
            this.cnt = cnt;
        }
    }
}
