public class TheBiggestSquare {
    public static void main(String[] args) {
        int[][] board = {{0, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 0, 1, 0}};

//        int[][] board = {{0, 0, 1, 1}, {1, 1, 1, 1}};

        TheBiggestSquare tbs = new TheBiggestSquare();
        System.out.println(tbs.solution(board));

    }

    public int solution(int[][] board) {
        int row = board.length;
        int col = board[0].length;
        int max = 0;
        int[][] dp = new int[row][col];

        for (int i = 0; i < row; i++) {
            if (board[i][0] == 1) {
                dp[i][0] = 1;
                max = 1;
            }
        }
        for (int i = 0; i < col; i++) {
            if (board[0][i] == 1) {
                dp[0][i] = 1;
                max = 1;
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == 1) {
                    dp[i][j] = 1;
                    if (board[i - 1][j - 1] == 1 && board[i - 1][j] == 1 && board[i][j - 1] == 1) {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return (max * max);
    }
}
