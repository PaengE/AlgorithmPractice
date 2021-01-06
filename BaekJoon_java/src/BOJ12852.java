import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * No.12852: 1로 만들기 2
 * Description: 1로 만드는 최적해를 출력하는 문제
 * URL: https://www.acmicpc.net/problem/12852
 * Hint: 최적 해로 온 길을 저장함
 */

public class BOJ12852 {
    static boolean[] visited;
    static int[] pre, dp;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];
        dp = new int[n + 1];
        pre = new int[n + 1];

        bfs(1);

        bw.write(dp[n] + "\n");

        StringBuilder sb = new StringBuilder();
        while (pre[n] != -1) {
            sb.append(n + " ");
            n = pre[n];
        }
        bw.write(sb.append(1).toString());

        bw.flush();
        bw.close();
        br.close();
    }

    // 1부터 n까지 가는 최소비용을 구함
    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
        pre[start] = -1;
        dp[start] = 0;

        // 큐에서 하나씩 뺀 후 bfs 진행
        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur > n) {
                continue;
            }

            if (cur == n) {
                break;
            }

            // 범위를 벗어나는지, 방문했었는지를 검사후 큐에 삽입
            if (cur * 3 <= n && !visited[cur * 3]) {
                q.offer(cur * 3);
                pre[cur * 3] = cur;
                dp[cur * 3] = dp[cur] + 1;
                visited[cur * 3] = true;
            }
            if (cur * 2 <= n && !visited[cur * 2]) {
                q.offer(cur * 2);
                pre[cur * 2] = cur;
                dp[cur * 2] = dp[cur] + 1;
                visited[cur * 2] = true;
            }
            if (cur + 1 <= n && !visited[cur + 1]) {
                q.offer(cur + 1);
                pre[cur + 1] = cur;
                dp[cur + 1] = dp[cur] + 1;
                visited[cur + 1] = true;
            }
        }
    }
}

/*      재채점 이후 맞았습니다. -> 틀렸습니다. 코드

        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n + 1][2];

        if (n <= 3) {
            bw.write("1\n");
            bw.write(String.valueOf(n) + " 1\n");
        } else {
            dp[2][0] = 1; dp[2][1] = 1;
            dp[3][0] = 1; dp[3][1] = 1;

            for (int i = 4; i <= n; i++) {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = i - 1;

                if (i % 3 == 0) {
                    if (dp[i / 3][0] + 1 < dp[i][0]) {
                        dp[i][0] = dp[i / 3][0] + 1;
                        dp[i][1] = i / 3;
                    }
                }

                if (i % 2 == 0) {
                    if (dp[i / 2][0] + 1 < dp[i][0]) {
                        dp[i][0] = dp[i / 2][0] + 1;
                        dp[i][1] = i / 2;
                    }
                }
            }

            bw.write(String.valueOf(dp[n][0]));
            bw.newLine();
            bw.write(String.valueOf(n) + " ");

            while (dp[n][1] != 0) {
                bw.write(String.valueOf(dp[n][1] + " "));
                n = dp[n][1];
            }
        }


 */