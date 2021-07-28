/*
    no.1932 : 정수 삼각형
    hint : 삼각형의 밑 변의 n개 숫자 중 하나를 고를 때의 경로 최대값을 구함
        왼쪽 변이나 오른쪽 변에 있는 숫자는 부모가 하나.
 */

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1932 {
    static int[][] arr, sumArr;
    static int[] res;

    public static void dp(int n){
        if (n == 1) {
            res[0] = arr[0][0];
        }
        sumArr[0][0] = arr[0][0];
        for(int i = 1; i < n; i++){
            for(int j = 0; j <= i; j++){
                if(j == 0){
                    sumArr[i][j] = arr[i][j] + sumArr[i-1][j];
                } else if(i == j){
                    sumArr[i][j] = arr[i][j] + sumArr[i-1][j-1];
                } else {
                    sumArr[i][j] = arr[i][j] + Math.max(sumArr[i-1][j-1], sumArr[i-1][j]);
                }
                if(i == n-1){
                    res[j] = sumArr[i][j];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        sumArr = new int[n][n];
        res = new int[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j <= i; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp(n);

        Arrays.sort(res);

        bw.write(res[n-1] + "");

        bw.flush();
        br.close();
        bw.close();
    }
}
