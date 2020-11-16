import java.io.*;

/**
 * No.11727: 2 x n 타일링 2
 * URL: https://www.acmicpc.net/problem/11727
 * Hint: dp[i] = 2 * dp[i - 2] + dp[i - 1]
 */

public class BOJ11727 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        if (n == 1) {
            bw.write(String.valueOf(1));
        } else if (n == 2) {
            bw.write(String.valueOf(3));
        } else {
            int[] dp = new int[n];
            dp[0] = 1;
            dp[1] = 3;
            for (int i = 2; i < n; i++) {
                dp[i] = (2 * dp[i - 2] + dp[i - 1]) % 10007;
            }

            bw.write(String.valueOf(dp[n - 1]));
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
