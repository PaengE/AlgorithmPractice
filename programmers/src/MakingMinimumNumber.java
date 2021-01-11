import java.util.Arrays;

public class MakingMinimumNumber {
    public static void main(String[] args) {
        int[] a = {1, 4, 2};
        int[] b = {5, 4, 4};

        MakingMinimumNumber mnn = new MakingMinimumNumber();
        mnn.solution(a, b);
    }

    public int solution(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);

        int answer = 0;
        for (int i = 0; i < a.length; i++) {
            answer += a[i] * b[b.length - i - 1];
        }

        return answer;
    }
}
