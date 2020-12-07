import java.io.*;
import java.util.*;

/**
 *  No.13344: Chess Tournament
 *  URL: https://www.acmicpc.net/problem/13344
 *  Description: 부등호에 등호까지 들어간 문제
 *  Hint: Topological Sort + Union-find
 */

public class BOJ13344 {
    static int[] parents;   // 부모 노드를 저장하는 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean consistent = true;

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n];

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        parents = new int[n];
        Arrays.fill(parents, -1);
        Queue<int[]> tmp = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            char op = st.nextToken().charAt(0);
            int b = Integer.parseInt(st.nextToken());

            switch (op) {
                // 간선 정보를 저장
                case '>':
                    tmp.offer(new int[]{a, b});
                    break;

                // = 이면 같은 집합으로 묶음
                case '=':
                    union(a, b);
                    break;
            }
        }

        // 부등호 관계인데 부모가 같으면 inconsistent
        // 다르면 부모끼리 간선을 연결
        while (!tmp.isEmpty()) {
            int[] t = tmp.poll();
            int a = find(t[0]);
            int b = find(t[1]);
            if (a == b) {
                consistent = false;
                break;
            }
            graph.get(b).add(a);
            inDegree[a] += 1;
        }

        // inDegree 가 0 인 숫자 enqueue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        // Topological Sort
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Integer next : graph.get(cur)) {
                inDegree[next] += -1;
                if (inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        // 위상정렬 후에도 inDegree 가 남아있을 경우
        for (int i = 0; i < n; i++) {
            if (inDegree[i] != 0) {
                consistent = false;
                break;
            }
        }

        if (consistent) {
            bw.write("consistent");
        } else {
            bw.write("inconsistent");
        }
        bw.close();
        br.close();
    }


    // union-find function
    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot != bRoot) {
            parents[bRoot] = aRoot;
        }
    }
    static int find(int a) {
        if (parents[a] < 0) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }
}
