import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.17070: 파이프 옮기기 1
 *  URL: https://www.acmicpc.net/problem/17070
 *  Hint: DP
 */

public class BOJ17070 {
    static int n;
    static int[][][] dp;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][n][3];  // 1: 가로, 2: 대각선, 3: 세로

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][1][0] = 1;
        int res = dp();

        bw.write(String.valueOf(res));
        bw.close();
        br.close();
    }

    static int dp() {
        // 파이프를 놓을 수 있는 것은 2열부터임
        for (int i = 0; i < n; i++) {
            for (int j = 2; j < n; j++) {
                // 길이 막혀있으면 continue
                if (map[i][j] == 1) {
                    continue;
                }

                // 가로 방향 파이프의 끝이 (i, j)인 경우의 수
                dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];

                // 맨 윗 줄일 경우 continue
                if (i == 0) {
                    continue;
                }

                // 세로 방향 파이프의 끝이 (i, j)인 경우의 수
                dp[i][j][2] = dp[i - 1][j][1] + dp[i - 1][j][2];

                // (i, j) 기준 왼쪽, 위쪽이 막혀있으면 continue
                if (map[i - 1][j] == 1 || map[i][j - 1] == 1) {
                    continue;
                }

                // 대각선 방향 파이프의 끝이 (i, j)인 경우의 수
                dp[i][j][1] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
            }
        }

        return dp[n - 1][n - 1][0] + dp[n - 1][n - 1][1] + dp[n - 1][n - 1][2];
    }
}
