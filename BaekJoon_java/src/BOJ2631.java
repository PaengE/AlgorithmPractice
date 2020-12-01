import java.io.*;
import java.util.Arrays;

/**
 *  No.2631: 줄세우기
 *  URL: https://www.acmicpc.net/problem/2631
 *  Hint: LCS + DP
 */

public class BOJ2631 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int length = 1;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    length = Math.max(length, dp[i]);
                }
            }
        }

        bw.write(String.valueOf(n - length));
        bw.close();
        br.close();
    }
}
