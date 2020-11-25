import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.1890: 점프
 *  URL: https://www.acmicpc.net/problem/1890
 *  Hint: DP
 */

public class BOJ1890 {
    static int n;
    static int[][] arr;
    static long[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        map = new long[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

            }
        }

        map[0][0] = 1;
        dp();

        bw.write(String.valueOf(map[n - 1][n - 1]));
        bw.flush();
        br.close();
        bw.close();
    }

    static void dp() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == n - 1 && j == n - 1)) {
                    continue;
                }
                int t = arr[i][j];

                if (i + t < n) {
                    map[i + t][j] += map[i][j];
                }

                if (j + t < n) {
                    map[i][j + t] += map[i][j];
                }
            }
        }
    }
}
