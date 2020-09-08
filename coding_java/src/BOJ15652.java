/*
    no.15652 : N and M(4)
    hint: 가능한 모든 수 중에서 이전 값보다 큰 값들만 사용
 */

import java.io.*;
import java.util.StringTokenizer;

public class BOJ15652 {

    static int n, m;
    static int[] arr;
    static BufferedReader br;
    static BufferedWriter bw;

    static void solution(int idx) throws IOException{
        if(idx > m){
            for(int i = 1; i <= m; i++){
                bw.write(arr[i] + " ");
            }
            bw.write("\n");
            bw.flush();
        } else {
            for (int j = 1; j <= n; j++){
                if(arr[idx-1] <= j){
                    arr[idx] = j;
                    solution(idx + 1);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m+1];

        solution(1);

        br.close();
        bw.close();
    }
}
