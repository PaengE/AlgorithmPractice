import java.io.*;
import java.util.*;

/**
 *  No.16940: BFS 스페셜 저지
 *  URL: https://www.acmicpc.net/problem/16940
 *  Hint: BFS 순서가 맞는지를 판단
 */

public class BOJ16940 {
    static int n;
    static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        // 그래프 구성
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        ArrayList<Integer> arr = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        if (isPossible(1, arr)) {
            bw.write("1");
        } else {
            bw.write("0");
        }

        bw.close();
        br.close();
    }

    static boolean isPossible(int start, ArrayList<Integer> arr) {
        // 시작이 1번이 아니면 false
        if (arr.get(0) != start) {
            return false;
        }

        HashSet<Integer> set = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        q.offer(start);
        visited[start] = true;

        int idx = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();

            // 현재 노드와 연결된 모드 노드들을 set에 추가
            for (int next : list[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    set.add(next);
                }
            }

            for (int i = 0; i < set.size(); i++) {
                if (set.remove(arr.get(idx))) { // 방문 순서에 있는 노드를 방문할 수 있으면 방분
                    q.offer(arr.get(idx++));
                } else {    // 방문할 수 없다면 잘못된 방문 순서
                    return false;
                }
            }
        }

        return true;
    }
}
