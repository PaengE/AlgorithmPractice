import java.io.*;
import java.util.StringTokenizer;

/**
 * No.1010: 다리놓기
 * URL: https://www.acmicpc.net/problem/1010
 * Hint: dp[i][j] = dp[i-1][j-1] + dp[i][j-1]
 */

public class BOJ1010 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] dp = new int[n][m];

            for (int j = 0; j < m; j++) {
                dp[0][j] = j + 1;
            }
            for (int j = 0; j < n; j++) {
                dp[j][j] = 1;
            }

            for (int j = 1; j < n; j++) {
                for (int k = j; k < m; k++) {
                    dp[j][k] = dp[j - 1][k - 1] + dp[j][k - 1];
                }
            }

            bw.write(String.valueOf(dp[n-1][m-1]));
            bw.newLine();
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
