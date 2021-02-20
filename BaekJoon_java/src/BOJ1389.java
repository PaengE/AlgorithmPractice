import java.io.*;
import java.util.StringTokenizer;

public class BOJ1389 {
    static int[][] relations;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        relations = new int[n + 1][n + 1];

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            relations[a][b] = 1;
            relations[b][a] = 1;
        }

        floydWarshall();

        int answer = 0;
        int bacon = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    sum += relations[i][j];
                }
            }

            if (bacon > sum) {
                answer = i;
                bacon = Math.min(bacon, sum);
            }
        }

        bw.write(String.valueOf(answer));
        bw.close();
        br.close();
    }

    static void floydWarshall() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (relations[i][k] > 0 && relations[k][j] > 0) {
                        if (relations[i][j] == 0) {
                            relations[i][j] = relations[i][k] + relations[k][j];
                        } else {
                            relations[i][j] = Math.min(relations[i][j], relations[i][k] + relations[k][j]);
                        }
                    }
                }
            }
        }
    }
}
