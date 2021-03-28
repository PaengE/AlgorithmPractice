import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.9466: 텀 프로젝트
 *  URL: https://www.acmicpc.net/problem/9466
 *  Hint: DFS 로 사이클이 있는지 판단, 시간초과가 난다면 boolean 배열을 전역변수로 선언해보길..
 */

public class BOJ9466 {
    static int n, ans;
    static int[] list;
    static boolean[] visited, finished;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            ans = 0;
            list = new int[n + 1];
            visited = new boolean[n + 1];
            finished = new boolean[n + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= n; i++) {
                list[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                dfs(i);
            }

            sb.append((n - ans) + "\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void dfs(int cur) {
        if (visited[cur]) {
            return;
        }

        visited[cur] = true;
        int next = list[cur];

        // next가 방문한 적이 있다는 것은 사이클이 존재한다는 뜻
        if (!visited[next]) {
            dfs(next);
        } else {
            if (!finished[next]) {
                ans++;
                // next 는 무조건 사이클의 일부이므로 next를 시작으로 함
                for (int i = next; i != cur ; i = list[i]) {
                    ans++;
                }
            }
        }

        // 팀을 꾸리던 못꾸리던 한번 탐색된 노드는 다음번에 검사할 필요가 없다
        finished[cur] = true;
    }
}
