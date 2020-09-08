/*
    no.2579 : 계단 오르기
    hint : 마지막 계단을 밟을 때
        1. 마지막 계단 전 계단을 밟을 경우
        2. 마지막 계단 전 계단을 밟지 않을 경우
 */

import java.io.*;

public class BOJ2579 {
    static int[] arr, res;
    static int n;

    public static void dp() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        if (n == 1){
            bw.write(arr[0] + "");
        } else if(n == 2){
            bw.write(Math.max(arr[0] + arr[1], arr[1]) + "");
        } else {
            res[0] = arr[0];
            res[1] = arr[0] + arr[1];
            res[2] = Math.max(arr[0]+arr[2], arr[1]+arr[2]);

            for(int i = 3; i < n; i++){
                res[i] = Math.max(res[i-3] + arr[i-1], res[i-2]) + arr[i];
            }
            bw.write(res[n-1] + "");
        }

        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        res = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp();

        br.close();
    }
}
