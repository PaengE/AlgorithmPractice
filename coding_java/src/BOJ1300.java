import java.io.*;

/**
 * no.1300 : K번째 수
 * title : 의외로 이진 탐색으로 풀 수 있는 놀라운 문제 1
 * hint : 구하려는 값이 S라 할때, S는 1행부터 N행까지  i번째 행에서
 *         min(S / i, N)을 더한 값임.
 */

public class BOJ1300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        long left = 1;
        long right = k;
        long ans = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            int cnt = 0;

            for (int i = 1; i <= n; i++) {
                cnt += Math.min(mid / i, n);
            }

            if (cnt >= k) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}


/**
 * TLE code
 */
//        int n = Integer.parseInt(br.readLine());
//        int k = Integer.parseInt(br.readLine());
//        int[] mat = new int[n * n];
//
//        int l = 0;
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= n; j++) {
//                mat[l++] = i * j;
//            }
//        }
//        Arrays.sort(mat);
//
//        bw.write(mat[k - 1] + "");