import java.io.*;
import java.util.StringTokenizer;

public class BOJ2294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (dp[i][j] <= k) {
                    dp[i][j] =
                }
            }

        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {

            }

        }
    }
}
