import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class Monthly2 {
    public int solution(String s) {
        String str = s + s.substring(0, s.length() - 1);

        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isCorrect(str, i, s.length() + i)) {
                ans++;
            }
        }

        return ans;
    }

    static boolean isCorrect(String s, int start, int end) {
        Stack<Character> stk = new Stack<>();

        for (int i = start; i < end; i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                stk.push(c);
            } else {
                if (stk.isEmpty()) {
                    return false;
                }

                if (c == '}') {
                    if (stk.peek() == '{') {
                        stk.pop();
                    } else {
                        return false;
                    }
                } else if (c == ')') {
                    if (stk.peek() == '(') {
                        stk.pop();
                    } else {
                        return false;
                    }
                } else if (c == ']') {
                    if (stk.peek() == '[') {
                        stk.pop();
                    } else {
                        return false;
                    }
                }
            }
        }

        if (!stk.isEmpty()) {
            return false;
        }

        return true;
    }

    @Test
    public void test() {
        Assertions.assertEquals(3, solution("[](){}"));
        Assertions.assertEquals(2, solution("}]()[{"));
        Assertions.assertEquals(0, solution("[)(]"));
        Assertions.assertEquals(0, solution("}}}"));
    }
}
