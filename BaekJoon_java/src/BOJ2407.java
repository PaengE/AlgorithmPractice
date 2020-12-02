import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 *  No.2407: 조합
 *  URL: https://www.acmicpc.net/problem/2407
 *  Hint: BigInteger 사용
 */

public class BOJ2407 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        BigInteger[] arr = new BigInteger[n + 1];

        arr[1] = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1].add(BigInteger.ONE);
        }

        BigInteger ans = new BigInteger(String.valueOf(1));
        for (int i = 0; i < m; i++) {
            ans = ans.multiply(arr[n - i]);
        }
        for (int i = m; i > 0; i--) {
            ans = ans.divide(arr[i]);
        }

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }
}
