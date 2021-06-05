import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  No.13397: 구간 나누기 2
 *  Hint: 이분탐색
 */

public class BOJ13397 {
    static int n, m, ans = Integer.MAX_VALUE;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;   // 점수의 최솟값은 0 (구간의 크기가 1인 경우)
        int right = Arrays.stream(arr).max().getAsInt() - 1;    // 점수의 최댓값은 최대 요소값 - 최소 요소값 (편의상 최대 요소값 - 1)
        binarySearch(left, right);

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    // 이분탐색
    static void binarySearch(int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (whetherBlockCountIsSatisfied(mid)) {
                ans = Math.min(ans, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }

    // 해당 value 를 만족시키는 구간의 개수가 m개 초과인지 아닌지를 판단
    static boolean whetherBlockCountIsSatisfied(int value) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int blockCnt = 0;

        for (int e : arr) {
            int tMax = Math.max(max, e);
            int tMin = Math.min(min, e);

            if (tMax - tMin <= value) {
                max = tMax;
                min = tMin;
            } else {
                max = e;
                min = e;
                blockCnt++;
            }

            if (blockCnt >= m) {
                return false;
            }
        }
        return true;
    }
}
