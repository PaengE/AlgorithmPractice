import java.io.*;

/**
 *  No.5582: 공통 부분 문자열
 *  URL: https://www.acmicpc.net/problem/5582
 *  Hint: dp[i][j] = s1의 0~i 문자열과 s2의 0~j 문자열의 공통 부분 문자열 개수
 */

public class BOJ5582 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s1 = br.readLine();
        String s2 = br.readLine();

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }
}
