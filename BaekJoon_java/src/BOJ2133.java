import java.io.*;

/**
 *  No.2133: 타일 채우기
 *  URL: https://www.acmicpc.net/problem/2133
 *  Hint: dp [n] = dp [n-2] * 3 + dp [n-4] *2 + dp [n-6] * 2....
 */

public class BOJ2133 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        if (n % 2 == 1) {
            bw.write("0");
        } else {
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[2] = 3;

            for (int i = 4; i <= n; i += 2) {
                dp[i] = dp[i - 2] * 3;
                for (int j = 0; j < i - 2; j += 2) {
                    dp[i] += dp[j] * 2;
                }
            }
            bw.write(String.valueOf(dp[n]));
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
