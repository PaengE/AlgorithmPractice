import java.io.*;
import java.math.BigInteger;

/**
 *  No.10826: 피보나치 수 4
 *  URL: https://www.acmicpc.net/problem/10826
 *  Hint: BigInteger 사용 ( 무한대 커버 가능 )
 */

public class BOJ10826 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        BigInteger[] fibo = new BigInteger[n + 2];
        fibo[0] = BigInteger.ZERO;
        fibo[1] = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            fibo[i] = fibo[i - 1].add(fibo[i - 2]);
        }

        bw.write(String.valueOf(fibo[n]));
        bw.close();
        br.close();
    }
}
