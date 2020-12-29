import java.io.*;
import java.util.*;

/**
 * no.1260: DFS 와 BFS
 * description: DFS 와 BFS 를 다루는 문제
 * hint: DFS 는 stack 으로 구현함,(재귀, 인접리스트 등으로도 풀 수 있음)
 *       BFS 는 queue 으로 구현함
 */

public class BOJ1260 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        int[][] arr = new int[m][2];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        dfs(arr, n, m, v);
        bfs(arr, n, m, v);

        br.close();
        bw.close();
    }

    // DFS: Stack 활용
    static void dfs(int[][] arr, int n, int m, int v) throws IOException {
        Stack<Integer> stk = new Stack<>();
        int current = v;
        boolean[] visit = new boolean[n + 1];

        // 시작점
        stk.push(0);
        stk.push(current);

        while (stk.peek() != 0) {
            // 연결된 노드 중 가장 작은 번호의 노드를 탐색
            int next = Integer.MAX_VALUE;
            for (int i = 0; i < m; i++) {
                if (current == arr[i][0]) {
                    if (!visit[arr[i][1]]) {
                        next = Math.min(next, arr[i][1]);
                    }
                } else if (current == arr[i][1]) {
                    if (!visit[arr[i][0]]) {
                        next = Math.min(next, arr[i][0]);
                    }
                }
            }
            // 방문하지 않았다면, 방문 후 표시
            if (!visit[current]) {
                bw.write(current + " ");
                visit[current] = true;
            }
            // next 가 갱신이 안되었으므로 더 이상 연결된 노드가 없는 것.
            if (next == Integer.MAX_VALUE) {
                stk.pop();
                current = stk.peek();
            }
            // 연결된 노드가 더있으면 stack 에 push
            else {
                stk.push(next);
                current = next;
            }
        }
        bw.newLine();
        bw.flush();
    }
    // BFS: Queue 활용
    static void bfs(int[][] arr, int n, int m, int v) throws IOException {
        Queue<Integer> q = new LinkedList<>();
        boolean end = false;
        int current = v;
        boolean[] visit = new boolean[n + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 시작점
        q.offer(current);

        while (!end) {
            // 연결된 노드를 모두 우선순위 큐에 삽입(작은 번호 노드 우선)
            for (int i = 0; i < m; i++) {
                if (current == arr[i][0]) {
                    if (!visit[arr[i][1]]) {
                        pq.offer(arr[i][1]);
                    }
                } else if (current == arr[i][1]) {
                    if (!visit[arr[i][0]]) {
                        pq.offer(arr[i][0]);
                    }
                }
            }
            // 방문하지 않았다면, 방문 후 표시
            if (!visit[current]) {
                bw.write(current + " ");
                visit[current] = true;
            }
            // 현재 노드에 연결된 모든 노드를 큐에 삽입
            while (!pq.isEmpty()) {
                q.offer(pq.poll());
            }

            // 더이상 진행 할 수 없으면 end
            if (q.isEmpty()) {
                end = true;
            } else {
                current = q.poll();
            }
        }
        bw.flush();
    }
}
