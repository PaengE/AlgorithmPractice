import java.io.*;

/**
 *  No.15988: 1, 2, 3 더하기 3
 *  URL: https://www.acmicpc.net/problem/15988
 */

public class BOJ15988 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            long[] dp = new long[n + 3];

            dp[0] = 1;
            dp[1] = 2;
            dp[2] = 4;
            for (int i = 3; i < n; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000009;
            }
            sb.append(dp[n - 1]).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
