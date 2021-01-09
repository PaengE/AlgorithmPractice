import java.util.Stack;

public class CorrectBracket {
    public static void main(String[] args) {
        String s = "(()(";
        CorrectBracket cb = new CorrectBracket();
        System.out.println(cb.solution(s));
    }

    public boolean solution(String s) {
        var stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }

        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
