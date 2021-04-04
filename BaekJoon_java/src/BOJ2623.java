import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  No.2523: 음악프로그램
 *  URL: https://www.acmicpc.net/problem/2623
 *  Hint: 위상정렬 + 사이클 판별
 */

public class BOJ2623 {
    static int n, m;
    static ArrayList<Integer>[] list;
    static int[] inDegree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        inDegree = new int[n + 1];
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            for (int j = 1; j < t; j++) {
                int to = Integer.parseInt(st.nextToken());
                list[from].add(to);
                inDegree[to]++;
                from = to;
            }
        }

        String ans = topologicalSort();
        bw.write(ans);
        bw.close();
        br.close();
    }

    static String topologicalSort() {
        Queue<Integer> q = new LinkedList<>();

        // inDegree 가 0인 노드 큐에 삽입
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        // 일반적인 위상정렬
        while (!q.isEmpty()) {
            int cur = q.poll();
            res.add(cur);

            for (int next : list[cur]) {
                if (--inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        // res 의 크기가 n이 아니면 사이클이 존재함 (사이클이 존재하면 inDegree 값이 0이 되지 못함
        if (res.size() != n) {
            return "0";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < res.size(); i++) {
                sb.append(res.get(i) + "\n");
            }
            return sb.toString();
        }
    }
}
