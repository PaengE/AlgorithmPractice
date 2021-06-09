import java.io.*;
import java.util.*;

/**
 *  No.1981: 배열에서 이동
 *  Hint: 이분탐색 + BFS
 */

public class BOJ1981 {
    static int n, ans, maxNum, minNum = 200;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0,};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                maxNum = Math.max(maxNum, arr[i][j]);
                minNum = Math.min(minNum, arr[i][j]);
            }
        }

        // 정답이 될 수 있는 후보 중 최솟값은 arr의 모든 값이 같을 때이고, 최댓값은 arr의 최댓값 - 최솟값이다.
        binarySearch(0, maxNum - minNum);

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    // 이분탐색으로 (최대 - 최소) 값을 찾음
    static void binarySearch(int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;

            if (whetherItCanBeReached(mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }

    // diff 값으로 (n-1, n-1)에 도달할 수 있는지를 체크
    static boolean whetherItCanBeReached(int diff) {
        // (s - e) 가 diff 인 모든 구간을 시도
        for (int i = minNum; i + diff <= maxNum; i++) {
            int s = i;
            int e = s + diff;

            // 시작지점 검사
            if (s > arr[0][0] || arr[0][0] > e) {
                continue;
            }

            Queue<Point> q = new ArrayDeque<>();
            boolean[][] visited = new boolean[n][n];
            q.offer(new Point(0, 0));
            visited[0][0] = true;

            while (!q.isEmpty()) {
                Point cur = q.poll();

                if (cur.x == n - 1 && cur.y == n - 1) {
                    return true;
                }

                for (int j = 0; j < 4; j++) {
                    int nx = cur.x + dx[j];
                    int ny = cur.y + dy[j];

                    // s ~ e 사이에 arr[nx][ny] 값이 존재하면 이동할 수 있음
                    if (inRange(nx, ny) && !visited[nx][ny] && s <= arr[nx][ny] && arr[nx][ny] <= e){
                        q.offer(new Point(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        return false;
    }

    static boolean inRange(int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < n) {
            return true;
        }
        return false;
    }

    static class Point{
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
