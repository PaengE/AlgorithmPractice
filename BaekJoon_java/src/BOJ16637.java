import java.io.*;

/**
 *  No.16637: 괄호 추가하기
 *  URL: https://www.acmicpc.net/problem/16637
 *  Hint: DFS + BruteForce
 */

public class BOJ16637 {
    static int[] nums;
    static char[] ops;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        nums = new int[(n / 2) + 1];
        ops = new char[n / 2];

        int numIdx = 0;
        int opIdx = 0;
        for (int i = 0; i < str.length(); i++) {
            if (i % 2 == 0) {
                nums[numIdx++] = str.charAt(i) - '0';
            } else {
                ops[opIdx++] = str.charAt(i);
            }
        }

        ans = Integer.MIN_VALUE;
        dfs(nums[0], 0);

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    static void dfs(int res, int opIdx) {
        if (opIdx >= ops.length) {
            ans = Math.max(ans, res);
            return;
        }

        // 괄호가 없는 경우를 계산
        int resNoBracket = calc(res, nums[opIdx + 1], ops[opIdx]);
        dfs(resNoBracket, opIdx + 1);

        // 괄호가 있는 경우를 계산
        if (opIdx + 1 < ops.length) {
            // res 의 오른쪽의 A op B 를 계산
            int resWithBracket = calc(nums[opIdx + 1], nums[opIdx + 2], ops[opIdx + 1]);
            dfs(calc(res, resWithBracket, ops[opIdx]), opIdx + 2);
        }
    }

    static int calc(int n1, int n2, char c) {
        if (c == '+') {
            return n1 + n2;
        } else if (c == '-') {
            return n1 - n2;
        } else {
            return n1 * n2;
        }
    }
}
