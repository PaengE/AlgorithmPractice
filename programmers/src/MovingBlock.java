import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MovingBlock {
    private int border;
    private boolean[][][] visited;
    private int[][][] dp;
    private int[][] board;

    public int solution(int[][] board) {
        border = board.length;
        this.board = new int[border][border];
        visited = new boolean[border][border][2];   // 0: 가로방향, 1: 세로방향 방문체크
        dp = new int[border][border][2];
        for (int[][] a : dp) {
            for (int[] b : a) {
                Arrays.fill(b, -1);
            }
        }

        for (int i = 0; i < border; i++) {
            this.board[i] = board[i].clone();
        }
        bfs(0, 0, 0, 1);

        return Math.max(dp[border - 1][border - 1][0], dp[border - 1][border - 1][1]);
    }

    private void bfs(int x1, int y1, int x2, int y2) {
        Queue<Robot> q = new LinkedList<>();
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int[] r = {-1, 1};

        visited[x1][y1][0] = true;
        visited[x2][y2][0] = true;
        dp[x1][y1][0] = 0;
        dp[x2][y2][0] = 0;
        q.offer(new Robot(x1, y1, x2, y2, 0));

        while (!q.isEmpty()) {
            Robot cur = q.poll();

            // 종료 조건
            if ((cur.x1 == border - 1 && cur.y1 == border - 1) || (cur.x2 == border - 1 && cur.y2 == border - 1)) {
                return;
            }

            // 상하좌우 이동
            for (int i = 0; i < 4; i++) {
                int nx1 = cur.x1 + dx[i];
                int ny1 = cur.y1 + dy[i];
                int nx2 = cur.x2 + dx[i];
                int ny2 = cur.y2 + dy[i];

                if (checkInRange(nx1, ny1) && checkInRange(nx2, ny2)) {
                    if (!visited[nx1][ny1][cur.direction] || !visited[nx2][ny2][cur.direction]) {
                        q.offer(new Robot(nx1, ny1, nx2, ny2, cur.direction));

                        visited[nx1][ny1][cur.direction] = true;
                        visited[nx2][ny2][cur.direction] = true;

                        if (dp[nx1][ny1][cur.direction] == -1) {
                            dp[nx1][ny1][cur.direction] = Math.max(dp[cur.x1][cur.y1][cur.direction], dp[cur.x2][cur.y2][cur.direction]) + 1;
                        }
                        if (dp[nx2][ny2][cur.direction] == -1) {
                            dp[nx2][ny2][cur.direction] = Math.max(dp[cur.x1][cur.y1][cur.direction], dp[cur.x2][cur.y2][cur.direction]) + 1;
                        }
                    }
                }
            }

            // 가로방향일 때 회전
            if (cur.direction == 0) {
                for (int i = 0; i < 2; i++) {
                    int nx1 = cur.x1 + r[i];
                    int ny1 = cur.y1;
                    int nx2 = cur.x2 + r[i];
                    int ny2 = cur.y2;
                    rotate((Queue<Robot>) q, r, cur, i, nx1, ny1, nx2, ny2);
                }
            } else {    // 세로방향일 떄 회전
                for (int i = 0; i < 2; i++) {
                    int nx1 = cur.x1;
                    int ny1 = cur.y1 + r[i];
                    int nx2 = cur.x2;
                    int ny2 = cur.y2 + r[i];
                    rotate((Queue<Robot>) q, r, cur, i, nx1, ny1, nx2, ny2);
                }
            }
            System.out.println();
        }


    }

    // 회전 이동 메소드
    private void rotate(Queue<Robot> q, int[] r, Robot cur, int i, int nx1, int ny1, int nx2, int ny2) {
        // 현재 방향과 반대방향으로 설정
        int direct = cur.direction == 0 ? 1 : 0;

        // 회전할 수 있는지를 검사
        if (checkIfCanRotate(cur.x1, cur.y1, cur.x2, cur.y2, r[i], cur.direction)) {
            if (!visited[nx1][ny1][direct] || !visited[cur.x1][cur.y1][direct]) {
                q.offer(new Robot(nx1, ny1, cur.x1, cur.y1, direct));

                visited[nx1][ny1][direct] = true;
                visited[cur.x1][cur.y1][direct] = true;

                if (dp[nx1][ny1][direct] == -1) {
                    dp[nx1][ny1][direct] = Math.max(dp[cur.x1][cur.y1][cur.direction], dp[cur.x2][cur.y2][cur.direction]) + 1;
                }
                if (dp[cur.x1][cur.y1][direct] == -1) {
                    dp[cur.x1][cur.y1][direct] = dp[cur.x1][cur.y1][cur.direction];
                }
            }

            if (!visited[nx2][ny2][direct] || !visited[cur.x2][cur.y2][direct]) {
                q.offer(new Robot(nx2, ny2, cur.x2, cur.y2, direct));

                visited[nx2][ny2][direct] = true;
                visited[cur.x2][cur.y2][direct] = true;

                if (dp[nx2][ny2][direct] == -1) {
                    dp[nx2][ny2][direct] = Math.max(dp[cur.x1][cur.y1][cur.direction], dp[cur.x2][cur.y2][cur.direction]) + 1;
                }
                if (dp[cur.x2][cur.y2][direct] == -1) {
                    dp[cur.x2][cur.y2][direct] = dp[cur.x2][cur.y2][cur.direction];
                }
            }
        }
    }

    // direction = 0: 가로방향, 1: 세로방향
    private class Robot {
        int x1, y1, x2, y2, direction;
        Robot(int x1, int y1, int x2, int y2, int direction) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.direction = direction;
        }
    }

    private boolean checkIfCanRotate(int x1, int y1, int x2, int y2, int next, int direction) {
        int nx1, ny1, nx2, ny2;
        // 가로방향이면, (x1, y1), (x2, y2) 기준 위아래로 벽이 없어야함.
        if (direction == 0) {
            nx1 = x1 + next;
            ny1 = y1;
            nx2 = x2 + next;
            ny2 = y2;
        } else {    // 세로방향이면, (x1, y1), (x2, y2) 기준 양옆으로 벽이 없어야함.
            nx1 = x1;
            ny1 = y1 + next;
            nx2 = x2;
            ny2 = y2 + next;
        }

        // 범위를 벗어나지 않는지 검사
        if (!checkInRange(nx1, ny1) || !checkInRange(nx2, ny2)) {
            return false;
        }
        // 벽이 있는지를 검사
        if (board[nx1][ny1] == 1 || board[nx2][ny2] == 1) {
            return false;
        }
        return true;
    }

    private boolean checkInRange(int x, int y) {
        // 범위를 벗어나는지를 검사
        if (x < 0 || x >= border || y < 0 || y >= border) {
            return false;
        }
        // 벽이 있는지를 검사
        if (board[x][y] == 1) {
            return false;
        }
        return true;
    }


    @Test
    public void test() {
        Assertions.assertEquals(7, solution(new int[][]{{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}}));
        Assertions.assertEquals(21, solution(new int[][]{{0, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 1, 1}, {0, 0, 1, 0, 0, 0, 0}}));
        Assertions.assertEquals(11, solution(new int[][]{{0, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 1, 0}, {0, 0, 1, 0, 0, 0, 0}}));
        Assertions.assertEquals(33, solution(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 1, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 0, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1, 0}}));
    }
}
