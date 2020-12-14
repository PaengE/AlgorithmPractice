import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1949 {
    static int n;
    static int[] arr;
    static int[][] dp;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        dp = new int[n + 1][2];

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }

        dfs(1, -1);

        bw.write(String.valueOf(Math.max(dp[1][0], dp[1][1])));
        bw.close();
        br.close();
    }

    static void dfs(int cur, int parent) {
        for (int child : list.get(cur)) {
            if (child != parent) {
                // 현재 마을을 우수마을로 지정했는지 안했는지만 처리하면 된다.
                // 현재마을이 우수마을이 아닐때 다음마을을 max(다음마을지정, 미지정)으로
                // 계산하는데 다음마을을 지정하지 않더라도 그 다음 단계에서는
                // 무조건 지정하는게 이득이어서 지정하게 되므로 3번 규칙을 무시해도된다.
                dfs(child, cur);
                dp[cur][1] += dp[child][0];
                dp[cur][0] += Math.max(dp[child][0], dp[child][1]);
            }
        }
        dp[cur][1] += arr[cur];
    }
}
