import java.io.*;
import java.util.*;

/**
 *  No.3665: 최종 순위
 *  URL: https://www.acmicpc.net/problem/3665
 *  Description: 간선을 직접 정의한 다음 위상정렬을 하는 문제
 *  Hint: Queue 를 이용한 Topological Sort
 */

public class BOJ3665_TopologicalSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // inDegree, map 생성
            boolean[][] map = new boolean[n + 1][n + 1];
            int[] inDegree = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                int a = arr[i];

                for (int j = 1; j < i; j++) {
                    int b = arr[j];
                    map[a][b] = true;
                    inDegree[a] += 1;
                }
            }

            // 순위 바꾸기
            int m = Integer.parseInt(br.readLine());
            for (int i = 1; i <= m; i++) {
                st = new StringTokenizer(br.readLine());
                int team1 = Integer.parseInt(st.nextToken());
                int team2 = Integer.parseInt(st.nextToken());

                if (map[team1][team2]) {
                    inDegree[team1] += -1;
                    inDegree[team2] += 1;
                } else {
                    inDegree[team1] += 1;
                    inDegree[team2] += -1;
                }
                map[team1][team2] = !map[team1][team2];
                map[team2][team1] = !map[team2][team1];
            }

            // 위상정렬
            Queue<Integer> q = new LinkedList<Integer>();
            for (int i = 1; i <= n; i++) {
                if (inDegree[i] == 0) {
                    q.offer(i);
                }
            }

            int loopCnt = 0;
            StringBuilder tsb = new StringBuilder();
            while(!q.isEmpty()) {
                // 큐 상태 체크 (순위 데이터이기 때문에 큐에 데이터가 2개 이상 동시에 존재할 수 없다.)
                if (q.size() >= 2) {
                    break;
                }

                loopCnt += 1;
                int cur = q.poll();
                tsb.append(cur).append(" ");

                for (int i = 1; i <= n; i++) {
                    if (map[i][cur]) {
                        inDegree[i] += -1;
                        if (inDegree[i] == 0) {
                            q.add(i);
                        }
                    }
                }
            }
            if (loopCnt == n) {
                sb.append(tsb.toString()).append("\n");
            } else {
                sb.append("IMPOSSIBLE\n");
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
