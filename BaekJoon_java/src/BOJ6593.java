import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  No.6593: 상범 빌딩
 *  Hint: BFS
 */

public class BOJ6593 {
    static int l, r, c, sl, sr, sc, el, er, ec;
    static char[][][] map;
    static boolean[][][] visited;
    static int[] dl = {-1, 1, 0, 0, 0, 0};
    static int[] dr = {0, 0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s;
        StringBuilder sb = new StringBuilder();
        while (!(s = br.readLine()).equals("0 0 0")) {
            StringTokenizer st = new StringTokenizer(s);
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            map = new char[l][r][c];
            visited = new boolean[l][r][c];

            for (int i = 0; i < l; i++) {
                for (int j = 0; j < r; j++) {
                    String str = br.readLine();
                    for (int k = 0; k < c; k++) {
                        char c = str.charAt(k);
                        map[i][j][k] = c;

                        if (c == '#') {
                            visited[i][j][k] = true;
                        } else if (c == 'S') {
                            sl = i;
                            sr = j;
                            sc = k;
                        } else if (c == 'E') {
                            el = i;
                            er = j;
                            ec = k;
                        }
                    }
                }
                br.readLine();
            }

            String res = bfs();
            if (res.equals("-1")) {
                sb.append("Trapped!\n");
            } else {
                sb.append("Escaped in " + res + " minute(s).\n");
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static String bfs() {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(sl, sr, sc, 0));
        visited[sl][sr][sc] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            if (cur.l == el && cur.r == er && cur.c == ec) {
                return String.valueOf(cur.cnt);
            }

            for (int i = 0; i < 6; i++) {
                int nl = cur.l + dl[i];
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (isInRange(nl, nr, nc) && !visited[nl][nr][nc]) {
                    q.offer(new Point(nl, nr, nc, cur.cnt + 1));
                    visited[nl][nr][nc] = true;
                }
            }
        }

        return "-1";
    }

    // 범위 체크
    static boolean isInRange(int tl, int tr, int tc) {
        if (tl >= 0 && tl < l && tr >= 0 && tr < r && tc >= 0 && tc < c) {
            return true;
        }
        return false;
    }

    static class Point {
        int l, r, c, cnt;

        Point(int l, int r, int c, int cnt) {
            this.l = l;
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
}
