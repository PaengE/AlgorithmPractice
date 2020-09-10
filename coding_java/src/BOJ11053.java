import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
    no.11053 : 가장 긴 증가하는 수열
    hint : n번째 요소를 n-1, n-2, ... , 0번째 요소들 모두와 비교해 봐야 함
           비교하고 난 뒤 저장 할 때 DP를 이용
 */

public class BOJ11053 {
    public static void dp(int[] arr, int n) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] res = new int[n];
        Arrays.fill(res, 1);

        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--){
                if(arr[i] > arr[j]){
                    res[i] = Math.max(res[i], (res[j] + 1));
                }
            }
        }

        Arrays.sort(res);
        bw.write(res[n-1] + "");
        bw.flush();
        bw.close();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp(arr, n);

        br.close();
    }
}
