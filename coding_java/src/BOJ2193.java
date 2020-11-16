import java.io.*;
/**
 * No.2193: 이친수
 * URL: https://www.acmicpc.net/problem/2193
 * Hint: 5자리 이친수의 개수 = 3자리 이친수에 01을 붙인 개수 + 4자리 이친수에 0을 붙인 개수
 */

public class BOJ2193 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        if (n <= 2) {
            bw.write(String.valueOf(1));
        } else {
            long[] dp = new long[n];
            dp[0] = 1;
            dp[1] = 1;

            for (int i = 2; i < n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }

            bw.write(String.valueOf(dp[n - 1]));
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
