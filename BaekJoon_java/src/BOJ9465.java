import java.io.*;
import java.util.StringTokenizer;

/**
 * No.9465: 스티커
 * URL: https://www.acmicpc.net/problem/9465
 * Hint: dp[i][j] = max(max(dp[0][j-2], dp[1][j-2]), dp[(i + 1) % 2][j-1]) + sticker[i][j]
 */

public class BOJ9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[][] sticker = new int[2][n];
            int[][] dp = new int[2][n];

            for (int j = 0; j < 2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][0] = sticker[0][0];
            dp[1][0] = sticker[1][0];
            int preValue = 0;
            for (int j = 1; j < n; j++) {
                for (int k = 0; k < 2; k++) {
                    if (j - 2 >= 0) {
                        preValue = Math.max(dp[0][j - 2], dp[1][j - 2]);
                    }

                    dp[k][j] = Math.max(dp[(k + 1) % 2][j - 1], preValue) + sticker[k][j];
                }
            }

            int answer = Math.max(dp[0][n - 1], dp[1][n - 1]);
            bw.write(String.valueOf(answer));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
