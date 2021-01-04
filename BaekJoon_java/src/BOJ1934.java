import java.io.*;
import java.util.StringTokenizer;

/**
 *  no.1934: 최소공배수
 *  url: https://www.acmicpc.net/problem/1934
 *  hint: 두 수의 곱 = 최대공약수 * 최소공배수
 */

public class BOJ1934 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(a * b / gcd(a, b)).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}
