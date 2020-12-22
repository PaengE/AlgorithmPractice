import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.11660: 구간 합 구하기 5
 *  Hint: DP
 */

public class BOJ11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] dp = new int[n + 1][n + 1];

        dp[1][1] = arr[1][1];
        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= n; j++) {
                if (i == 1) {
                    dp[i][j] = dp[i][j - 1] + arr[i][j];
                    dp[j][i] = dp[j - 1][i] + arr[j][i];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + arr[i][j];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int ans = dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1];

            sb.append(ans + "\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
