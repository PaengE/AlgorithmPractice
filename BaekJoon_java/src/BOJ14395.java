import java.io.*;
import java.util.*;

/**
 *  No.14395: 4연산
 *  URL: https://www.acmicpc.net/problem/14395
 *  Hint: BFS + Set
 */

public class BOJ14395 {
    static int s, t;
    static HashSet<Integer> visited = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        if (s == t) {   // s == t
            bw.write("0");
        } else if (t == 1) {    // t == 1이면 무조건 / 하는 것이 제일 빠름
            bw.write("/");
        } else {
            Number ans = bfs(s);
            if (!visited.contains(t)) { // t에 도달하지 못하면
                bw.write("-1");
            } else {    // t에 도달 했다면
                bw.write(ans.preOps);
            }
        }

        bw.close();
        br.close();
    }

    static Number bfs(int start) {
        Queue<Number> q = new ArrayDeque<>();
        q.offer(new Number(start, ""));
        visited.add(start);

        while (!q.isEmpty()) {
            Number cur = q.poll();
            if (cur.me == t) {
                return cur;
            }

            for (int i = 0; i < 3; i++) {
                // *, +, /에 대해 숫자 처리
                long tmp = cur.me;
                if (i == 0) tmp *= tmp;
                else if (i == 1) tmp += tmp;
                else if (i == 2) tmp /= tmp;

                // t보다 크면 의미가 없는 숫자가 됨
                if (tmp > t) continue;
                // 이미 방문한 숫자일 경우
                if (visited.contains((int) tmp)) continue;

                // 사용한 명령어를 추가
                if (i == 0) q.offer(new Number((int) tmp, cur.preOps + "*"));
                else if (i == 1) q.offer(new Number((int) tmp, cur.preOps + "+"));
                else if (i == 2) q.offer(new Number((int) tmp, cur.preOps + "/"));

                // 방문 표시
                visited.add((int) tmp);
            }
        }

        return null;
    }

    static class Number{
        int me;
        String preOps;

        Number(int pre, String preOps) {
            this.me = pre;
            this.preOps = preOps;
        }
    }
}