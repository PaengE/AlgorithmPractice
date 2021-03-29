import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11437 {
    static int n, m;
    static ArrayList<Integer>[] graph;
    static int[] depth, parent;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        init(n);

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        dfs(1, 1);  // root 의 깊이를 0이라고 가정

        m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(findCommonParent(a, b) + "\n");
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }

    static void init(int n) {
        parent = new int[n + 1];
        depth = new int[n + 1];
        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    static int findCommonParent(int a, int b) {
        // a, b의 depth 맞추기
        int depthDiff = Math.abs(depth[a] - depth[b]);
        if (depth[a] < depth[b]) {
            for (int i = 0; i < depthDiff; i++) {
                b = parent[b];
            }
        } else {
            for (int i = 0; i < depthDiff; i++) {
                a = parent[a];
            }
        }

        // depth를 하나씩 줄이면서 같은 값인지(조상이 같은지) 검사
        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        // a, b의 공통 조상을 리턴
        return a;
    }

    // 각 노드의 부모와 레벨을 탐색
    static void dfs(int cur, int level) {
        for (int next : graph[cur]) {
            if (parent[cur] != next) {
                parent[next] = cur;
                depth[next] = level;
                dfs(next, level + 1);
                visited[next] = false;
            }
        }
    }
}
