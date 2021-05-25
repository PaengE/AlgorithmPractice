import java.io.*;
import java.util.StringTokenizer;

public class BOJ21774 {
    static long[] logs;
    static int[] lvs;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        logs = new long[n + 2];
        logs[n + 1] = Integer.MAX_VALUE;    // lowerbound와 upperbound가 없을 때를 위한 인덱스
        lvs = new int[n + 1];
        dp = new int[n + 1][7];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), "#");
            logs[i] = Long.parseLong(st.nextToken().replaceAll("[- :]", ""));
            lvs[i] = Integer.parseInt(st.nextToken());
            for (int j = lvs[i]; j >= 1; j--) {
                dp[i][j]++;
            }
        }

        // i번째 로그까지 j레벨 이상인 로그의 개수
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 6; j++) {
                dp[i][j] += dp[i - 1][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine(), "#");
            long queryS = Long.parseLong(st.nextToken().replaceAll("[- :]", ""));
            long queryE = Long.parseLong(st.nextToken().replaceAll("[- :]", ""));
            int queryLv = Integer.parseInt(st.nextToken());

            int lower = lowerBound(1, n + 1, queryS);
            int upper = upperBound(1, n + 1, queryE);
            int count = dp[upper - 1][queryLv] - dp[lower - 1][queryLv];
            sb.append(count + "\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    // target 이상인 첫번째 수
    static int lowerBound(int left, int right, long target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (target <= logs[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    // target 초과인 첫번째 수
    static int upperBound(int left, int right, long target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (target >= logs[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
