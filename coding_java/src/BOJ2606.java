import java.io.*;
import java.util.*;

public class BOJ2606 {
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        LinkedList<Integer>[] adjList = new LinkedList[n + 1];
        for (int i = 0; i <= n; i++) {
            adjList[i] = new LinkedList<Integer>();
        }
        boolean[] visited = new boolean[n + 1];

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            adjList[v1].add(v2);
            adjList[v2].add(v1);
        }
        for (int i = 1; i <= n; i++) {
            Collections.sort(adjList[i]);
        }

        dfs(adjList, visited, 1);

        bw.write(String.valueOf(count));
        bw.flush();
        br.close();
        bw.close();
    }

    static void dfs(LinkedList<Integer>[] adjList, boolean[] visited, int v) {
        visited[v] = true;

        Iterator<Integer> iter = adjList[v].listIterator();
        while (iter.hasNext()) {
            int nextVisit = iter.next();
            if (!visited[nextVisit]) {
                count++;
                dfs(adjList, visited, nextVisit);
            }
        }
    }
}
