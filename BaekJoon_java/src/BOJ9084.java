import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *  No.9084: 동전
 *  URL: https://www.acmicpc.net/problem/9084
 *  Hint: DP
 */

public class BOJ9084 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            ArrayList<Integer> list = new ArrayList<>();
            int m = Integer.parseInt(br.readLine());
            int[] dp = new int[m + 1];

            for (int j = 0; j < n; j++) {
                int a = Integer.parseInt(st.nextToken());
                list.add(a);
            }

            dp[0] = 1;
            for (int tmp : list) {
                for (int j = tmp; j <= m; j++) {
                    dp[j] += dp[j - tmp];
                }
            }
            sb.append(dp[m]).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
