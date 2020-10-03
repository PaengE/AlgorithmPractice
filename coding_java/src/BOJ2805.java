import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * no.2805 : 나무 자르기
 * title : 이진 탐색을 응용하여 최솟값이나 최댓값을 찾는 문제 2
 * hint : parametric search
 */

public class BOJ2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] tree = new int[n];


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(tree);

        long left = 1;
        long right = tree[n - 1];
        long maxHeight = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            boolean chk = check(tree, m, mid);

            if (chk) {
                maxHeight = Math.max(maxHeight, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        bw.write(maxHeight + "");
        bw.flush();
        bw.close();
        br.close();

    }

    public static boolean check(int[] tree, int m, long length) {
        long sumTreeLength = 0;

        for (int i : tree) {
            sumTreeLength = (i - length > 0) ? sumTreeLength + i - length : sumTreeLength;
        }

        return sumTreeLength >= m;
    }
}
