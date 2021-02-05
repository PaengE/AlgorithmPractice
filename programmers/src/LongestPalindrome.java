import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongestPalindrome {
    public int solution(String s) {
        int answer = 1;
        for (int i = 0; i < s.length() - 1; i++) {
            // 팰린드롬 부분문자열의 길이가 홀수일 경우
            answer = Math.max(answer, findLongestPalindrome(i, i, s));
            // 팰린드롬 부분문자열의 길이가 짝수일 경우
            answer = Math.max(answer, findLongestPalindrome(i, i + 1, s));
        }
        return answer;
    }

    // 어느 한 지점부터 늘려가면서 팰린드롬의 길이 구하는 메소드
    private int findLongestPalindrome(int left, int right, String s) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    @Test
    public void test(){
        Assertions.assertEquals(7, solution("abcdcba"));
        Assertions.assertEquals(3, solution("abacde"));
    }
}
