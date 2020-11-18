import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    no.11050 : 이항 계수 1
 */

public class BOJ11050 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);

        int res = 1;
        for(int i = 1; i <= k; i++){
            res = res * n;
            n--;
        }
        for(int j = k; j >= 2; j--){
            res = res / j;
        }
        System.out.println(res);
    }
}
