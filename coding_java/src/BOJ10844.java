/*
    no.10844 : 쉬운 계단 수
    hint : n번째 자리에 숫자 k를 놓을 수 있는지를 따져 볼 것.
 */

import java.io.*;

public class BOJ10844 {

    public static void dp(int n) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[][] arr = new int[n+1][10];

        for(int i = 1; i <= 9; i++){
            arr[1][i] = 1;
        }
        for(int i = 2; i <= n; i++){
            for(int j = 0; j <= 9; j++){
                if (j == 0){
                    arr[i][j] = arr[i-1][1];
                } else if (j == 9){
                    arr[i][j] = arr[i-1][8];
                } else {
                    arr[i][j] = (arr[i-1][j-1] + arr[i-1][j+1]) % 1000000000;
                }
            }
        }
        long sum = 0;
        for(int i = 0; i <= 9; i++){
            sum += arr[n][i];
        }

        bw.write(sum % 1000000000 + "");
        bw.flush();
        bw.close();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        dp(n);

        br.close();
    }
}
