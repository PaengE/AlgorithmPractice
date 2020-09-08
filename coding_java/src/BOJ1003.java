/*
    no.1003 : fibonacci function
    hint : 규칙 찾기
 */

import java.io.*;

public class BOJ1003 {
    static BufferedReader br;
    static BufferedWriter bw;
    static String s;
    static int[][] res;
    public static void fibo(int n) throws IOException{
        if (n == 0){
            bw.write(res[0][0] + " " + res[0][1] + "\n");
        } else if (n == 1){
            bw.write(res[1][0] + " " + res[1][1] + "\n");
        } else {
            for(int i = 2; i < n+1; i++){
                res[i][0] = res[i-1][0] + res[i-2][0];
                res[i][1] = res[i-1][1] + res[i-2][1];
            }
            bw.write(res[n][0] + " " + res[n][1] + "\n");
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        s = br.readLine();
        int t = Integer.parseInt(s);
        int[] arr = new int[t];
        res = new int[41][2];
        for (int i = 0; i < t; i++){
            s = br.readLine();
            arr[i] = Integer.parseInt(s);
        }
        res[0][0] = 1;
        res[1][1] = 1;

        for (int i = 0; i < t; i++){
            fibo(arr[i]);
        }
        bw.flush();

        br.close();
        bw.close();

    }
}
