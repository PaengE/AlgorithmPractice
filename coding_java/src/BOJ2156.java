import java.io.*;

/*
    no.2156 : 포도주 시식
    hint : 1. n-1번째와 n번째 잔을 마시는 경우
           2. n-1번째 잔을 마시지 않고 n번째 잔을 마시는 경우
           3. n-1번째 잔을 마시고 n번째 잔을 마시지 않는 경우
 */

public class BOJ2156 {
    public static void dp(int[] arr, int n) throws IOException {
        int[] sum = new int[n];
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        if(n == 1){
            bw.write(arr[n-1] + "");
        } else if(n == 2){
            int s = arr[n-2] + arr[n-1];
            bw.write(s + "");
        } else {
            sum[0] = arr[0];
            sum[1] = arr[0] + arr[1];
            sum[2] = Math.max(Math.max(arr[0] + arr[1], arr[1] + arr[2]), arr[0] + arr[2]);
            for(int i = 3; i < n; i++){
                sum[i] = Math.max(Math.max(sum[i-3] + arr[i-1] + arr[i], sum[i-2] + arr[i]), sum[i-1]);
            }
            bw.write(sum[n-1] + "");
        }
        bw.flush();
        bw.close();

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp(arr, n);

        br.close();
    }
}
