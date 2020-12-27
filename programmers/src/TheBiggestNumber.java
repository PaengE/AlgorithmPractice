import java.util.Arrays;

public class TheBiggestNumber {
    public static void main(String[] args) {
        int[] numbers = {121, 12};


        String s = solution(numbers);
        System.out.println("s = " + s);
    }

    static String solution(int[] numbers) {
        String answer = "";

        String[] s = new String[numbers.length];
        for (int i = 0; i < s.length; i++) {
            s[i] = String.valueOf(numbers[i]);
        }

        // 12121 과 12112를 비교해서 큰값으로 내림차순 정렬
        Arrays.sort(s, (str1, str2) -> (str2 + str1).compareTo(str1 + str2));

        return s[0].equals("0") ? s[0] : String.join("", s);
    }
}
