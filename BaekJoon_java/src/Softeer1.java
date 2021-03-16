import java.io.*;
import java.util.StringTokenizer;

public class Softeer1 {
    static int k, p, n;
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        long ans = pow(n) * k;

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    static long pow(long num) {
        if (num == 0) {
            return k;
        }
        if (num == 1) {
            return p;
        }

        long m = pow(num / 2);

        long t = (m * m) % MOD;
        if (num % 2 == 1) {
            t = (t * p) % MOD;
        }

        return t;
    }
}
