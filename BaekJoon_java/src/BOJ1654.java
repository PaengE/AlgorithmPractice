import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * no.1654 : 랜선 자르기
 * title : parametric search 를 이용한 이진탐색을 응용하여 최댓값, 최솟값 구하기
 * hint : parametric search
 */

public class BOJ1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] line = new int[n];

        for (int i = 0; i < n; i++) {
            line[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(line);

        long left = 1;
        long right = line[n-1];
        long maxLength = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            boolean chk = check(line, k, mid);

            if (chk) {
                maxLength = Math.max(maxLength, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        bw.write(maxLength + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean check(int[] line, int k, long length) {
        int cnt = 0;
        for (int i : line) {
            cnt += i / length;
        }
        return cnt >= k;
    }
}
