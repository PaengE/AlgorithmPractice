import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.2096: 내려가기
 *  URL: https://www.acmicpc.net/problem/2096
 *  Hint: DP
 */

public class BOJ2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][3];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dpMin = new int[n][3];
        int[][] dpMax = new int[n][3];
        dpMin[0][0] = dpMax[0][0] = arr[0][0];
        dpMin[0][1] = dpMax[0][1] = arr[0][1];
        dpMin[0][2] = dpMax[0][2] = arr[0][2];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    dpMin[i][j] = Math.min(dpMin[i - 1][0], dpMin[i - 1][1]) + arr[i][j];
                    dpMax[i][j] = Math.max(dpMax[i - 1][0], dpMax[i - 1][1]) + arr[i][j];
                } else if (j == 2) {
                    dpMin[i][j] = Math.min(dpMin[i - 1][1], dpMin[i - 1][2]) + arr[i][j];
                    dpMax[i][j] = Math.max(dpMax[i - 1][1], dpMax[i - 1][2]) + arr[i][j];
                } else {
                    dpMin[i][j] = Math.min(Math.min(dpMin[i - 1][0], dpMin[i - 1][1]), dpMin[i - 1][2]) + arr[i][j];
                    dpMax[i][j] = Math.max(Math.max(dpMax[i - 1][0], dpMax[i - 1][1]), dpMax[i - 1][2]) + arr[i][j];
                }
            }
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            max = Math.max(dpMax[n - 1][i], max);
            min = Math.min(dpMin[n - 1][i], min);
        }
        bw.write(String.valueOf(max) + " " + String.valueOf(min));
        bw.flush();
        bw.close();
        br.close();
    }
}
