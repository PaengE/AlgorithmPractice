import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * No.14002: 가장 긴 증가하는 부분 수열4
 * Description: O(N^2) LIS 를 출력하는 문제
 * URL: https://www.acmicpc.net/problem/14002
 * Hint: 최장길이배열 dp 와 바로 전의 위치를 기록
 */

public class BOJ14002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        dp(n, num);

        br.close();
    }

    static void dp(int n, int[] arr) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        Integer[] dp = new Integer[n];
        int[] p = new int[n];

        Arrays.fill(dp, 1);
        Arrays.fill(p, -1);

        int maxLength = 1;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        p[i] = j;
                    } else {
                        dp[i] = dp[i];
                    }
                    maxLength = Math.max(dp[i], maxLength);
                }
            }
        }
//        for (int i = 0; i < n; i++) {
//            System.out.print(dp[i] + " ");
//        }
//        System.out.println();

        sb.append(maxLength + "\n");

        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(dp));
        int maxLengthIndex = list.indexOf(maxLength);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (maxLengthIndex != -1) {
            pq.offer(arr[maxLengthIndex]);
            maxLengthIndex = p[maxLengthIndex];
        }

        while (!pq.isEmpty()) {
            sb.append(pq.poll() + " ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
