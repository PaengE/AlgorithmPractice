import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.10164: 격자상의 경로
 *  URL: https://www.acmicpc.net/problem/10164
 *  Hint: DP
 */

public class BOJ10164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n][m];



        if (k == 0) {
            for (int i = 0; i < m; i++) {
                dp[0][i] = 1;
            }
            for (int i = 0; i < n; i++) {
                dp[i][0] = 1;
            }

            for (int i = 1; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        } else {
            int x = (k - 1) / m;
            int y = (k - 1) % m;
            for (int i = 0; i <= y; i++) {
                dp[0][i] = 1;
            }
            for (int i = 0; i <= x; i++) {
                dp[i][0] = 1;
            }

            for (int i = 1; i <= x; i++) {
                for (int j = 1; j <= y; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }

            for (int i = x; i < n; i++) {
                dp[i][y] = dp[x][y];
            }
            for (int i = y; i < m; i++) {
                dp[x][i] = dp[x][y];
            }
            for (int i = x + 1; i < n; i++) {
                for (int j = y + 1; j < m; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        bw.write(String.valueOf(dp[n - 1][m - 1]));
        bw.close();
        br.close();
    }
}
