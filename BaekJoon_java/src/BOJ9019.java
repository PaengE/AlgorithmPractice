import java.io.*;
import java.util.*;

/**
 *  No.9019: DSLR
 *  URL: https://www.acmicpc.net/problem/9019
 *  Hint: BFS + DP + Tracing
 */

public class BOJ9019 {
    static boolean[] visited;
    static String[] result;
    static Queue<String> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String a = st.nextToken();
            String b = st.nextToken();

            bfs(a, b);

            sb.append(result[Integer.parseInt(b)] + "\n");

        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }

    static void bfs(String a, String b) {
        visited = new boolean[10000];
        result = new String[10000];
        q = new LinkedList<>();
        Arrays.fill(result, "");

        q.offer(a);
        visited[Integer.parseInt(a)] = true;

        while (!visited[Integer.parseInt(b)]) {
            String s = q.poll();
            int ns = Integer.parseInt(s);

            for (int i = 0; i < 4; i++) {
                String t = "";
                if (i == 0) {
                    t = String.valueOf((Integer.parseInt(s) * 2) % 10000);
                    while (t.length() < 4) {
                        t = "0" + t;
                    }
                } else if (i == 1) {
                    if (ns == 0) {
                        t = "9999";
                    } else {
                        t = String.valueOf(Integer.parseInt(s) - 1);
                        while (t.length() < 4) {
                            t = "0" + t;
                        }
                    }
                } else if (i == 2) {
                    while (s.length() < 4) {
                        s = "0" + s;
                    }
                    t = s.substring(1, s.length()) + s.charAt(0);
                } else if (i == 3) {
                    while (s.length() < 4) {
                        s = "0" + s;
                    }
                    t = s.charAt(s.length() - 1) + s.substring(0, s.length() - 1);
                }

                int nt = Integer.parseInt(t);
                if (!visited[nt]) {
                    q.offer(t);
                    visited[nt] = true;

                    if (i == 0) {
                        result[nt] = result[ns] + "D";
                    } else if (i == 1) {
                        result[nt] = result[ns] + "S";
                    } else if (i == 2) {
                        result[nt] = result[ns] + "L";
                    } else if (i == 3) {
                        result[nt] = result[ns] + "R";
                    }
                }
            }
        }
    }
}
