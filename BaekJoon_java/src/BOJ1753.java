import java.io.*;
import java.util.*;

/**
 * no.1753: 최단경로
 * description: 다익스트라 알고리즘을 배우는 문제
 * hint: dijkstra algorithm 사용
 */

public class BOJ1753 {
    static int v, e, k;
    static ArrayList<Node>[] list;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());

        list = new ArrayList[v + 1];
        dist = new int[v + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 1; i <= v; i++) {
            list[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list[start].add(new Node(end, weight));
        }

        StringBuilder sb = new StringBuilder();

        dijkstra(k);

        for (int i = 1; i <= v; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                sb.append("INF\n");
            } else {
                sb.append(dist[i] + "\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    static void dijkstra(int start) {
        // 초기 시작노드
        PriorityQueue<Node> q = new PriorityQueue<Node>();
        boolean[] check = new boolean[v + 1];
        q.add(new Node(start, 0));
        dist[start] = 0;

        // 각 노드까지 가는 최단 경로 검사
        while (!q.isEmpty()) {
            Node curNode = q.poll();
            int cur = curNode.end;

            // 현재 노드를 방문했다면 continue
            if (check[cur])
                continue;
            check[cur] = true;

            // 현재 노드에서 연결된 노드로 가기까지의 최단경로 계산
            for (Node node : list[cur]) {
                if (dist[node.end] > dist[cur] + node.weight) {
                    dist[node.end] = dist[cur] + node.weight;
                    q.add(new Node(node.end, dist[node.end]));
                }
            }
        }
    }
}

class Node implements Comparable<Node> {
    int end, weight;

    public Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return weight - o.weight;
    }
}