import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class NumberGame {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int idx = 0;
        int answer = 0;

        for (int i = 0; i < B.length; i++) {
            if (A[idx] < B[i]) {
                answer++;
                idx++;
            }
        }
        return answer;
    }

    @Test
    public void test() {
        Assertions.assertEquals(3, solution(new int[]{5, 1, 3, 7}, new int[]{2, 2, 6, 8}));
        Assertions.assertEquals(0, solution(new int[]{2, 2, 2, 2}, new int[]{1, 1, 1, 1}));
    }
}
