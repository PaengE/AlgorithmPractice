import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.17069: 파이프 옮기기 2
 *  Hint: DP + 자료형
 */

public class BOJ17069 {
    static int n;
    static long[][][] dp;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        dp = new long[n][n][3];   // 0:가로, 1:세로, 2:대각선

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][1][0] = 1;

        bw.write(String.valueOf(dp()));
        bw.close();
        br.close();
    }

    static long dp() {
        for (int i = 0; i < n; i++) {
            for (int j = 2; j < n; j++) {
                if (arr[i][j] == 1) {   // 막혀 있으면 continue
                    continue;
                }

                dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];    // 가로방향

                if (i == 0) {   // 맨 윗줄이면 continue
                    continue;
                }

                dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];    // 세로방향

                if (arr[i - 1][j] == 1 || arr[i][j - 1] == 1) {
                    continue;
                }

                dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];  // 대각선 방향
            }
        }

        return dp[n - 1][n - 1][0] + dp[n - 1][n - 1][1] + dp[n - 1][n - 1][2];
    }
}
