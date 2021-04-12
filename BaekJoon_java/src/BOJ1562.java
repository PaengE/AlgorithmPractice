import java.io.*;

/**
 *  No.1562: 계단 수
 *  URL: https://www.acmicpc.net/problem/1562
 *  Hint: Bitmasking DP
 */

public class BOJ1562 {
    static int n;
    static long[][][] dp;
    static final int MOD = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        dp = new long[n + 1][10][1 << 10];

        bw.write(String.valueOf(calc()));
        bw.close();
        br.close();
    }

    static long calc() {
        long sum = 0;

        // 1자리 수는 모두 계단 수
        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }

        // n자리 계단수의 개수는 n - 1자리 계단수에 숫자를 붙인 것
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k < (1 << 10); k++) {
                    if (j == 0) {
                        dp[i][0][k | 1 << 0] = (dp[i][0][k | 1 << 0] + dp[i - 1][1][k]) % MOD;
                    } else if (j == 9) {
                        dp[i][9][k | 1 << 9] = (dp[i][9][k | 1 << 9] + dp[i - 1][8][k]) % MOD;
                    } else {
                        dp[i][j][k | 1 << j] = (dp[i][j][k | 1 << j] + dp[i - 1][j - 1][k] + dp[i - 1][j + 1][k]) % MOD;
                    }
                }
            }
        }

        // n자리 계단수의 개수 중 0~9가 모두 쓰인 수를 더함
        for (int i = 0; i <= 9; i++) {
            sum = (sum + dp[n][i][(1 << 10) - 1]) % MOD;
        }

        return sum;
    }
}
