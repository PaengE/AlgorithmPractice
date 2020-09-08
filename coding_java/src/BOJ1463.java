/*
    no.1463 : 1로 만들기
    hint : n을 -1, /2, /3 한 것 중 연산 횟수가 작은걸 선택
 */

import java.io.*;

public class BOJ1463 {
    static int n;
    static int[] arrDP;

    public static void dp() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        if (n == 1){
            bw.write("0");
        } else if(n == 2 || n == 3){
            bw.write("1");
        } else{
            arrDP[1] = 0;
            arrDP[2] = 1;
            arrDP[3] = 1;
            for(int i = 4; i <= n; i++){
                arrDP[i] = arrDP[i-1] + 1;
                if(i % 2 == 0){
                    arrDP[i] = Math.min(arrDP[i], arrDP[i / 2] + 1);
                }
                if(i % 3 == 0){
                    arrDP[i] = Math.min(arrDP[i], arrDP[i / 3] + 1);
                }
            }
            bw.write(arrDP[n] + "");
        }
        bw.flush();
        bw.close();

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arrDP = new int[n+1];

        dp();

        br.close();
    }
}
