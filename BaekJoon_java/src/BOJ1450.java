import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *  No.1450: 냅색문제
 *  hint: meet in the middle (반으로 나누어서 처리)
 */

public class BOJ1450 {
    static int n, c, index;
    static int[] arr;
    static ArrayList<Integer> aSum, bSum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        aSum = new ArrayList<>();
        bSum = new ArrayList<>();

        aBruteForce(0, 0);
        bBruteForce(n / 2, 0);
        Collections.sort(bSum);

        int ans = 0;
        for (int i = 0; i < aSum.size(); i++) {
            index = -1;
            binarySearch(0, bSum.size() - 1, aSum.get(i));
            ans += index + 1;
        }

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    static void aBruteForce(int i, int sum) {
        if (sum > c) {
            return;
        }
        if (i == n / 2) {
            aSum.add(sum);
            return;
        }
        aBruteForce(i + 1, sum + arr[i]);
        aBruteForce(i + 1, sum);
    }

    static void bBruteForce(int i, int sum) {
        if (sum > c) {
            return;
        }
        if (i == n) {
            bSum.add(sum);
            return;
        }
        bBruteForce(i + 1, sum + arr[i]);
        bBruteForce(i + 1, sum);
    }

    static void binarySearch(int start, int end, int val) {
        if (start > end) {
            return;
        }

        int mid = (start + end) / 2;

        if (bSum.get(mid) + val <= c) {
            index = mid;
            binarySearch(mid + 1, end, val);
        } else {
            binarySearch(start, mid - 1, val);
        }
    }
}
