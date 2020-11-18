import java.io.*;

/**
 * No.9095: 1, 2, 3 더하기
 * URL: https://www.acmicpc.net/problem/9095
 * Hint: dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]
 */

public class BOJ9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        int[] dp = new int[11];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;


        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            for (int j = 3; j < n; j++) {
                if (dp[j] == 0) {
                    dp[j] = dp[j - 3] + dp[j - 2] + dp[j - 1];
                }
            }
            bw.write(String.valueOf(dp[n - 1]) + "\n");

        }
        bw.flush();
        br.close();
        bw.close();
    }
}
