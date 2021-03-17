import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  No.15654: N과 M(5)
 *  URL: https://www.acmicpc.net/problem/15654
 *  Hint: Backtracking or DFS
 */

public class BOJ15654 {
    static int[] arr, res;
    static boolean[] used;
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        res = new int[m];
        used = new boolean[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        backtracking(0);

        bw.write(sb.toString());
        bw.close();
        br.close();

    }

    static void backtracking(int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(res[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                used[i] = true;
                res[depth] = arr[i];
                backtracking(depth + 1);
                used[i] = false;
            }
        }
    }
}
