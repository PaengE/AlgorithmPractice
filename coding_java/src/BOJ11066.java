import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11066 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[k];
            for (int j = 0; j < k; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);

            int[][] dp = new int[k][k];
            int idx = 0;
            long sum = 0;
            for (int j = 1; j < k; j += 2) {
                dp[0][idx++] = arr[j - 1] + arr[j];
                System.out.print(" " + dp[0][idx-1]);
                sum += dp[0][idx - 1];
            }
            if (k % 2 == 1) {
                dp[0][idx] = arr[k - 1];
                System.out.print(" " + dp[0][idx]);
                sum += dp[0][idx];
            }
            System.out.println();


            // 홀수면 안더하고 나중에 더하면 됨
            int tmpIdx = 0;
            var ceil = Math.ceil(Math.log(k) / Math.log(2));
            for (int j = 1; j < (int) ceil; j++) {
                int tmp = 1;
                while (dp[j - 1][tmp] != 0) {
                    dp[j][tmpIdx++] = dp[j - 1][tmp - 1] + dp[j - 1][tmp];
                    System.out.print(" " + dp[j][tmpIdx-1]);
                    sum += dp[j][tmpIdx - 1];
                    tmp += 2;
                }
                if (dp[j - 1][tmp - 1] != 0) {
                    dp[j][tmpIdx] = dp[j - 1][tmp - 1];
                    System.out.print(" " + dp[j][tmpIdx]);
                    sum += dp[j][tmpIdx];
                }
                System.out.println();
            }

            bw.write(sum + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
