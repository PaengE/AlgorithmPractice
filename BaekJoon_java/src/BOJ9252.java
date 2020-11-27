import java.io.*;
import java.util.Stack;

/**
 *  No.9252: LCS 2 (Least Common Subsequence)
 *  URL: https://www.acmicpc.net/problem/9252
 *  Hint: DP Backtracking
 */

public class BOJ9252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s1 = br.readLine();
        String s2 = br.readLine();
        int len1 = s1.length();
        int len2 = s2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        for(int i = 0; i < len1; i++){
            for(int j = 0; j < len2; j++){
                if(s1.charAt(i) == s2.charAt(j)){
                    dp[i+1][j+1] = dp[i][j] + 1;
                } else {
                    dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }

        bw.write(dp[len1][len2] + "\n");

        Stack<Character> stack = new Stack<>();
        while (len1 >= 1 && len2 >= 1) {
            // s1의 len1번째와 s2의 len2번째가 다르므로 s1의 인덱스를 하나 줄임
            if (dp[len1][len2] == dp[len1 - 1][len2]) {
                len1--;
            }
            // s1의 len1번째와 s2의 len2번째가 다르므로 s2의 인덱스를 하나 줄임
            else if (dp[len1][len2] == dp[len1][len2 - 1]) {
                len2--;
            }
            // s1의 len1번째와 s2의 len2번째가 같으므로 인덱스를 하나씩 줄이고
            // 스택에 저장
            else {
                stack.push(s1.charAt(len1 - 1));
                len1--;
                len2--;
            }
        }

        // 스택에서 하나씩 뽑아서 출력
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();


    }
}
