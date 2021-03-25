import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  No.2473: 세 용액
 *  Url: https://www.acmicpc.net/problem/2473
 */

public class BOJ2473 {
    static int n;
    static long v1, v2, v3, minSum = Long.MAX_VALUE;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        for (int i = 0; i < n - 2; i++) {   // i 는 첫 번째 용액
            for (int j = i + 2; j < n; j++) {   // j 는 세 번째 용액
                binarySearch(i, j);
            }
        }

        bw.write(v1 + " " + v2 + " " + v3);
        bw.close();
        br.close();
    }

    // 첫번째 용액과 세번째 용액 사이에 있는 용액 중 혼합액이 0과 가장 가까운 용액을 구하는 과정
    // 과정 속에서 정확히 혼합액이 0이 아니더라도 0에 가장 근접한 것으로 업데이트됨
    static void binarySearch(int first, int third) {
        int left = first + 1;
        int right = third - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            long sumOfThree = (long) arr[first] + (long) arr[mid] + (long) arr[third];

            if (minSum > Math.abs(sumOfThree)) {
                v1 = arr[first];
                v2 = arr[mid];
                v3 = arr[third];
                minSum = Math.abs(sumOfThree);
            }

            if (sumOfThree == 0) {
                return;
            } else if (sumOfThree < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }
}
