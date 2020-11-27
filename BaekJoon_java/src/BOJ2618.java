import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  No.2618: 경찰차
 *  URL: https://www.acmicpc.net/problem/2618
 *  Hint: dp[x][y] 는 경찰차 1이 최근에 처리한 사건이 x, 
 *        경찰차 2가 최근에 처리한 사건이 y일 때 남은 최소이동거리
 */

public class BOJ2618 {
    static int n, w;
    static int[][] dp;      // 남은 최소 이동거리를 표시
    static int[][] choose;  // 다음 사건을 어떤 경찰차가 맡았는지를 표시
    static Point[] first, second;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        w = Integer.parseInt(br.readLine());
        first = new Point[w + 1];
        second = new Point[w + 1];
        choose = new int[w + 1][w + 1];
        dp = new int[w + 1][w + 1];

        for (int[] t : dp) {
            Arrays.fill(t, -1);
        }
        first[0] = new Point(1, 1);
        second[0] = new Point(n, n);

        for (int i = 1; i <= w; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            first[i] = new Point(a, b);
            second[i] = new Point(a, b);
        }

        sb.append(solve(0, 0) + "\n");

        for (int x = 0, y = 0; Math.max(x, y) + 1 <= w; ) {
            sb.append((choose[x][y] + 1) + "\n");
            
            // 2번이 맡았다면
            if (choose[x][y] == 1) {
                y = Math.max(x, y) + 1;
            }
            // 1번이 맡았다면
            else {
                x = Math.max(x, y) + 1;
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    static int solve(int x, int y) {
        int here = Math.max(x, y) + 1;

        if (here == w + 1) {
            return 0;
        }
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        // 어떤 경찰차가 사건을 맡는게 더 거리가 짧은지를 구함
        int select1 = solve(here, y) + getDistance(first[here], first[x]);
        int select2 = solve(x, here) + getDistance(second[y], second[here]);

        // 2번을 고르는게 더 작다면 2번을 골랐다고 표시
        if (select2 < select1) {
            choose[x][y] = 1;
        }
        
        // 더 짧은거리 값을 저장 후 리턴
        return dp[x][y] = Math.min(select1, select2);
    }

    
    // 경찰차의 위치부터 사건 발생지까지의 거리를 구하는 메소드
    static int getDistance(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    static class Point {
        public int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
