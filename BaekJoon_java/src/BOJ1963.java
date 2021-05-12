import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  No.1963: 소수 경로
 *  URL: https://www.acmicpc.net/problem/1963
 *  Hint: 에라토스테네스의 체 + BFS
 */

public class BOJ1963 {
    static boolean[] prime = new boolean[10000];    // false가 소수
    static boolean[] visited;
    static int[] cnt;
    static int[] d = {1000, 100, 10, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        checkPrimeNumber();

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            visited = new boolean[10000];
            cnt = new int[10000];

            bfs(start, end);

            if (!visited[end]) {
                sb.append("Impossible\n");
            } else {
                sb.append(cnt[end] + "\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void bfs(int start, int end) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            // end에 도달하면 종료
            if (cur == end) {
                return;
            }

            for (int i = 0; i < 4; i++) {
                int val = cur / d[i] / 10;  // 바뀔 숫자 왼쪽 부분
                int mod = cur % d[i];       // 바뀔 숫자 오른쪽 부분

                for (int j = 0; j <= 9; j++) {
                    if (i == 0 && j == 0) { // 천의 자리가 0으로 시작하면 안되므로
                        continue;
                    }

                    int next = (val * d[i] * 10) + (j * d[i]) + mod;
                    if (!visited[next] && !prime[next]) {   // 다음 숫자를 방문하지 않았고, 소수이면 큐에 추가
                        q.offer(next);
                        visited[next] = true;
                        cnt[next] = cnt[cur] + 1;
                    }
                }
            }
        }
    }

    // 에라토스테네스의 체를 이용하여 1 ~ 10000 까지 소수판별
    static void checkPrimeNumber() {
        prime[0] = prime[1] = true;

        for (int i = 2; i * i < 10000; i++) {
            if (!prime[i]) {    // i가 소수라면
                for (int j = i * i; j < 10000; j += i) {
                    prime[j] = true;
                }
            }
        }
    }
}
