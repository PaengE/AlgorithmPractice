import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.5557: 1학년
 *  URL: https://www.acmicpc.net/problem/5557
 *  Hint: DP
 */

public class BOJ5557 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        long[][] dp = new long[n - 1][21];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0][arr[0]] = 1;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 0; j < 21; j++) {
                if (dp[i - 1][j] != 0) {
                    if (j + arr[i] <= 20) {
                        dp[i][j + arr[i]] += dp[i - 1][j];
                    }

                    if (j - arr[i] >= 0) {
                        dp[i][j - arr[i]] += dp[i - 1][j];
                    }
                }
            }
        }

        bw.write(String.valueOf(dp[n - 2][arr[n - 1]]));
        bw.close();
        br.close();
    }
}
