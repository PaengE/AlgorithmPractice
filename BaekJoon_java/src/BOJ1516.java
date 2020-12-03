import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  No.1516: 게임 개발
 *  URL: https://www.acmicpc.net/problem/1516
 *  Hint: 위상정렬(Topological Sort)
 *
 *  1. List 를 이용하여 값을 저장해준다.(ArrayList, LinkedList 둘다 사용 가능)
 *  2. inDegree 가 0인 값을 큐에 모두 집어 넣는다.
 *  3. 큐가 빌때까지 0인 값을 하나씩 빼서 그 다음 값을 다시 큐에 집어 넣는다. (단 그때 화살표 값인 Indegree 를 하나씩 줄인다.)
 *  4. 큐에서 빼고 난 후에 자기 자신까지 가장 오래 걸린 시간을 배열에 저장해야한다. (이전 건물들을 모두 지어야 자기 건물을 지을 수 있기 때문)
 */

public class BOJ1516 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] list = new ArrayList[n + 1];
        int[] inDegree = new int[n + 1];
        int[] value = new int[n + 1];
        int[] result = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            value[i] = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp != -1) {
                    inDegree[i] += 1;
                    list[tmp].add(i);
                }
            }
        }

        // inDegree 가 0 인 수를 큐에 add
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
                result[i] = value[i];
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < list[cur].size(); i++) {
                int next = list[cur].get(i);
                inDegree[next] += -1;

                // 자신의 건물을 짓기 전 가장 오래 걸린 시간을 저장
                result[next] = Math.max(result[next], result[cur] + value[next]);

                if (inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(result[i]).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
