import java.io.*;
import java.util.*;

/**
 *  No.1005: ACM Craft
 *  URL: https://www.acmicpc.net/problem/1005
 *  Hint: DAG(Directed Acyclic Graph: 방향성이 있고 사이클이 없는 그래프) + DP
 */

public class BOJ1005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[] d = new int[n + 1];
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            graph.add(new ArrayList<>());
            for (int j = 1; j <= n; j++) {
                d[j] = Integer.parseInt(st.nextToken());
                graph.add(new ArrayList<Integer>());
            }

            int[] inDegree = new int[n + 1];
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph.get(x).add(y);
                inDegree[y]++;
            }

            int w = Integer.parseInt(br.readLine());

            Queue<Integer> q = new LinkedList<>();
            int[] dp = new int[n + 1];

            // inDegree 가 0인 것들을 enqueue
            for (int j = 1; j <= n; j++) {
                dp[j] = d[j];

                if (inDegree[j] == 0) {
                    q.offer(j);
                }
            }

            // 위상정렬
            while (!q.isEmpty()) {
                int node = q.poll();

                for (Integer o : graph.get(node)) {
                    dp[o] = Math.max(dp[o], dp[node] + d[o]);
                    inDegree[o]--;

                    if (inDegree[o] == 0) {
                        q.offer(o);
                    }
                }
            }
//            for (int j = 0; j <= n; j++) {
//                System.out.print(dp[j] + " ");
//            }
//            System.out.println();

            sb.append(dp[w] + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
