import java.io.*;
import java.util.Stack;

/**
 *  No.1918: 후위 표기식
 *  URL: https://www.acmicpc.net/problem/1918
 *  Hint: Stack
 */

public class BOJ1918 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        Stack<Character> stk = new Stack<>();

        for (Character c : s.toCharArray()) {
            if (c >= 'A' && c <= 'Z') { // 알파벳이면 그대로 출력
                sb.append(c);
            } else if (c == '(') {  // 여는 괄호이면
                stk.push(c);
            } else if (c == ')') {  // 닫는 괄호이면
                while (!stk.isEmpty()) {    // 여는 괄호가 나올 때까지 수행ㄱ
                    if (stk.peek() == '(') {
                        stk.pop();
                        break;
                    }
                    sb.append(stk.pop());
                }
            } else {
                while (!stk.isEmpty() && priority(stk.peek()) >= priority(c)) {
                    sb.append(stk.pop());
                }
                stk.push(c);
            }
        }

        while (!stk.isEmpty()) {
            sb.append(stk.pop());
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    // 연산자 우선순위
    static int priority(char c) {
        if (c == '(') {
            return 0;
        } else if (c == '+' || c == '-') {
            return 1;
        } else {
            return 2;
        }
    }
}
