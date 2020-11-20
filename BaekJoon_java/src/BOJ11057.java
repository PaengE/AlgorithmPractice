import java.io.*;
import java.util.Arrays;

/**
 * No.11057: 오르막 수
 * URL: https://www.acmicpc.net/problem/11057
 * Hint: dp[i][j] = dp[i][j] + dp[i-1][j...9]
 */

public class BOJ11057 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n][10];

        Arrays.fill(dp[0], 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = j; k < 10; k++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][k]) % 10007;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < 10; i++) {
            answer = (answer + dp[n - 1][i]) % 10007;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        br.close();
        bw.close();
    }
}
