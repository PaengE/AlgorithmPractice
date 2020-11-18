import java.io.*;

/**
 * no.2740 : 행렬 곱셈
 * title : 행렬의 거듭제곱을 계산하기 전에 먼저 풀어야 할 문제
 * hint : C[n][k] = C[n][k] + A[n][m] * B[m][k];
 */

public class BOJ2740 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int[][] A = new int[n][m];

        for (int i = 0; i < n; i++) {
            s = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                A[i][j] = Integer.parseInt(s[j]);
            }
        }

        s = br.readLine().split(" ");
        int k = Integer.parseInt(s[1]);
        int[][] B = new int[m][k];

        for (int i = 0; i < m; i++) {
            s = br.readLine().split(" ");
            for (int j = 0; j < k; j++) {
                B[i][j] = Integer.parseInt(s[j]);
            }
        }

        int[][] res = new int[n][k];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                for (int l = 0; l < m; l++) {
                    res[i][j] = res[i][j] + A[i][l] * B[l][j];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                bw.write(res[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
        
    }
}
