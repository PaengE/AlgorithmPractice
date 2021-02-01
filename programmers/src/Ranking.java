import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Ranking {
    static final int MAX = 10000;
    public int solution(int n, int[][] result) {
        int[][] dp = new int[n + 1][n + 1];

        // DP 배열 초기화
        for (int[] t : dp) {
            Arrays.fill(t, MAX);
        }
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 0;
        }

        // 경기 결과를 반영(i = win, j = loss)
        for (int[] t : result) {
            dp[t[0]][t[1]] = 1;
        }

        // Floyd-Warshall 알고리즘
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }

        // i 의 등수를 확정 지을 수 있는지 판단
        int ans = n;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    continue;
                }

                if (dp[i][j] == MAX && dp[j][i] == MAX) {
                    ans--;
                    break;
                }
            }
        }
        return ans;
    }

    @Test
    public void test() {
        Assertions.assertEquals(2, solution(5, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}));
        Assertions.assertEquals(5, solution(5, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}}));
        Assertions.assertEquals(0, solution(5, new int[][]{{1, 2}, {2, 3}, {3, 4}}));
    }
}
