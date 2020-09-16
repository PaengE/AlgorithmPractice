import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
    no.11047 : 동전 0
    hint : 큰 숫자부터 나누기
 */

public class BOJ11047 {
    private static void greedy(int n, int k, int[] coin){
        int count = 0;
        for(int i = n-1; i >= 0; i--){
            if(k == 0){
                break;
            }else if(k / coin[i] != 0){
                count += k / coin[i];
                k %= coin[i];
            }
        }
        System.out.println(count);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coin = new int[n];
        for(int i = 0; i < n; i++){
            coin[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        greedy(n, k, coin);
    }
}
