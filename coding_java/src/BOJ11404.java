import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * No.11404: 플로이드
 * description: Floyd Warshall 알고리즘을 배우는 문제
 * hint: distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j])
 */

public class BOJ11404 {
    static int n, m;
    static long[][] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        distance = new long[n + 1][n + 1];

        for (long[] a : distance) {
            Arrays.fill(a, Integer.MAX_VALUE);
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            distance[start][end] = Math.min(distance[start][end], time);
        }

        floydWarshall();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j || distance[i][j] >= Integer.MAX_VALUE) {
                    sb.append("0 ");
                } else {
                    sb.append(distance[i][j] + " ");
                }
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();

    }
    // i ~ j 경로의 최단 거리는 i ~ k 와 k ~ j 최단 경로의 합
    static void floydWarshall() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }
    }
}
