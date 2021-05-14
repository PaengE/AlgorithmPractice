import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Monthly5 {
    public int solution(int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            int cnt = 0;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    cnt++;
                }
            }

            if (cnt % 2 == 0) {
                ans += i;
            } else {
                ans -= i;
            }
        }

        return ans;
    }

    @Test
    public void test() {
        Assertions.assertEquals(43, solution(13, 17));
        Assertions.assertEquals(52, solution(24, 27));
    }
}