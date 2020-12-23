import java.io.*;
import java.util.*;

/**
 *  No.13549: 숨바꼭질 3
 *  Hint: BFS
 */

public class BOJ13549 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int size = Math.max(n, k) * 2 + 1;
        int[] dp = new int[size];

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{n, 0});

        dp[n] = 0;
        while (!q.isEmpty()) {
            int[] t = q.poll();
            int a = t[0];
            int b = t[1];

            if (dp[a] != 0) {
                continue;
            }
            dp[a] = b;

            if (a == k) {
                break;
            }

            if (a * 2 < size) {
                q.offer(new int[]{a * 2, b});
            }
            if (a - 1 >= 0) {
                q.offer(new int[]{a - 1, b + 1});
            }
            if (a + 1 < size) {
                q.offer(new int[]{a + 1, b + 1});
            }
        }

        bw.write(String.valueOf(dp[k]));
        bw.close();
        br.close();
    }
}
