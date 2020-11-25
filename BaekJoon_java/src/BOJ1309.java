import java.io.*;
import java.util.Arrays;
/**
 *  No.1309: 동물원
 *  URL: https://www.acmicpc.net/problem/1309
 *  Hint: dp[i][0] = dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]
 *        dp[i][1] = dp[i - 1][0] + dp[i - 1][2]
 *        dp[i][2] = dp[i - 1][0] + dp[i - 1][1]
 *        
 *        dp[n][0] -> 두 개의 방 중에 사자를 아예 넣지 않은 경우
 *        dp[n][1] -> 두 개의 방 중에 사자를 왼쪽 방에 넣은 경우
 *        dp[n][2] -> 두 개의 방 중에 사자를 오른쪽 방에 넣은 경우
 */

public class BOJ1309 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n][3];
        Arrays.fill(dp[0], 1);

        for (int i = 1; i < n; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % 9901;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % 9901;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % 9901;
        }

        int sum = dp[n - 1][0] + dp[n - 1][1] + dp[n - 1][2];
        bw.write(String.valueOf(sum));
        bw.flush();
        br.close();
        bw.close();
    }
}
