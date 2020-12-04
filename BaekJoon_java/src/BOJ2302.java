import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  No.2302: 극장 좌석
 *  URL: https://www.acmicpc.net/problem/2302
 *  Hint: fibonacci + Queue
 */

public class BOJ2302 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int start = 0, end;
        int maxDepth = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            end = Integer.parseInt(br.readLine());
            maxDepth = Math.max(maxDepth, end - start - 1);
            q.offer(end - start - 1);
            start = end;
        }
        maxDepth = Math.max(maxDepth, n - start);
        q.offer(n - start);

        int[] dp = new int[maxDepth + 2];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= maxDepth; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        long ans = 1;
        while (!q.isEmpty()) {
            ans *= dp[q.poll()];
        }

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }
}
