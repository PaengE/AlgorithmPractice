import java.io.*;
import java.util.StringTokenizer;
/*
    no.3036 : 링
    hint : 최대 공약수를 구한 후, 각 숫자를 최대 공약수로 나누면 됨.
 */

public class BOJ3036 {
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < n; i++){
            int val = gcd(arr[0], arr[i]);
            bw.write((arr[0] / val)+"/"+(arr[i] / val)+"\n");
        }
        bw.flush();
        bw.close();
        br.close();

    }
}
