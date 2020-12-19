import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.11659: 구간 합 구하기 4
 *  Description: 배열의 값이 바뀌지 않을 때 DP로 구간 합을 구하는 문제
 *  Hint: 구간 i ~ j 의 합 = DP[j] - DP[i - 1]
 */

public class BOJ11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(sum[b] - sum[a - 1]).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
