import java.io.*;
import java.util.StringTokenizer;

/*
    no.12865 : 평범한 배낭(KnapSack Problem)
    hint : DP배열 구성 시, max(지금까지 넣은 보석의 최대 가치, 현재 보석 가치 + 나머지 자리에 넣을 수 있는 보석의 최대 가치)
           ans[i][j] = max(ans[i-1][j], ans[i-1][j-weight[i-1]] + profit[i])
 */

public class BOJ12865 {
    public static void dp(int n, int k, int[][] arr) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[][] dp = new int [n+1][k+1];

        for(int i = 0; i < n; i++){
            int weight = arr[i][0];
            int value = arr[i][1];
            for (int j = 0; j <= k; j++){
                if (j < weight){
                    dp[i+1][j] = dp[i][j];
                } else {
                    dp[i+1][j] = Math.max(dp[i][j], dp[i][j-weight] + value);
                }
            }
        }

        bw.write(dp[n][k] + "");

        bw.flush();
        bw.close();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        StringTokenizer st;

        s = br.readLine();
        st = new StringTokenizer(s);
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][2];
        for(int i = 0; i < n; i++){
            s = br.readLine();
            st = new StringTokenizer(s);
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        br.close();

        dp(n, k, arr);
    }
}
