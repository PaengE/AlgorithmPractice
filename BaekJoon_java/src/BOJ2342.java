import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *  No.2342: Dance Dance Revolution
 *  URL: https://www.acmicpc.net/problem/2342
 *  Hint: DFS + DP
 */

public class BOJ2342 {
    static int[][][] dp;
    static ArrayList<Integer> to = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        while (true) {
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }
            to.add(n);
        }

        dp = new int[to.size()][5][5];

        bw.write(String.valueOf(dfs(0, 0, 0)));
        bw.close();
        br.close();
    }

    static int dfs(int idx, int left, int right) {
        if (idx == to.size()) {
            return 0;
        }

        if (dp[idx][left][right] != 0) {
            return dp[idx][left][right];
        }

        int moveLeft = dfs(idx + 1, to.get(idx), right) + move(left, to.get(idx));
        int moveRight = dfs(idx + 1, left, to.get(idx)) + move(right, to.get(idx));
        return dp[idx][left][right] = Math.min(moveLeft, moveRight);
    }

    static int move(int from, int to) {
        if (from == 0) {    // 중앙에서 시작할 때
            return 2;
        } else if (from == to) {    // 같은 곳을 연속으로 밟을 때
            return 1;
        } else if (Math.abs(from - to) == 2) {  // 반대편으로 이동할 때
            return 3;
        } else {    // 인접한 곳으로 이동할 때
            return 4;
        } 
    }
}
