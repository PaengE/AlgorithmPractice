import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  No.18513: 쉼터
 *  URL: https://www.acmicpc.net/problem/18513
 *  Hint: BFS + 범위 주의
 */

public class BOJ18513 {
    static int n, k;
    static final int ZERO_INDEX = 100050000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        String s = br.readLine();
        bw.write(String.valueOf(bfs(s)));
        bw.close();
        br.close();
    }

    static long bfs(String s) {
        String[] str = s.split(" ");
        boolean[] visited = new boolean[200100001]; // index 100050000 이 0
        int[] dx = {-1, 1};
        long ans = 0;

        Queue<Number> q = new LinkedList<>();
        for (int i = 0; i < str.length; i++) {
            int t = Integer.parseInt(str[i]);
            visited[t + ZERO_INDEX] = true;
            q.offer(new Number(t + ZERO_INDEX, t + ZERO_INDEX));
        }

        int end = 0;
        while (true){
            Number cur = q.poll();

            for (int j = 0; j < 2; j++) {
                if (end == k) {
                    return ans;
                }
                int nx = cur.loc + dx[j];

                if (isPossible(nx) && !visited[nx]) {
                    q.offer(new Number(cur.standard, nx));
                    visited[nx] = true;
                    ans += Math.abs(cur.standard - nx);
                    end++;
                }
            }
        }
    }

    static boolean isPossible(int idx) {
        if (idx >= 0 && idx <= 200100001) {
            return true;
        } else {
            return false;
        }
    }

    static class Number {
        int standard, loc;

        Number(int standard, int loc) {
            this.standard = standard;
            this.loc = loc;
        }
    }
}
