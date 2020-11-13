import java.io.*;
import java.util.StringTokenizer;

/**
 * No.17404: RGB 거리 2
 * Description: 집이 원형으로 배열된 RGB 거리 문제
 * Hint: 시작점을 지정하여 DP
 */

public class BOJ17404 {
    static long[][] arr, dp;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new long[n][3];

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long answer = Integer.MAX_VALUE;
        answer = Math.min(answer, dp(0));
        answer = Math.min(answer, dp(1));
        answer = Math.min(answer, dp(2));

        bw.write(String.valueOf(answer));
        bw.flush();

        br.close();
        bw.close();
    }
    static long dp(int start){
        dp = new long[n][3];

        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];

        dp[1][start] = Integer.MAX_VALUE;
        dp[1][(start + 1) % 3] = dp[0][start] + arr[1][(start + 1) % 3];
        dp[1][(start + 2) % 3] = dp[0][start] + arr[1][(start + 2) % 3];

        for(int i = 2; i < n; i++){
            dp[i][0] = arr[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = arr[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = arr[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }

        return Math.min(dp[n - 1][(start + 1) % 3], dp[n - 1][(start + 2) % 3]);

    }
}
