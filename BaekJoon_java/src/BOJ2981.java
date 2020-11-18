import java.io.*;
import java.util.Arrays;
/*
    no.2981 : 검문
    hint : 유클리드 호제법으로 최대공약수를 구해야 시간초과가 안남
 */

public class BOJ2981 {
    private static int gcd(int a, int b){
        if(a % b == 0)
            return b;
        else
            return gcd(b, a%b);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int val = arr[1] - arr[0];
        for(int i = 2; i < n; i++){
            val = gcd(val, arr[i] - arr[i-1]);
        }

        for(int i = 2; i <= val; i++){
            if (val % i == 0) {
                bw.write(i + " ");
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
