public class StringCompression {
    public static void main(String[] args) {
        String s = "xxxxxxxxxxyyy";

        System.out.println(solution(s));
    }

    static int solution(String s) {
        int size = s.length() / 2;
        int length = s.length();
        int answer = s.length();

        // 단위를 1 부터 s.length / 2까지 반복(s.length / 2 보다 크게 정하는건 무의미)
        for (int i = 1; i <= size; i++) {
            String pre = s.substring(0, i);
            int subLength = 0;
            int count = 1;
            int j = 0;

            // 나눌 수 있을때까지 나누면서 비교
            for (j = i; j + i <= length; j += i) {
                String cur = s.substring(j, j + i);
                if (pre.equals(cur)) {
                    count += 1;
                } else {
                    pre = cur;
                    if (count == 1) {
                        subLength += i;
                    } else {
                        subLength += i + String.valueOf(count).length();
                    }
                    count = 1;
                }
            }

            // 마지막으로 나눠진 문자열 처리
            if (count == 1) {
                subLength += i;
            } else {
                subLength += i + String.valueOf(count).length();
            }

            // 문자열의 마지막이 완벽하게 나눠지지 않은 부분 처리
            if (j != length) {
                subLength += length - j;
            }

            // 최소 길이를 저장
            answer = Math.min(answer, subLength);
        }

        return answer;
    }
}
