import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.1495: 기타리스트
 *  URL: https://www.acmicpc.net/problem/1495
 */

public class BOJ1495 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] dp = new boolean[n + 1][m + 1];

        dp[0][s] = true;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int v = Integer.parseInt(st.nextToken());

            for (int j = 0; j <= m; j++) {
                if (dp[i - 1][j]) {
                    if (j - v >= 0) {
                        dp[i][j - v] = true;
                    }

                    if (j + v <= m) {
                        dp[i][j + v] = true;
                    }
                }
            }
        }

        for (int i = m; i >= 0; i--) {
            if (dp[n][i]) {
                bw.write(String.valueOf(i));
                bw.close();
                br.close();
                System.exit(0);
            }
        }
        bw.write("-1");
        bw.close();
        br.close();
    }
}
