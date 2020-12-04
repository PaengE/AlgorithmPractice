import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  No.14226: 이모티콘
 *  URL: https://www.acmicpc.net/problem/14226
 *  Hint: BFS + DP -> visited[이모티콘길이][버퍼의길이]
 */

public class BOJ14226 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        boolean[][] visited = new boolean[n * 2 + 1][n * 2 + 1];

        Queue<Emo> q = new LinkedList<>();

        q.offer(new Emo(1, 0, 0));

        while (!q.isEmpty()) {
            Emo cur = q.poll();
//            System.out.println(cur.len + " " + cur.buf + " " + cur.time);

            if (cur.len == n) {
                bw.write(String.valueOf(cur.time));
                bw.close();
                br.close();
                System.exit(0);
            }

            if (visited[cur.len][cur.buf]) {
                continue;
            }
            visited[cur.len][cur.buf] = true;

            // copy
            q.offer(new Emo(cur.len, cur.len, cur.time + 1));

            // paste
            if (cur.buf != 0 && cur.len + cur.buf <= n) {
                q.offer(new Emo(cur.len + cur.buf, cur.buf, cur.time + 1));
            }

            // minus 1
            if (cur.len > 0 && cur.len - 1 <= n) {
                q.offer(new Emo(cur.len - 1, cur.buf, cur.time + 1));
            }
        }
    }

    static class Emo {
        int len, buf, time;

        // (이모티콘의 길이, 버퍼의 길이, 걸린 최소 시간
        Emo(int len, int buf, int time) {
            this.len = len;
            this.buf = buf;
            this.time = time;
        }
    }
}
