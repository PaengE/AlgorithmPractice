import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TheBestSet {
    public int[] solution(int n, int s) {
        if (s < n) {
            return new int[]{-1};
        }

        int[] ans = new int[n];
        int value = s / n;
        int rest = s % n;

        for (int i = 0; i < n - rest; i++) {
            ans[i] = value;
        }
        for (int i = n - rest; i < n; i++) {
            ans[i] = value + 1;
        }

        return ans;
    }

    @Test
    public void test() {
        Assertions.assertArrayEquals(new int[]{4, 5}, solution(2, 9));
        Assertions.assertArrayEquals(new int[]{-1}, solution(2, 1));
        Assertions.assertArrayEquals(new int[]{4, 4}, solution(2, 8));
        Assertions.assertArrayEquals(new int[]{1}, solution(1, 1));
        Assertions.assertArrayEquals(new int[]{5}, solution(1, 5));
        Assertions.assertArrayEquals(new int[]{3,3,3}, solution(3, 9));
        Assertions.assertArrayEquals(new int[]{3,3,4}, solution(3, 10));
        Assertions.assertArrayEquals(new int[]{3,4,4}, solution(3, 11));
        Assertions.assertArrayEquals(new int[]{4,4,4}, solution(3, 12));
    }
}
