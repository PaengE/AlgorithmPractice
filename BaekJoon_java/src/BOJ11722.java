import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  No.11722: 가장 긴 감소하는 부분 수열
 *  URL: https://www.acmicpc.net/problem/11722
 *  Hint: DP (or BinarySearch)
 */
public class BOJ11722 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int maxLength = 1;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] < arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    maxLength = Math.max(dp[i], maxLength);
                }
            }
        }

        bw.write(String.valueOf(maxLength));
        bw.flush();
        br.close();
        bw.close();
    }
}
