public class Hopscotch {
    public static void main(String[] args) {
        int[][] land = {{1, 2, 3, 5}, {5, 6, 7, 8}, {4, 3, 2, 1}};
        Hopscotch h = new Hopscotch();
        System.out.println(h.solution(land));
    }

    public int solution(int[][] land) {
        int size = land.length;
        int[][] dp = new int[size][4];

        for (int i = 0; i < size; i++) {
            if (i != 0) {
                dp[i][0] += Math.max(dp[i - 1][1], Math.max(dp[i - 1][2], dp[i - 1][3])) + land[i][0];
                dp[i][1] += Math.max(dp[i - 1][2], Math.max(dp[i - 1][3], dp[i - 1][0])) + land[i][1];
                dp[i][2] += Math.max(dp[i - 1][3], Math.max(dp[i - 1][0], dp[i - 1][1])) + land[i][2];
                dp[i][3] += Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2])) + land[i][3];
            } else {
                dp[i][0] = land[i][0];
                dp[i][1] = land[i][1];
                dp[i][2] = land[i][2];
                dp[i][3] = land[i][3];
            }
        }
        return Math.max(Math.max(Math.max(dp[size - 1][0], dp[size - 1][1]), dp[size - 1][2]), dp[size - 1][3]);
    }
}
