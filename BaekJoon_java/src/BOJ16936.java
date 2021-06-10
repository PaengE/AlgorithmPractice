import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  No.16936: 나3곱2
 *  Hint: BruteForce + Backtracking + NumberFormatException
 */

public class BOJ16936 {
    static int n, depth;
    static long[] arr, ans;
    static boolean[] used;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        ans = new long[n];
        used = new boolean[n];
        depth = 0;

        // 배열의 모든 수를 시작 숫자로 하여 시도
        for (int i = 0; i < n; i++) {
            solve(i, 0);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(ans[i] + " ");
        }

        bw.write(sb.toString().trim());
        bw.close();
        br.close();
    }

    // backtracking
    static void solve(int idx, int depth) {
        if (flag) { // 정답을 찾았는지에 관한 flag
            return;
        }

        // arr[idx] 를 사용했는지 판단
        if (!used[idx]) {
            used[idx] = true;   // 사용
            ans[depth] = arr[idx];
            if (depth == n - 1) {   // arr의 모든 수를 다 사용했다면
                flag = true;
                return;
            }

            if (ans[depth] % 3 == 0) {  // 3으로 나눠지는 경우
                for (int i = idx - 1; i >= 0; i--) {    // idx보다 작은 인덱스에는 arr[idx]이하 값만 있음
                    if (arr[i] == ans[depth] / 3) {
                        solve(i, depth + 1);
                        break;
                    }
                }
            }

            for (int i = idx + 1; i < n; i++) { // idx보다 큰 인덱스에는 arr[idx]이상 값만 있음
                if (arr[i] == ans[depth] * 2) {
                    solve(i, depth + 1);
                    break;
                }
            }

            used[idx] = false;  // 사용 해제
        }
    }
}
