import java.io.*;

/**
 *  no.11444: 피보나치 수 6
 *  url: https://www.acmicpc.net/problem/11444
 *  hint: 행렬의 곱셈 성질을 이용
 */

public class BOJ11444 {
    static final int MOD = 1000000007;
    static long[][] arr = {{1, 1}, {1, 0}};
    public static long[][] pow(long n){
        long[][] tmp = new long[2][2];

        if(n == 1){
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    tmp[i][j] = arr[i][j];
                }
            }
            return tmp;
        }

        long[][] m;
        m = pow(n / 2);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    tmp[i][j] += m[i][k] * m[k][j];
                }
                tmp[i][j] %= MOD;
            }
        }
        if (n % 2 == 1){
            long[][] t = new long[2][2];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 2; k++) {
                        t[i][j] += tmp[i][k] * arr[k][j];
                    }
                    t[i][j] %= MOD;
                }
            }
            return t;
        } else {
            return tmp;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long n = Long.parseLong(br.readLine());
        long ans;

        if(n == 1)
            ans = 1;
        else {
            long[][] res = pow(n-1);
            ans = res[0][0];
        }

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();

    }
}
