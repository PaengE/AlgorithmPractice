import java.io.*;
import java.util.StringTokenizer;

/**
 *  no.2629: 양팔저울
 *  url: https://www.acmicpc.net/problem/2629
 *  hint: knapsack 변형 문제
 */
public class BOJ2629 {
    static boolean[][] isPossible;
    static int[] weight;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        weight = new int[n];
        isPossible = new boolean[n + 1][15001]; // 추 무게의 최대 총합은 15000

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int bead = Integer.parseInt(st.nextToken());

            // 무게가 15000이 넘는 구슬은 무게를 확인할 수 없음 (추 무게의 최대 총합이 15000이므로)
            if (bead > 15000) {
                sb.append("N ");
            } else {
                if (isPossible[n][bead]) {
                    sb.append("Y ");
                } else {
                    sb.append("N ");
                }
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void dfs(int cnt, int w){
        if (isPossible[cnt][w]) {
            return;
        }
        isPossible[cnt][w] = true;

        if (cnt == n) {
            return;
        }

        // 한쪽에 추가로 놓은 경우
        dfs(cnt + 1, w + weight[cnt]);
        // 아무쪽에도 놓지 않은 경우
        dfs(cnt + 1, w);
        // 반대쪽에 놓은 경우
        dfs(cnt + 1, Math.abs(w - weight[cnt]));
    }
}
