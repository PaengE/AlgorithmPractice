import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.2467: 용액
 *  URL: https://www.acmicpc.net/problem/2467
 *  Hint: 이분탐색 or 투포인터
 */

public class BOJ2467 {
    static int n, ans, min = 0, max = 0;
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

        ans = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            binarySearch(arr[i], i + 1, n - 1);
        }

        bw.write(min + " " + max);
        bw.close();
        br.close();
    }

    static void binarySearch(int o, int left, int right) {
        if (left == right) {
            if (ans > Math.abs(o + arr[right])) {
                ans = Math.abs(o + arr[right]);
                min = o;
                max = arr[right];
            }
            return;
        }

        int val = arr[(left + right) / 2];
        if (ans > Math.abs(o + val)) {
            ans = Math.abs(o + val);
            min = o;
            max = val;
        }

        if (o + val == 0) {
            ans = 0;
            return;
        } else if (o + val < 0) {
            binarySearch(o, (left + right) / 2 + 1, right);
        } else {
            binarySearch(o, left, (left + right) / 2);
        }
    }
}
