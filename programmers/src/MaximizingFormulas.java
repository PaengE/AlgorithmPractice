import java.util.ArrayList;

public class MaximizingFormulas {
    public static void main(String[] args) {
        String exp = "50*6-3*2";
        MaximizingFormulas mf = new MaximizingFormulas();
        System.out.println(mf.solution(exp));
    }

    public long solution(String exp) {
        ArrayList<Long> nums = new ArrayList<>();
        ArrayList<Character> ops = new ArrayList<>();
        char[][] priority = {{'+', '-', '*'},
                {'+', '*', '-'},
                {'-', '+', '*'},
                {'-', '*', '+'},
                {'*', '+', '-'},
                {'*', '-', '+'}};

        // 문자열 해석(숫자는 nums, 연산자는 ops)
        String num = "";
        for (int i=0; i<exp.length(); i++) {
            if (exp.charAt(i) >= '0' && exp.charAt(i) <= '9') {
                num += exp.charAt(i);
            } else {
                nums.add(Long.parseLong(num));
                num = "";
                ops.add(exp.charAt(i));
            }
        }
        // 마지막 숫자
        nums.add(Long.parseLong(num));

        Long answer = 0L;

        // 연산자 3개로는 최대 6가지 우선순위를 매길 수 있음
        for (int i = 0; i < 6; i++) {
            ArrayList<Long> copiedNums = new ArrayList<>(nums);
            ArrayList<Character> copiedOps = new ArrayList<>(ops);
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < copiedOps.size(); k++) {
                    // 현재 우선순위 연산자이면
                    if (priority[i][j] == copiedOps.get(k)) {
                        // copiedNums 에서 숫자 두개를 뺀 후 op로 계산
                        Long res = calc(copiedNums.remove(k), copiedNums.remove(k), priority[i][j]);
                        // 계산된 숫자를 copiedNums 에 같은 위치에 추가
                        copiedNums.add(k, res);
                        // 계산한 op를 삭제
                        copiedOps.remove(k);
                        k--;    // copiedOps 의 size 가 1 줄어드니 k도 1줄임
                    }
                }
            }
            answer = Math.max(answer, Math.abs(copiedNums.get(0)));
        }
        return answer;
    }

    static Long calc(Long n1, Long n2, char op) {
        switch (op) {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
        }
        return 0L;
    }
}
