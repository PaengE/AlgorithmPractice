import java.io.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16236 {
    static int n, nx = 0, ny = 0, fishSize = 2, eatCount = 0;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static PriorityQueue<Shark> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        // 가장 위에있는거 먼저, 그리고 가장 왼쪽에 있는거 먼저 (같은 거리에서)
        pq = new PriorityQueue<>(((o1, o2) -> { // pq는 가장 가까운 거리의 물고기들의 좌표리스트
            if (o1.x == o2.x) {
                return Integer.compare(o1.y, o2.y);
            }
            return Integer.compare(o1.x, o2.x);
        }));

        // map 초기화, 시작점 nx ny 초기화
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    map[i][j] = 0;
                    nx = i;
                    ny = j;
                }
            }
        }

        int ans = 0;
        while (true) {
            int d = bfs();
            if (d == 0) {
                break;
            }

            ans += d;
        }

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();

    }

    // 가장 가까운 거리를 찾고, 시작점에서 그만큼 떨어진 포인트는 모두 검사한 후,
    // 가장 위쪽, 왼쪽에 있는 포인트를 다음 시작점으로 정함
    static int bfs() {
        Queue<Shark> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        visited[nx][ny] = true;
        pq.clear();
        q.offer(new Shark(nx, ny, 0));

        int t = -1;
        while (!q.isEmpty()) {
            Shark cur = q.poll();

            // 먹을 수 있는 물고기를 찾으면
            if (map[cur.x][cur.y] != 0 && map[cur.x][cur.y] < fishSize) {
                // 가장 가까운 거리를 갱신하고, 가장 가까운 거리에 위치한 다른 물고기들이 있는지 탐색
                if (t == -1) {
                    t = cur.distance;
                    pq.offer(cur);
                } else if (t == cur.distance) {
                    pq.offer(cur);
                }
                continue;
            }

            for (int i = 0; i < 4; i++) {
                nx = cur.x + dx[i];
                ny = cur.y + dy[i];

                if (isRange(nx, ny) && !visited[nx][ny] && map[nx][ny] <= fishSize) {
                    q.offer(new Shark(nx, ny, cur.distance + 1));
                    visited[nx][ny] = true;
                }
            }
        }

        // 먹을 수 있는 물고기들이 없으면
        if (pq.isEmpty()) {
            return 0;
        } else {    // 먹을 수 있는 물고기들이 있으면
            // 먹은 후 eatCount++, fishSize 재조정, 시작점으로부터 거리 반환, 시작점 재조정
            Shark next = pq.poll();
            nx = next.x;
            ny = next.y;
            map[nx][ny] = 0;
            eatCount++;
            if (eatCount == fishSize) {
                fishSize++;
                eatCount = 0;
            }
            return next.distance;
        }
    }

    // 범위 검사
    static boolean isRange(int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < n) {
            return true;
        } else {
            return false;
        }
    }

    static class Shark {
        int x, y, distance;

        Shark(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
