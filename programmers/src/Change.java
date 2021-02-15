import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Change {
    public int solution(int n, int[] money) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 0; i <= n; i++) {
            dp[i] = (i % money[0] == 0) ? 1 : 0;
        }

        for (int i = 1; i < money.length; i++) {
            for (int j = money[i]; j <= n; j++) {
                dp[j] = (dp[j] + dp[j - money[i]]) % 1000000007;
            }
        }
        return dp[n];
    }

    @Test
    public void test(){
        Assertions.assertEquals(4, solution(5, new int[]{1, 2, 5}));
    }
}
