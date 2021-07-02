import java.io.*;
import java.util.*;

/**
 *  No.17071: 숨바꼭질 5
 *  Hint: 규칙이 있는 BFS
 *      동생이 k 를 홀수(짝수) 초에 방문했을 때, 수빈이가 k를 홀수(짝수) 초에 방문한 적이 있었다면,
 *      둘은 서로 만날 수 있다.
 */

public class BOJ17071 {
    static int n, k;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        visited = new boolean[2][500001];

        bw.write(n == k ? "0" : String.valueOf(bfs()));
        bw.close();
        br.close();
    }

    static int bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(n);
        visited[0][n] = true;   // visited[0][] == 짝수 초, visited[1][] == 홀수 초

        int size, mod, time = 0;
        while (!q.isEmpty()) {
            size = q.size();
            time++;
            mod = time % 2;

            k += time;
            if (k > 500000) {
                return -1;
            }

            for (int i = 0; i < size; i++) {
                int cur = q.poll();

                if (cur - 1 >= 0 && !visited[mod][cur - 1]) {
                    q.offer(cur - 1);
                    visited[mod][cur - 1] = true;
                }

                if (cur + 1 <= 500000 && !visited[mod][cur + 1]) {
                    q.offer(cur + 1);
                    visited[mod][cur + 1] = true;
                }

                if (cur * 2 <= 500000 && !visited[mod][cur * 2]) {
                    q.offer(cur * 2);
                    visited[mod][cur * 2] = true;
                }
            }

            if (visited[mod][k]) {
                return time;
            }
        }
        return -1;
    }
}


/**
 *  시간초과 코드 71% TLE
 *
public class BOJ17071 {
    static int n, k, ans = -1;
    static boolean[] visited;
    static boolean flag;
    static Queue<Integer> startPoints = new ArrayDeque<>();
    static HashSet<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        visited = new boolean[Math.max(n, k) + 1];

        int time = 0;
        startPoints.offer(n);
        while (!flag && k <= 500000) {
            bfs(time++);
            k += time;
        }

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    static void bfs(int time) {
        startPoints.addAll(set);
        set.clear();

        while (!startPoints.isEmpty()) {
            int cur = startPoints.poll();
            if (cur == k) {
                ans = time;
                flag = true;
                return;
            }

            for (int i = 0; i < 3; i++) {
                int nx = cur;
                switch (i) {
                    case 0:
                        nx += -1;
                        break;
                    case 1:
                        nx += 1;
                        break;
                    case 2:
                        nx *= 2;
                        break;
                }

                if (isInRange(nx)) {
                    set.add(nx);
                }
            }
        }
    }

    static boolean isInRange(int x) {
        if (x >= 0 && x <= 500000) {
            return true;
        }
        return false;
    }
}
**/