import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
    no.2565 : 전깃줄
    hint : 왼쪽 전봇대를 정렬 후 생각해보기
           LIS(Longest Increasing Subsequence) Problem
 */

public class BOJ2565 {
    public static void dp(int[][] arr, int n) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] dp = new int[n];


        Arrays.fill(dp, 1);
        for(int i = 1; i < n; i++){
            for(int j = i-1; j >= 0; j--){
                if(arr[i][1] > arr[j][1]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        Arrays.sort(dp);

        bw.write((n-dp[n-1]) + "");

        bw.flush();
        bw.close();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];
        String s;
        StringTokenizer st;
        for(int i = 0; i < n; i++){
            s = br.readLine();
            st = new StringTokenizer(s);
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, Comparator.comparingInt(o1 -> o1[0]));

        dp(arr, n);

        br.close();
    }
}
