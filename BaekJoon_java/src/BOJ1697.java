import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * no.1697: 숨바꼭질
 * description: 또다른 BFS 최단거리 연습문제
 * hint: 1차원 배열을 이용한 BFS 문제
 */

public class BOJ1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[Math.max(n, k) * 2 + 1];
        boolean[] visited = new boolean[Math.max(n, k) * 2 + 1];

        bfs(n, k, arr, visited);

        bw.write(String.valueOf(arr[k]));
        bw.flush();
        br.close();
        bw.close();

    }

    static void bfs(int n, int k, int[] arr, boolean[] visited) {
        Queue<Integer> q = new LinkedList<Integer>();
        int limit = Math.max(n, k);
        q.offer(n);
        visited[n] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 0; i < 3; i++) {
                int next = 0;
                if (i == 0) {
                    next = cur - 1;
                } else if (i == 1) {
                    next = cur + 1;
                } else {
                    next = 2 * cur;
                }

                if (next >= 0 && next < 2 * limit && !visited[next]) {
                    q.offer(next);
                    visited[next] = true;
                    arr[next] = arr[cur] + 1;
                }
            }
        }
    }
}
