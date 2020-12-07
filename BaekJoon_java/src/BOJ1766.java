import java.io.*;
import java.util.*;

/**
 *  No.1766: 문제집
 *  URL: https://www.acmicpc.net/problem/1766
 *  Description: 위상정렬 알고리즘을 변형하여 사전순으로 가장 앞선 위상정렬을 찾는 문제
 *  Hint: PriorityQueue 를 활용
 */

public class BOJ1766 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<PriorityQueue<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new PriorityQueue<>());
        }

        int[] inDegree = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).offer(b);
            inDegree[b] += 1;
        }
        
        // 위상정렬
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                pq.offer(i);
            }
        }
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            sb.append(cur).append(" ");

            for (Integer o : graph.get(cur)) {
                inDegree[o] += -1;
                if (inDegree[o] == 0) {
                    pq.offer(o);
                }
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
