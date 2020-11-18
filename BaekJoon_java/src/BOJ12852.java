import java.io.*;

/**
 * No.12852: 1로 만들기 2
 * Description: 1로 만드는 최적해를 출력하는 문제
 * URL: https://www.acmicpc.net/problem/12852
 * Hint: 최적 해로 온 길을 저장함
 */

public class BOJ12852 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n + 1][2];

        if (n <= 3) {
            bw.write("1\n");
            bw.write(String.valueOf(n) + " 1\n");
        } else {
            dp[2][0] = 1; dp[2][1] = 1;
            dp[3][0] = 1; dp[3][1] = 1;

            for (int i = 4; i <= n; i++) {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = i - 1;

                if (i % 3 == 0) {
                    if (dp[i / 3][0] + 1 < dp[i][0]) {
                        dp[i][0] = dp[i / 3][0] + 1;
                        dp[i][1] = i / 3;
                    }
                }

                if (i % 2 == 0) {
                    if (dp[i / 2][0] + 1 < dp[i][0]) {
                        dp[i][0] = dp[i / 2][0] + 1;
                        dp[i][1] = i / 2;
                    }
                }
            }

            bw.write(String.valueOf(dp[n][0]));
            bw.newLine();
            bw.write(String.valueOf(n) + " ");

            while (dp[n][1] != 0) {
                bw.write(String.valueOf(dp[n][1] + " "));
                n = dp[n][1];
            }
        }


        bw.flush();
        bw.close();
        br.close();
    }
}
