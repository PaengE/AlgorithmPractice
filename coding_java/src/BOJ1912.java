import java.io.*;
import java.util.StringTokenizer;
/*
    no.1912 : 연속합
    hint : 지금까지 더한 값, 현재 값을 비교
           dp[i] = max(dp[i-1] + arr[i], arr[i])
 */

public class BOJ1912 {
    public static void dp(int n, int[] arr) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] dp = new int[n];

        dp[0] = arr[0];
        int max = dp[0];
        for(int i = 1; i < n; i++){
            dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
            if(dp[i] > max)
                max = dp[i];
        }
        bw.write(max + "");
        bw.flush();
        bw.close();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp(n, arr);

        br.close();
    }
}
