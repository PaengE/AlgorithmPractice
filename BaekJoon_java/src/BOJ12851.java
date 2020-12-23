import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  No.12851: 숨바꼭질 2
 *  Hint: BFS
 */

public class BOJ12851 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int size = Math.max(n, k) * 2 + 1;
        int[] dp = new int[size];
        Arrays.fill(dp, -1);

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{n, 0});

        int count = 0;
        dp[n] = 0;
        while (!q.isEmpty()) {
            int[] t = q.poll();
            int a = t[0];
            int b = t[1];

            if (a == k) {
                if (dp[a] == -1) {
                    count += 1;
                } else if (dp[a] == b) {
                    count += 1;
                }
            }

            if (dp[a] != -1 && dp[a] < b) {
                continue;
            }
            dp[a] = b;

            if (a - 1 >= 0) {
                q.offer(new int[]{a - 1, b + 1});
            }
            if (a + 1 < size) {
                q.offer(new int[]{a + 1, b + 1});
            }
            if (a * 2 < size) {
                q.offer(new int[]{a * 2, b + 1});
            }

        }

        bw.write(String.valueOf(dp[k]) + "\n" + String.valueOf(count));
        bw.close();
        br.close();
    }
}
