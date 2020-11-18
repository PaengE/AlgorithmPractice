import java.io.*;
import java.util.StringTokenizer;

/**
 * no.11049: 행렬 곱셈 순서
 * description: 행렬을 곱하는 최소비용을 구하는 문제
 * hint: dp[i][j] = Min(dp[i][j], dp[i][k] + dp[k+1][j] + (mat[i][0] * mat[k][1] * mat[j][1]))
 */

public class BOJ11049 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] mat = new int[n][2];
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mat[i][0] = Integer.parseInt(st.nextToken());
            mat[i][1] = Integer.parseInt(st.nextToken());
        }

        bw.write(String.valueOf(solution(mat)));
        bw.flush();

        bw.close();
        br.close();
    }

    static int solution(int[][] mat) {
        int size = mat.length;
        int[][] dp = new int[size][size];

        for (int i = 0; i < size - 1; i++) {
            dp[i][i + 1] = mat[i][0] * mat[i][1] * mat[i + 1][1];
        }

        for (int gap = 2; gap < size; gap++) {
            for (int i = 0; i + gap < size; i++) {
                int j = i + gap;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + (mat[i][0] * mat[k][1] * mat[j][1]));
                }
            }
        }
        return dp[0][size - 1];
    }
}
