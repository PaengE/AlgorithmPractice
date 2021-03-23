import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  No.2638: 치즈
 *  URL: https://www.acmicpc.net/problem/2638
 *  Hint: BFS 완전탐색
 */

public class BOJ2638 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n, m;
    static int[][] map, copy;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        copy = new int[n][m];

        ArrayList<P> cheeseList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    cheeseList.add(new P(i, j));
                }
            }
        }


        int answer = 0;
        while (!cheeseList.isEmpty()) {
            bfs(0, 0);
            for (int i = 0; i < cheeseList.size(); i++) {
                P cheese = cheeseList.get(i);
                int contactSide = 0;

                for (int j = 0; j < 4; j++) {
                    if (copy[cheese.x + dx[j]][cheese.y + dy[j]] == -1) {
                        contactSide++;
                    }

                    if (contactSide >= 2) {
                        cheeseList.remove(i--);
                        map[cheese.x][cheese.y] = 0;
                        break;
                    }
                }
            }
            answer++;
        }

        bw.write(String.valueOf(answer));
        bw.close();
        br.close();
    }

    static void bfs(int startX, int startY) {
        Queue<P> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        q.offer(new P(startX, startY));
        visited[startX][startY] = true;

        copyMap();
        copy[startX][startY] = -1;

        while (!q.isEmpty()) {
            P cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (inRange(nx, ny) && map[nx][ny] == 0 && !visited[nx][ny]) {
                    q.offer(new P(nx, ny));
                    visited[nx][ny] = true;
                    copy[nx][ny] = -1;
                }
            }

        }
    }

    static void copyMap() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = map[i][j];
            }
        }
    }

    static boolean inRange(int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < m) {
            return true;
        } else {
            return false;
        }
    }

    static class P{
        int x, y;

        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
