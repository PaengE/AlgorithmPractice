import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *  No.2493: 탑
 *  URL: https://www.acmicpc.net/problem/2493
 *  Hint: Stack을 활용한 문제
 */

public class BOJ2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stk = new Stack<>();
        int[] ans = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            if (stk.isEmpty()) {
                stk.push(i);
                continue;
            }

            while (!stk.isEmpty() && arr[i] >= arr[stk.peek()]) {
                ans[stk.pop()] = i;
            }
            stk.push(i);
        }

        while (!stk.isEmpty()) {
            ans[stk.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int a : ans) {
            sb.append((a + 1) + " ");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
