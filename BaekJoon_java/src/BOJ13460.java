import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  No.13460: 구슬 탈출 2
 *  URL: https://www.acmicpc.net/problem/13460
 *  Hint: 구현 + 시뮬레이션 + BFS
 */

public class BOJ13460 {
    static int n, m;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        int bx = 0, by = 0, rx = 0, ry = 0;
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);

                if (map[i][j] == 'B') {
                    bx = i;
                    by = j;
                } else if (map[i][j] == 'R') {
                    rx = i;
                    ry = j;
                }
            }
        }

        int ans = bfs(bx, by, rx, ry);

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    static int bfs(int bx, int by, int rx, int ry) {
        int[] dx = {0, 0, -1, 1};   // 좌, 우, 상, 하
        int[] dy = {-1, 1, 0, 0};

        boolean[][][][] visited = new boolean[n][m][n][m];  // [bx][by][rx][ry]
        Queue<Ball> q = new LinkedList<>();
        visited[bx][by][rx][ry] = true;
        q.offer(new Ball(bx, by, rx, ry, 1));

        while (!q.isEmpty()) {
            Ball cur = q.poll();

            if (cur.count > 10) {
                return -1;
            }

            for (int i = 0; i < 4; i++) {
                // 파란공 굴리기
                int nextBlueX = cur.blueX + dx[i];
                int nextBlueY = cur.blueY + dy[i];
                boolean blueCanMove = true;
                while (map[nextBlueX][nextBlueY] != '#') {  // 벽을 만날 때까지
                    if (map[nextBlueX][nextBlueY] == 'O') {
                        blueCanMove = false;
                        break;
                    }
                    nextBlueX += dx[i];
                    nextBlueY += dy[i];
                }   // 최종 nextBlueX, nextBlueY 는 만난 벽의 위치
                // 따라서 바로 직전위치로 회귀
                nextBlueX -= dx[i];
                nextBlueY -= dy[i];

                // 파란공이 구멍에 빠지면 continue
                if (!blueCanMove) {
                    continue;
                }

                // 빨간공 굴리기
                int nextRedX = cur.redX + dx[i];
                int nextRedY = cur.redY + dy[i];

                while (map[nextRedX][nextRedY] != '#') {    // 벽을 만날 때까지
                    if (map[nextRedX][nextRedY] == 'O') {
                        return cur.count;
                    }
                    nextRedX += dx[i];
                    nextRedY += dy[i];
                }   // 최종 nextRedX, nextRedY 는 만난 벽의 위치
                // 따라서 바로 직전위치로 회귀
                nextRedX -= dx[i];
                nextRedY -= dy[i];

                // 두 공 모두 굴린 후, 위치가 같으면 위치 재조정
                if (nextBlueX == nextRedX && nextBlueY == nextRedY) {
                    switch (i) {
                        case 0: // 좌로 굴렸을 때
                            if (cur.blueY < cur.redY) {
                                nextRedY++;
                            } else {
                                nextBlueY++;
                            }
                            break;
                        case 1: // 우로 굴렸을 때
                            if (cur.blueY < cur.redY) {
                                nextBlueY--;
                            } else {
                                nextRedY--;
                            }
                            break;
                        case 2: // 위로 굴렸을 때
                            if (cur.blueX < cur.redX) {
                                nextRedX++;
                            } else {
                                nextBlueX++;
                            }
                            break;
                        case 3: // 아래로 굴렸을 때
                            if (cur.blueX < cur.redX) {
                                nextBlueX--;
                            } else {
                                nextRedX--;
                            }
                            break;
                    }
                }

                if (!visited[nextBlueX][nextBlueY][nextRedX][nextRedY]) {
                    q.offer(new Ball(nextBlueX, nextBlueY, nextRedX, nextRedY, cur.count + 1));
                    visited[nextBlueX][nextBlueY][nextRedX][nextRedY] = true;
                }
            }
        }

        return -1;
    }


    static class Ball {
        int blueX, blueY, redX, redY, count;

        Ball(int blueX, int blueY, int redX, int redY, int count) {
            this.blueX = blueX;
            this.blueY = blueY;
            this.redX = redX;
            this.redY = redY;
            this.count = count;
        }
    }
}
