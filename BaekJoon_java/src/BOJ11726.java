import java.io.*;

/**
 * No.11726: 2 x n 타일링
 * URL: https://www.acmicpc.net/problem/11726
 * Hint: dp[i] = dp[i - 1] + dp[i - 2]
 */

public class BOJ11726 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];

        if (n <= 2) {
            bw.write(String.valueOf(n));
        } else {
            dp[1] = 1;
            dp[2] = 2;

            for (int i = 3; i <= n; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
            }

            bw.write(String.valueOf(dp[n]));
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
