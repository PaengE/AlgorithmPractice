import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.2225: 합분해
 *  URL: https://www.acmicpc.net/problem/2225
 *  Hint: dp[k][n] = dp[k-1][0] + ... + dp[k-1][n]
 *     => dp[k][n] = dp[k-1][n] + dp[k][n-1]
 */

public class BOJ2225 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] dp = new int[k + 1][n + 1];

        for (int i = 1; i <= k; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % 1000000000;
            }
        }

        bw.write(String.valueOf(dp[k][n]));
        bw.flush();
        br.close();
        bw.close();
    }
}
