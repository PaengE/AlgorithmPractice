import java.io.*;

/**
 * no.10830 : 행렬 제곱
 * title : 분할 정복으로 행렬의 거듭젝보을 빠르게 계산하는 문제
 * hint : M^9 = (M^4 * M^4) * M = ((M^2 * M^2) * (M^2 * M^2)) * M
 * 알고리즘 자체는 O(logN), long 타입으로 할 것.
 */

public class BOJ10830 {
    static long[][] matrix;
    static int n;
    public static long[][] pow(long b){
        long[][] tmp = new long[n][n];
        long[][] m;
        if(b == 1){
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    tmp[i][j] = matrix[i][j] % 1000;
                }
            }
            return tmp;
        }
        m = pow(b / 2);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    tmp[i][j] += m[i][k] * m[k][j];
                }
                tmp[i][j] %= 1000;
            }
        }
        if(b % 2 == 1){
            long[][] odd = new long[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        odd[i][j] += tmp[i][k] * matrix[k][j];
                    }
                    odd[i][j] %= 1000;
                }
            }
            return odd;
        } else {
            return tmp;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        long b = Long.parseLong(s[1]);
        matrix = new long[n][n];

        for (int i = 0; i < n; i++) {
            s = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(s[j]);
            }
        }

        long[][] ans = pow(b);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bw.write(ans[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();

    }
}
