import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PedestrianParadise {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] city_map) {
        // 1 x 1 이면 1 반환
        if (m == 1 && n == 1) {
            return 1;
        }

        int[][][] dp = new int[m][n][2];    // 0: 세로방향, 1: 가로방향

        // 첫번째 열 초기화
        for (int i = 0; i < m; i++) {
            if (city_map[i][0] != 1) {
                dp[i][0][0] = 1;
            }
        }
        // 촛번째 행 초기화
        for (int i = 0; i < n; i++) {
            if (city_map[0][i] != 1) {
                dp[0][i][1] = 1;
            }
        }


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n ; j++) {
                if (city_map[i][j] == 1) {
                    continue;
                }

                // 세로방향 도로 검사
                if (city_map[i - 1][j] == 0) {
                    dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                } else if (city_map[i - 1][j] == 1) {
                    // nothing to do
                } else {
                    dp[i][j][0] = (dp[i][j][0] + dp[i - 1][j][0]) % MOD;
                }

                // 가로방향 도로 검사
                if (city_map[i][j - 1] == 0) {
                    dp[i][j][1] = (dp[i][j][1] + dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                } else if (city_map[i][j - 1] == 1) {
                    // nothing to do
                } else {
                    dp[i][j][1] = (dp[i][j][1] + dp[i][j - 1][1]) % MOD;
                }
            }
        }

        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }

    @Test
    public void test() {
        Assertions.assertEquals(6, solution(3, 3, new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
        Assertions.assertEquals(2, solution(3, 6, new int[][]{{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}}));
    }
}
