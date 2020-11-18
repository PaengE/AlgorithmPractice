import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * title : Dynamic Programming 2
 * no.2293 : 동전 1
 * desc : 더 이상 사용되지 않는 값을 버림으로써 공간 복잡도를 향상시키는 문제
 * hint : 동전이 1, 2, 5 일 경우 10원을 만드는 경우의 수는
 *        dp[9] + 1원, dp[8] + 2원, dp[5] + 5원 세가지 경우의 가지 수의 합이다.
 */

public class BOJ2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coin = new int[n];

        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coin);

        int[] dp = new int[k + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = coin[i]; j <= k; j++) {
                dp[j] += dp[j - coin[i]];
            }
        }

        bw.write(dp[k] + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
