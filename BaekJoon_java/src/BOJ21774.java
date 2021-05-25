import java.io.*;
import java.util.StringTokenizer;

/**
 *  아직 못품
 */

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
        logs = new long[n + 1];
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

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= 6; j++) {
                dp[i][j] += dp[i - 1][j];
            }
        }

        System.out.println();
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine(), "#");
            long queryS = Long.parseLong(st.nextToken().replaceAll("[- :]", ""));
            long queryE = Long.parseLong(st.nextToken().replaceAll("[- :]", ""));
            int queryLv = Integer.parseInt(st.nextToken());


            int lower = lowerBound(0, n, queryS);
            int upper = upperBound(0, n, queryE);
            System.out.println("lower = " + lower + ", upper = " + upper);

            int count = dp[upper == n ? upper : upper - 1][queryLv] - dp[lower == n ? lower : lower - 1][queryLv];
            sb.append(count + "\n");

        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

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
