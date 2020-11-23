import java.io.*;
import java.util.StringTokenizer;

/**
 * No.11048: 이동하기
 * URL: https://www.acmicpc.net/problem/11048
 * Hint: DP
 */

public class BOJ11048 {
    static int[][] dp, maze;
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        maze = new int[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = maze[0][0];
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + maze[0][i];
        }
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + maze[i][0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + maze[i][j];
            }
        }
        bw.write(String.valueOf(dp[n - 1][m - 1]));
        bw.flush();
        br.close();
        bw.close();
    }
}
