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

        stk.push(0);
        stk.push(current);

        while (stk.peek() != 0) {
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
            if (!visit[current]) {
                bw.write(current + " ");
                visit[current] = true;
            }
            if (next == Integer.MAX_VALUE) {
                stk.pop();
                current = stk.peek();
            } else {
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

        q.offer(current);

        while (!end) {
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
            if (!visit[current]) {
                bw.write(current + " ");
                visit[current] = true;
            }
            while (!pq.isEmpty()) {
                q.offer(pq.poll());
            }
            if (q.isEmpty()) {
                end = true;
            } else {
                current = q.poll();
            }
        }
        bw.flush();
    }
}
