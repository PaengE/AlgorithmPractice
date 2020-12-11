import java.io.*;
import java.util.*;

/**
 *  No.2213: 트리의 독립집합
 *  URL: https://www.acmicpc.net/problem/2213
 *  Description: 트리의 최대 독립 집합을 구하는 문제.
 *               일반적인 그래프에서는 NP-hard 문제로, 효율적인 알고리즘이 알려지지 않음
 *  Hint: Tree + DP + Tracing
 */

public class BOJ2213 {
    static int n;
    static int[] arr, dp, selected;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        dp = new int[n + 1];
        selected = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
            tree.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }

        buildTree(1, -1);

        int t1 = dp(1, 0);
        int t2 = dp(1, 1);
        if (t1 < t2) {
            selected[1] = 1;
        } else {
            selected[1] = 0;
        }

        bw.write(String.valueOf(Math.max(t1, t2)));
        bw.newLine();

        trace(1, selected[1]);

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append(" ");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();


    }

    // 트리 구성
    static void buildTree(int cur, int parent) {
        for (int child : list.get(cur)) {
            if (child != parent) {
                tree.get(cur).add(child);
                buildTree(child, cur);
            }
        }
    }

    // 최대 독립 집합 구하기
    static int dp(int cur, int include) {
        int ans = 0;
        // 현재 노드를 포함한다면
        if (include == 1) {
            for (int next : tree.get(cur)) {
                ans += dp(next, 0); // 다음 노드를 포함 안함
            }
            return ans + arr[cur];
        }
        // 현재 노드를 포함하지 않는다면
        else {
            for (int next : tree.get(cur)) {
                int t1 = dp(next, 0);
                int t2 = dp(next, 1);

                // 다음 노드를 포함할지 안할지를 비교
                if (t1 < t2) {  // 포함한 것이 더 크면
                    selected[next] = 1;
                } else {        // 포함하지 않은 것이 더 크면
                    selected[next] = 0;
                }
                ans += Math.max(t1, t2);
            }
            return ans;
        }
    }

    // 최대 독립 집합의 노드 찾기
    static void trace(int cur, int include) {
        if (include == 1) {
            // 노드가 최대 독립 집합에 포함되면 우선순위 큐에 삽입
            pq.offer(cur);
            for (int next : tree.get(cur)) {
                trace(next, 0);
            }
        } else if (include == 0) {
            for (int next : tree.get(cur)) {
                trace(next, selected[next]);
            }
        }
    }
}
