import java.io.*;

/**
 * no.2749 : 피보나치 수 3
 * title : 행렬 곱셈을 응용해 피보나치 수를 구하는 문제
 * hint : [ Fn   ] = [ 1 1 ] * [ Fn-1 ]
 *        [ Fn-1 ]   [ 1 0 ]   [ Fn-2 ]
 *    ->  [ Fn   ] = [ 1 1 ]^n-1 * [ F1 ]
 *        [ Fn-1 ]   [ 1 0 ]       [ F0 ]
 */

public class BOJ2749 {
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
                tmp[i][j] %= 1000000;
            }
        }
        if (n % 2 == 1){
            long[][] t = new long[2][2];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 2; k++) {
                        t[i][j] += tmp[i][k] * arr[k][j];
                    }
                    t[i][j] %= 1000000;
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
