import java.io.*;
import java.util.*;

/**
 *  No.1167: 트리의 지름
 *  URL: https://www.acmicpc.net/problem/1167
 *  Description: BFS or DFS 로 트리에서 가장 먼 두 점을 찾는 문제
 *  Hint: A 에서 가장 먼 B 를 구함 -> B 에서 가장 먼 노드를 구함 = 최대 길이
 */

public class BOJ1167 {
    static int v;
    static ArrayList<ArrayList<Tree>> list;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        v = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < v; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            String s;
            while (!(s = st.nextToken()).equals("-1")) {
                int to = Integer.parseInt(s);
                int dist = Integer.parseInt(st.nextToken());
                list.get(node).add(new Tree(to, dist));
            }
        }

        int longestLengthNode = bfs(1);
        int ansIndex = bfs(longestLengthNode);

        bw.write(String.valueOf(dist[ansIndex]));
        bw.close();
        br.close();
    }

    // 가장 멀리 있는 노드의 index 를 구해서 return
    static int bfs(int start) {
        int max = 0;
        dist = new int[v + 1];
        Queue<Tree> q = new LinkedList<>();
        boolean[] visited = new boolean[v + 1];
        q.offer(new Tree(start, 0));

        // BFS
        while (!q.isEmpty()) {
            Tree cur = q.poll();

            if (visited[cur.to]) {
                continue;
            }
            visited[cur.to] = true;

            for (Tree next : list.get(cur.to)) {
                if (!visited[next.to]) {
                    q.offer(next);
                    dist[next.to] = dist[cur.to] + next.dist;
                    max = Math.max(max, dist[next.to]);
                }
            }
        }

        int maxIdx = 0;
        for (int i = 1; i <= v; i++) {
            if (dist[i] == max) {
                maxIdx = i;
                break;
            }
        }
        return maxIdx;
    }

    static class Tree {
        int to, dist;

        Tree(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
}
