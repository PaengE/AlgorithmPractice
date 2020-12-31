import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * no.9370: 미확인 도착지
 * description: 최단 거리 알고리즘 응용 문제
 * hint: 시작점-목적지 최단경로 >= 시작점-h(g)-g(h)-목적지 최단경로 인 목적지 찾기
 */

public class BOJ9370 {
    static int n, m, t, s, g, h;
    static ArrayList<Node9370>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            list = new ArrayList[n + 1];
            for (int j = 1; j <= n; j++) {
                list[j] = new ArrayList<Node9370>();
            }

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                list[a].add(new Node9370(b, d));
                list[b].add(new Node9370(a, d));
            }
            int[] dest = new int[t];
            for (int j = 0; j < t; j++) {
                dest[j] = Integer.parseInt(br.readLine());
            }

            // s-x 최단경로와 같은 s-g-h-x 혹은 s-h-g-x 최단경로 찾기
            PriorityQueue<Integer> qi = new PriorityQueue<>();
            for (int x : dest) {
                long res1 = dijkstra9370(s, h) + dijkstra9370(h, g) + dijkstra9370(g, x);
                long res2 = dijkstra9370(s, g) + dijkstra9370(g, h) + dijkstra9370(h, x);
                long res3 = dijkstra9370(s, x);

                if (Math.min(res1, res2) == res3) {
                    qi.offer(x);
                }
            }
            while (!qi.isEmpty()) {
                sb.append(qi.poll() + " ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static long dijkstra9370(int start, int end) {
        boolean[] check = new boolean[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node9370> q = new PriorityQueue<>();
        q.offer(new Node9370(start, 0));
        dist[start] = 0;

        while (!q.isEmpty()) {
            Node9370 curNode = q.poll();
            int curTo = curNode.end;

            if (check[curTo])
                continue;
            check[curTo] = true;

            for (Node9370 node : list[curTo]) {
                if (dist[node.end] > dist[curTo] + node.weight) {
                    dist[node.end] = dist[curTo] + node.weight;
                    q.offer(new Node9370(node.end, dist[node.end]));
                }
            }
        }
        return dist[end];
    }
}
class Node9370 implements Comparable<Node9370> {
    int end, weight;

    public Node9370(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node9370 o) {
        return weight - o.weight;
    }
}
