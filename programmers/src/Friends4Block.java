public class Friends4Block {
    static char[][] map;
    public static void main(String[] args) {
        Friends4Block fb = new Friends4Block();
//        int m = 6;
//        int n = 6;
//        String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};

        int m = 4;
        int n = 5;
        String[] board = {"AAAAA", "AUUUA", "AUUAA", "AAAAA"};

        System.out.println(fb.solution(m, n, board));
    }

    public int solution(int m, int n, String[] board) {
        map = new char[m][n];
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        int answer = 0;
        while (true) {
            int cnt = checkBlock(m, n);
            if (cnt == 0) {
                break;
            }
            answer += cnt;
            dropBlock(m, n);
        }

        return answer;
    }

    public int checkBlock(int m, int n) {
        boolean[][] dp = new boolean[m][n];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (map[i][j] != '.') {
                    char cur = map[i][j];
                    if (cur == map[i - 1][j - 1] && cur == map[i - 1][j] && cur == map[i][j - 1]) {
                        dp[i - 1][j - 1] = true;
                        dp[i - 1][j] = true;
                        dp[i][j - 1] = true;
                        dp[i][j] = true;
                    }
                }
            }
        }

        return countBlock(m, n, dp);
    }

    public int countBlock(int m, int n, boolean[][] dp) {
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j]) {
                    count += 1;
                    map[i][j] = '.';
                }
            }
        }

        return count;
    }

    public void dropBlock(int m, int n) {
        for (int i = m - 1; i > 0; i--) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '.') {
                    for (int k = i - 1; k >= 0; k--) {
                        if (map[k][j] != '.') {
                            map[i][j] = map[k][j];
                            map[k][j] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }
}
