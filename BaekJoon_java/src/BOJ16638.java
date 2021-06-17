import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 *  No.16638: 괄호 추가하기 2
 *  Hint: Backtracking + (중위표기 -> 후위표기) + 구현 + 우선순위
 */

public class BOJ16638 {
    static int n, ans = Integer.MIN_VALUE;
    static boolean[] brackets;  // 괄호를 표시하는 배열
    static Formula[] input; // 수식의 문자와 우선순위를 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        brackets = new boolean[n];
        input = new Formula[n];

        String s = br.readLine();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '*') {
                input[i] = new Formula(c, 2);
            } else if (c == '+' || c == '-') {
                input[i] = new Formula(c, 3);
            } else {
                input[i] = new Formula(c, 0);
            }
        }

        makeBrackets(1);
        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }

    // backtracking 으로 괄호를 생성함
    static void makeBrackets(int idx) {
        if (idx >= n) {
            int res = postfixToResult(infixToPostfix());
            ans = Math.max(ans, res);
            return;
        }

        if (idx == 1) { // 첫 번째 연산자일 경우
            brackets[idx] = true;
            makeBrackets(idx + 2);
            brackets[idx] = false;
        } else {
            if (!brackets[idx - 2]) {   // 이전에 괄호가 없을 경우에만
                brackets[idx] = true;
                makeBrackets(idx + 2);
                brackets[idx] = false;
            }
        }
        makeBrackets(idx + 2);
    }

    // 우선순위를 기준으로 중위표기를 후위표기로 변환
    static ArrayList<Formula> infixToPostfix() {
        ArrayList<Formula> postfix = new ArrayList<>();
        Stack<Formula> stk = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (input[i].priority == 0) {   // 피연산자
                postfix.add(input[i]);
            } else {    // 연산자
                int priority = input[i].priority;
                if (brackets[i]) {  // 괄호를 씌운 연산자라면 우선순위를 변경
                    priority = 1;
                }

                while (!stk.isEmpty() && stk.peek().priority <= priority) {
                    postfix.add(stk.pop());
                }

                stk.push(new Formula(input[i].character, priority));
            }
        }

        while (!stk.isEmpty()) {
            postfix.add(stk.pop());
        }
        return postfix;
    }

    // 후위표기된 수식을 계산
    static int postfixToResult(ArrayList<Formula> postfix) {
        Stack<Integer> stk = new Stack<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (postfix.get(i).priority == 0) { // 피연산자
                stk.push(postfix.get(i).character - '0');
            } else {    // 연산자
                int n1 = stk.pop();
                int n2 = stk.pop();
                res = calculate(n2, n1, postfix.get(i).character);
                stk.push(res);
            }
        }
        return stk.pop();
    }

    // 연산자 계산
    static int calculate(int n1, int n2, char op) {
        int res = 0;
        switch (op) {
            case '+':
                res = n1 + n2;
                break;
            case '-':
                res = n1 - n2;
                break;
            case '*':
                res = n1 * n2;
                break;
        }
        return res;
    }

    static class Formula{
        char character;
        int priority;   // 숫자:0, +-:3, *:2, 괄호:1

        Formula(char op, int priority) {
            this.character = op;
            this.priority = priority;
        }
    }
}
