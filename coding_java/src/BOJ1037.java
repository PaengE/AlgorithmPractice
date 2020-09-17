import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
    no.1037 : 약수
    hint : 정수 = 최대약수 * 최소약수
 */

public class BOJ1037 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        bw.write((arr[0] * arr[n-1]) + "");
        bw.flush();

        br.close();
        bw.close();
        
    }
}
