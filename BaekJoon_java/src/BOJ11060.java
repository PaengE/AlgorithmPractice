import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  No.11060: 점프 점프
 *  URL: https://www.acmicpc.net/problem/11060
 */

public class BOJ11060 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 10000);

        dp[0] = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 1; j <= arr[i]; j++) {
                if (i + j < n) {
                    dp[i + j] = Math.min(dp[i] + 1, dp[i + j]);
                }
            }
        }

        if (dp[n - 1] == 10000) {
            bw.write("-1");
        } else {
            bw.write(String.valueOf(dp[n - 1]));
        }
        bw.close();
        br.close();
    }
}
