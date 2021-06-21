import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  No.16985: Maaaaaaaaaze
 *  Hint: BruteForce 순열 + Backtracking + BFS + 구현 + 배열 복사
 */

public class BOJ16985 {
    static final int size = 5;
    static int ans = Integer.MAX_VALUE;
    static boolean[][][] map = new boolean[size][size][size];
    static boolean[][][] copy = new boolean[size][size][size];
    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static int[] res = new int[5];
    static boolean[] used = new boolean[5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < size; k++) {
                    int t = Integer.parseInt(st.nextToken());
                    if (t == 1) {
                        map[i][j][k] = true;
                        copy[i][j][k] = true;
                    }
                }
            }
        }

        Ordering(0);

        bw.write(ans == Integer.MAX_VALUE ? "-1" : String.valueOf(ans));
        bw.close();
        br.close();
    }

    static void backtracking(int depth) {
        if (depth == size) {
            bfs(0, 0, 0);
            return;
        }

        // 시계 방향 0도 회전 후 backtracking
        backtracking(depth + 1);

        // 시계 방향 90도 회전 후 backtracking
        rotateArray(depth);
        backtracking(depth + 1);

        // 시계 방향 180도 회전 후 backtracking
        rotateArray(depth);
        backtracking(depth + 1);

        // 시계 방향 270도 회전 후 backtracking
        rotateArray(depth);
        backtracking(depth + 1);

        // 미로 원상복구
        rotateArray(depth);
    }

    static void bfs(int x, int y, int z) {
        if (!copy[x][y][z] || !copy[size - 1][size - 1][size - 1]) {    // 시작 지점이나 끝 지점이 막혀 있으면
            return;
        }

        Queue<Point> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[size][size][size];
        q.offer(new Point(x, y, z, 0));
        visited[x][y][z] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            // 끝 지점에 도착 했으면 ans를 최솟값으로 갱신
            if (cur.x == size - 1 && cur.y == size - 1 && cur.z == size - 1) {
                ans = Math.min(ans, cur.cnt);
                return;
            }

            for (int i = 0; i < 6; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nz = cur.z + dz[i];

                if (isInRange(nx, ny, nz) && copy[nx][ny][nz] && !visited[nx][ny][nz]) {
                    q.offer(new Point(nx, ny, nz, cur.cnt + 1));
                    visited[nx][ny][nz] = true;
                }
            }
        }
    }

    // maze 쌓기 순서
    static void Ordering(int depth) {
        if (depth == size) {
            buildMaze();
            backtracking(0);
            return;
        }

        for (int i = 0; i < size; i++) {
            if (!used[i]) {
                used[i] = true;
                res[depth] = i;
                Ordering(depth + 1);
                res[depth] = 0;
                used[i] = false;
            }
        }
    }

    // maze 쌓기
    static void buildMaze() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    copy[i][j][k] = map[res[i]][j][k];
                }
            }
        }
    }

    // 시계 방향 90도 회전
    static void rotateArray(int layer) {
        boolean[][] copied = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copied[i][j] = copy[layer][size - 1 - j][i];
            }
        }
        System.arraycopy(copied, 0, copy[layer], 0, size);
    }

    // 미로 범위 검사
    static boolean isInRange(int x, int y, int z) {
        if (x >= 0 && x < size && y >= 0 && y < size && z >= 0 && z < size) {
            return true;
        }
        return false;
    }

    static class Point {
        int x, y, z, cnt;

        Point(int x, int y, int z, int cnt) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.cnt = cnt;
        }
    }
}
