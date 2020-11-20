import java.io.*;
import java.util.StringTokenizer;

/**
 * No.11055: 가장 큰 증가 부분 수열
 * URL: https://www.acmicpc.net/problem/11055
 * Hint: Dynamic Programming
 */

public class BOJ11055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        int[] dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
            dp[i] = num[i];
        }

        int maxValue = dp[0];
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (num[i] > num[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + num[i]);
                    maxValue = Math.max(maxValue, dp[i]);
                }
            }
        }

//        for (int i = 0; i < n; i++) {
//            System.out.println("dp["+i+"] = " + dp[i]);
//        }

        bw.write(String.valueOf(maxValue));
        bw.flush();
        bw.close();
        br.close();
    }
}
