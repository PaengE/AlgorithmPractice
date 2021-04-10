import java.io.*;
import java.util.Arrays;

/**
 *  No.1509: 팰린드롬 분할
 *  URL: https://www.acmicpc.net/problem/1509
 *  Hint: i ~ j 문자열이 팰린드롬인지를 판단한 후, 그것을 응용
 */

public class BOJ1509 {
    static String str;
    static int size;
    static boolean[][] palindrome;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        str = br.readLine();
        size = str.length();
        palindrome = new boolean[size + 1][size + 1];
        dp = new int[size + 1];

        checkPalindrome();
        setDP();

        bw.write(String.valueOf(dp[size]));
        bw.close();
        br.close();
    }

    // DP[i] 는 0 ~ i 까지의 문자열으로 만들 수 있는 팰린드롬 분할의 개수의 최솟값
    static void setDP() {
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int b = 1; b <= size; b++) {
            for (int a = 1; a <= b; a++) {
                if (palindrome[a][b]) {
                    dp[b] = Math.min(dp[b], dp[a - 1] + 1);
                }
            }
        }
    }

    // i ~ j 길이의 문자열이 팰린드롬인지를 판단하는 메소드
    static void checkPalindrome() {
        for (int i = 1; i <= size; i++) {
            for (int j = i; j <= size; j++) {
                boolean isSame = true;

                int from = i - 1;
                int to = j - 1;
                while (from <= to) {
                    if (str.charAt(from++) != str.charAt(to--)) {
                        isSame = false;
                        break;
                    }
                }

                if (isSame) {
                    palindrome[i][j] = true;
                }
            }
        }
    }
}
