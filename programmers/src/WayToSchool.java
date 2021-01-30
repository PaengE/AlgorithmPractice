import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WayToSchool {
    static final int MOD = 1000000007;
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n + 1][m + 1];
        for (int[] t : puddles) {
            dp[t[0]][t[1]] = -1;
        }

        dp[0][1] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (dp[i][j] == -1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
                }
            }
        }
        return dp[n][m];
    }

    @Test
    public void test() {
        Assertions.assertEquals(4, solution(4, 3, new int[][]{{2, 2}}));
        Assertions.assertEquals(690285631, solution(100, 100, new int[][]{}));

    }
}
