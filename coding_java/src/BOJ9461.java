/*
    no.9461 : 파도반 수열
    hint : 점화식 arr[n] = arr[n-1] + a[n-5]
        arr[n] = arr[n-2] + arr[n-3] 도 가능
 */

import java.io.*;

public class BOJ9461 {
    static long[] arr;
    static BufferedWriter bw;
    public static void dp(int p) throws IOException{
        if (p > 3) {
            for (int i = 3; i < p; i++){
                arr[i] = arr[i-3] + arr[i-2];
            }
        }
        bw.write(arr[p-1] + "\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        arr = new long[101];
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        arr[0] = 1;
        arr[1] = 1;
        arr[2] = 1;

        for(int i = 0; i < t; i++){
            int p = Integer.parseInt(br.readLine());
            dp(p);
        }

        bw.flush();

        br.close();
        bw.close();
    }
}
