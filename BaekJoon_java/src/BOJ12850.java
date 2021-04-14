import java.io.*;

/**
 *  NO.12850: 본대 산책2
 *  URL: https://www.acmicpc.net/problem/12850
 *  Hint: 분할 정복을 이용한 거듭제곱
 */

public class BOJ12850 {
    static long[][] mat;
    static int d;
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        d = Integer.parseInt(br.readLine());
        init();

        long[][] ans = dq(d);

        bw.write(String.valueOf(ans[0][0]));
        bw.close();
        br.close();
    }

    static long[][] dq(int n) {
        if (n == 1) {
            return mat;
        }

        // n이 1이 될때까지 분할
        long[][] res = dq(n / 2);

        // 분할된 부분부터 곱셈을 수행
        res = multMat(res, res);

        // n이 홀수라면 행렬을 한번 더 곱함
        if (n % 2 == 1) {
            res = multMat(res, mat);
        }

        return res;
    }

    // 행렬의 곱셈
    static long[][] multMat(long[][] a, long[][] b) {
        long[][] res = new long[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return res;
    }

    // 인접 행렬 구성
    static void init() {
        mat = new long[8][8];
        // 0:정보과학관, 1:전산관, 2:미래관, 3:신양관
        // 4:한경직기념관, 5:진리관, 6:형남공학관, 7:학생회관
        mat[0][1] = 1;
        mat[0][2] = 1;
        mat[1][0] = 1;
        mat[1][2] = 1;
        mat[1][3] = 1;
        mat[2][0] = 1;
        mat[2][1] = 1;
        mat[2][3] = 1;
        mat[2][4] = 1;
        mat[3][1] = 1;
        mat[3][2] = 1;
        mat[3][4] = 1;
        mat[3][5] = 1;
        mat[4][2] = 1;
        mat[4][3] = 1;
        mat[4][5] = 1;
        mat[4][6] = 1;
        mat[5][3] = 1;
        mat[5][4] = 1;
        mat[5][7] = 1;
        mat[6][4] = 1;
        mat[6][7] = 1;
        mat[7][5] = 1;
        mat[7][6] = 1;
    }
}
