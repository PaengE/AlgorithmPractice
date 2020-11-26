import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.1965: 상자넣기
 *  URL: https://www.acmicpc.net/problem/1965
 *  Hint: DP
 */

public class BOJ1965 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] < arr[j]) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                    max = Math.max(dp[j], max);
                }
            }
        }

//        for (int i = 0; i < n; i++) {
//            System.out.print(dp[i] + " ");
//        }

        bw.write(String.valueOf(max + 1));
        bw.flush();
        br.close();
        bw.close();
    }
}
