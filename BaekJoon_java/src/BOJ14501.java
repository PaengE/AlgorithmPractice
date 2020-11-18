import java.io.*;
import java.util.StringTokenizer;

/**
 * No.14501: 퇴사
 * URL: https://www.acmicpc.net/problem/14501
 * Hint: DP 사용
 */

public class BOJ14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n+1][2];
        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());

        }

        int maxPrice = 0;
        for (int i = 0; i <= n; i++) {
            int t = arr[i][0];
            int p = arr[i][1];

            dp[i] = Math.max(dp[i], maxPrice);

            if (i + t < n + 1) {
                dp[i + t] = Math.max(dp[i] + p, dp[i + t]);
            }

            maxPrice = Math.max(maxPrice, dp[i]);
        }

//        for (int i = 1; i <=  n; i++) {
//            System.out.print(dp[i] + " ");
//        }
//        System.out.println();

        bw.write(String.valueOf(maxPrice));
        bw.flush();
        br.close();
        bw.close();
    }
}
