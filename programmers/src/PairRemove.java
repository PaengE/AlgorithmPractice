import java.util.Stack;

public class PairRemove {
    public static void main(String[] args) {
        PairRemove pr = new PairRemove();
        String s = "baabaa";
        System.out.println(pr.solution(s));

    }

    public int solution(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty() || stack.peek() != s.charAt(i)) {
                stack.push(s.charAt(i));
            } else if (stack.peek() == s.charAt(i)) {
                stack.pop();
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }
}
