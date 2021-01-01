import java.util.Stack;

public class MakingBiggerNumber {
    Integer max = 0;
    public static void main(String[] args) {
        String number = "9988445566";
        int k = 4;

        MakingBiggerNumber q = new MakingBiggerNumber();

        System.out.println(q.solution(number, k));

    }

    public String solution(String number, int k) {
        char[] input = number.toCharArray();

        // 스택, 작은 수 일수록 위에 존재한다.
        Stack<Character> temp = new Stack<>();
        for (int i = 0 ; i < input.length ; i++) {
            while (!temp.empty() && k > 0 && temp.peek() < input[i]) {
                k--;
                temp.pop();
            }
            temp.push(input[i]);
        }


        StringBuilder sb = new StringBuilder();
        // k개를 삭제하지 못 한 경우 뒤에서 부터 제거.
        while (!temp.empty()) {
            if (k != 0) {
                temp.pop();
                k--;
            } else {
                sb.append(temp.pop());
            }
        }
        return sb.reverse().toString();
    }


    // 완전탐색 (시간초과, 메모리초과)
    private void wrong_solution(String number, String s, int k, int index) {
        if (s.length() == number.length() - k) {
            max = Math.max(max, Integer.parseInt(s));
            return;
        }

        if (index >= number.length()) {
            return;
        }

        wrong_solution(number, s + String.valueOf(number.charAt(index)), k, index + 1);
        wrong_solution(number, s, k, index + 1);
    }
}
