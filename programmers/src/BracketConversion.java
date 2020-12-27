import java.util.Stack;

public class BracketConversion {
    public static void main(String[] args) {
        String s = ")()()()(";

        System.out.println("solution(s) = " + solution(s));
    }

    static String solution(String p) {
        String answer = "";

        // 1단계
        if (p.isEmpty() || checkComplete(p)) {
            return p;
        }

        // 2단계
        int sep = divide(p);
        String u = p.substring(0, sep);
        String v = p.substring(sep, p.length());

        // 3단계
        if (checkComplete(u)) {
            answer += u + solution(v);
        }

        // 4단계
        else {
            String t = "(" + solution(v) + ")";
            answer += t + replace(u.substring(1, u.length() - 1));
        }

        return answer;
    }

    static boolean checkComplete(String p) {
        Stack<Character> stack = new Stack<>();
        int size = p.length();

        for (int i = 0; i < size; i++) {
            switch (p.charAt(i)) {
                case '(':
                    stack.push('(');
                    break;
                case ')':
                    if (stack.isEmpty()) {
                        return false;
                    } else {
                        stack.pop();
                    }
                    break;
            }
        }

        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    static int divide(String p) {
        int size = p.length();
        int left_bracket = 0;
        int right_bracket = 0;

        for (int i = 0; i < size; i++) {
            if (p.charAt(i) == '(') {
                left_bracket += 1;
            } else {
                right_bracket += 1;
            }

            if (left_bracket == right_bracket) {
                break;
            }
        }

        return left_bracket + right_bracket;
    }

    static String replace(String p) {
        String s = "";
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                s += ")";
            } else {
                s += "(";
            }
        }
        return s;
    }
}
