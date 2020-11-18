import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
    no.11054 : 가장 긴 바이토닉 부분 수열
    hint : 현재 위치 요소가
        1. 왼쪽부터 시작하면 몇번째 증가하는 수열인지
        2. 오른쪽부터 시작하면 몇뻔재 증가하는 수열인지
 */

public class BOJ11054 {
    public static void dp(int[] arr, int n) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] res1 = new int[n];
        int[] res2 = new int[n];
        Arrays.fill(res1, 1);
        Arrays.fill(res2, 1);
        for (int i = 1; i < n; i++){
            for(int j = i - 1; j >= 0; j--){
                if(arr[i] > arr[j]){
                    res1[i] = Math.max(res1[i], res1[j] + 1);
                }
            }
        }
        for (int i = n - 2; i >= 0; i--){

            for(int j = i + 1; j < n; j++){
                if (arr[i] > arr[j]) {
                    res2[i] = Math.max(res2[i], res2[j] + 1);
                }
            }
        }

        for (int i = 0; i < n; i++){
            res1[i] = res1[i] + res2[i];
        }
        Arrays.sort(res1);
        bw.write((res1[n-1] - 1) + "");
        bw.flush();
        bw.close();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp(arr, n);

        br.close();
    }
}
