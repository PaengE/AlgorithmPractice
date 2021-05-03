import java.io.*;
import java.util.ArrayList;

/**
 *  No.1248: 맞춰봐
 *  URL: https://www.acmicpc.net/problem/1248
 *  Hint: BruteForce(Backtracking) + TL 처리
 */

public class BOJ1248 {
    static int n;
    static boolean findAnswer;
    static int[] ans;
    static char[][] s;
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        init(str);

        // Backtracking
        backtracking(0);

        for (int i = 0; i < n; i++) {
            bw.write(ans[i] + " ");
        }
        bw.close();
        br.close();
    }

    static void backtracking(int depth) {
        if (depth == n) {
            findAnswer = true;
            return;
        }

        for (int i = 0; i < 21; i++) {
            if (findAnswer) {   // 정답인 걸 하나 찾으면 종료
                return;
            }

            ans[depth] = list.get(i);
            // 검사를 이 단계에서 해야만 TL을 피할 수 있다.
            if (isCorrect(depth)) {
                backtracking(depth + 1);
            }
        }
    }

    // 만든 수열이 유효한 지 주어진 문자열과 비교
    static boolean isCorrect(int idx){
        for (int i = 0; i <= idx; i++) {
            int sum = 0;
            for (int j = i; j <= idx; j++) {
                sum += ans[j];
                char op = sum == 0 ? '0' : (sum > 0 ? '+' : '-');
                if (s[i][j] != op) {
                    return false;
                }
            }
        }
        return true;
    }

    // 메모리 초기화
    static void init(String str) {
        s = new char[n][n];
        ans = new int[n];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                s[i][j] = str.charAt(idx++);
            }
        }

        for (int i = -10; i <= 10; i++) {
            list.add(i);
        }
    }
}
