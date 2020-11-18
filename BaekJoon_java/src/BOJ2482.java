import java.io.*;

/**
 * No.2482: 색상환
 * Description: 원에서 인접하지 않게 색을 선택하는 문제
 * URL: https://www.acmicpc.net/problem/2482
 * Hint: dp[i][j] 는 i개의 색상 중 j개를 고른 것을 의미
 *       dp[i][j] = dp[i - 2][j - 1] + dp[i - 1][j] (i - 2개 색상 중 j - 1개를 고른 것 + i - 1개 색상 중 j 개를 고른 것)
 */

public class BOJ2482 {
    static final long MOD = 1000000003;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        long answer = dp(n, k);

        bw.write(String.valueOf(answer));
        bw.flush();

        br.close();
        bw.close();
    }

    static long dp(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];

        if (n / k < 2) {
            return 0;
        }

        for (int i = 1; i <= n; i++) {
            dp[i][1] = i;
        }

        for (int i = 4; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                dp[i][j] = (dp[i - 2][j - 1] + dp[i - 1][j]) % MOD;
            }
        }
        return dp[n][k];
    }
}
