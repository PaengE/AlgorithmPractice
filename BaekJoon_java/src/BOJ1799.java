import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *  No.1799: 비숍
 *  URL: https://www.acmicpc.net/problem/1799
 *  Hint: backtracking + meet in the middle
 */

public class BOJ1799 {
    static int n;
    static int[] ans;
    static int[][] map;
    static ArrayList<Dot>[] blankDotList;

    static int[] dx = {-1, -1};   // 좌상/우상
    static int[] dy = {-1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        ans = new int[2];                   // 0: black, 1: white
        blankDotList = new ArrayList[2];    // 0: black, 1: white
        for (int i = 0; i < 2; i++) {
            blankDotList[i] = new ArrayList<>();
        }

        // 체스판의 검/흰 각각 영역의 비숍을 놓을 수 있는 자리를 list 로 구성함
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    if (isBlack(i, j)) {
                        blankDotList[0].add(new Dot(i, j));
                    } else {
                        blankDotList[1].add(new Dot(i, j));
                    }
                }
            }
        }

        backtracking(0, 0, 0);  // 체스판의 검은 영역
        backtracking(0, 0, 1);  // 체스판의 흰 영역

        bw.write(String.valueOf(ans[0] + ans[1]));
        bw.close();
        br.close();
    }

    // backtracking 을 하며 비숍을 놓음
    static void backtracking(int index, int count, int color) {
        for (int i = index; i < blankDotList[color].size(); i++) {
            Dot cur = blankDotList[color].get(i);

            if (isPossible(cur.x, cur.y)) {
                map[cur.x][cur.y] = 2;
                backtracking(i + 1, count + 1, color);
                map[cur.x][cur.y] = 1;
            }
        }

        ans[color] = Math.max(ans[color], count);
    }

    // 체스판의 검은 영역인지를 판단
    static boolean isBlack(int x, int y) {
        if (x % 2 == 0 && y % 2 == 0) {
            return true;
        }

        if (x % 2 == 1 && y % 2 == 1) {
            return true;
        }

        return false;
    }

    // 비숍을 놓을 수 있는지를 판단 (대각선 상에 비숍이 존재하는지)
    // 위에서부터 비숍을 놓으므로, 현재 위치보다 아래 행에 있는 체스판은 검사할 필요 x
    static boolean isPossible(int x, int y) {
        for (int j = 1; j <= x; j++) {
            for (int i = 0; i < 2; i++) {
                int tx = x + dx[i] * j;
                int ty = y + dy[i] * j;

                if (tx >= 0 && tx < n && ty >= 0 && ty < n) {
                    if (map[tx][ty] == 2) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static class Dot {
        int x, y;

        Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
