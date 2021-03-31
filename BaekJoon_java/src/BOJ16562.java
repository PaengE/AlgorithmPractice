import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  No.16562: 친구비
 *  URL: https://www.acmicpc.net/problem/16562
 *  Hint: Union-find or BFS/DFS 로 풀이 가능 (속도면에선 BFS/DFS가 더 빠를듯)
 */

public class BOJ16562 {
    static int n, m, k;
    static boolean[] visited;
    static int[] cost;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        init();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        int totalCost = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                totalCost += bfs(i);
            } else {
                continue;
            }

            if (totalCost > k) {
                break;
            }
        }

        if (totalCost > k) {
            bw.write("Oh no");
        } else {
            bw.write(String.valueOf(totalCost));
        }
        bw.close();
        br.close();


    }

    static void init() {
        cost = new int[n + 1];
        visited = new boolean[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    // 같은 그룹에서 가장 싼 친구비를 리턴
    static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        int minCost = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int cur = q.poll();
            visited[cur] = true;
            minCost = Math.min(minCost, cost[cur]);

            for (int next : graph[cur]) {
                if (!visited[next]) {
                    q.offer(next);
                }
            }
        }

        return minCost;
    }
}
