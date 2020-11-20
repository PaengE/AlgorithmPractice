import java.io.*;

/**
 * No.1699: 제곱수의 합
 * URL: https://www.acmicpc.net/problem/1699
 * Hint: dp[i] = min(dp[i], dp[i - j^2])    (j^2 <= i, j = 1, 2, ...)
 */

public class BOJ1699 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[100001];

        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 2; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

//        for (int i = 1; i <= n; i++) {
//            System.out.println("dp["+i+"] = " + dp[i]);
//        }

        bw.write(String.valueOf(dp[n]));
        bw.flush();
        br.close();
        bw.close();

    }
}
