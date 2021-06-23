import java.io.*;
import java.util.Stack;

/**
 *  No.6198: 옥상 정원 꾸미기
 *  Hint: Stack
 */

public class BOJ6198 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        bw.write(solve(n, arr));
        bw.close();
        br.close();
    }

    static String solve(int n, int[] arr) {
        long ans = 0L;
        Stack<Building> stk = new Stack<>();    // 스택은 빌딩 높이 순 내림차순을 유지해야 함.

        for (int i = 0; i < n; i++) {
            while (!stk.isEmpty() && stk.peek().height <= arr[i]) {
                Building now = stk.pop();
                ans += (i - now.idx - 1);
            }

            stk.push(new Building(i, arr[i]));
        }

        // 스택에 남아 있는 빌딩들을 처리
        while (!stk.isEmpty()) {
            ans += (n - 1) - stk.pop().idx;
        }

        return String.valueOf(ans);
    }

    static class Building{
        int idx, height;

        Building(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }
}
