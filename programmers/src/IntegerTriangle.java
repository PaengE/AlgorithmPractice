import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class IntegerTriangle {
    public int solution(int[][] triangle) {
        int size = triangle.length;
        int[][] dp = new int[size][size];
        dp[0][0] = triangle[0][0];

        for (int i = 1; i < size; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) {
                    dp[i][j] += dp[i - 1][j] + triangle[i][j];
                } else if (i == j) {
                    dp[i][j] += dp[i - 1][j - 1] + triangle[i][j];
                } else {
                    dp[i][j] += Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + triangle[i][j];
                }
            }
        }

        Arrays.sort(dp[size - 1]);
        return dp[size - 1][size - 1];
    }

    @Test
    public void test() {
        Assertions.assertEquals(30, solution(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}}));
    }
}
