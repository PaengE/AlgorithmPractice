import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  No.16938: 캠프 준비
 *  Hint: BruteForce + 조합 + 백트랙킹
 */

public class BOJ16938 {
    static int n, l, r, x, ans;
    static int[] arr, res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 2; i <= n; i++) {
            res = new int[i];
            combination(n, i, 0, 0, res);
        }

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    static void combination(int n, int r, int index, int depth, int[] res) {
        if (depth == r) {
            if (areValidNumbers(res)) {
                ans++;
            }
            return;
        }

        for (int i = index; i < n; i++) {
            res[depth] = arr[i];
            combination(n, r, i + 1, depth + 1, res);
        }
    }

    static boolean areValidNumbers(int[] res) {
        int sum = Arrays.stream(res).sum();
        if (l > sum || sum > r) {
            return false;
        }

        int max = Arrays.stream(res).max().getAsInt();
        int min = Arrays.stream(res).min().getAsInt();
        if (max - min < x) {
            return false;
        }

        return true;
    }
}
