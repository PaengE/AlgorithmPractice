import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * No.10217: KCM Travel
 * description: 간선을 사용하는 비용과 예산 제약이 있을 때 다이나믹 프로그래밍을 활용하여 푸는 문제
 * hint: PriorityQueue + Dijkstra + DP 사용
 */

public class BOJ10217 {
    static int n, m, k;
    static long[][] dp;
    static ArrayList<AirPlane>[] list;
    static PriorityQueue<AirPlane> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            long answer = Integer.MAX_VALUE;
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            dp = new long[n + 1][m+ 1];
            list = new ArrayList[n + 1];
            for (int j = 1; j <= n; j++) {
                list[j] = new ArrayList<AirPlane>();
                Arrays.fill(dp[j], Integer.MAX_VALUE);
            }

            // 노드 추가
            for (int j = 1; j <= k; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                list[start].add(new AirPlane(end, cost, time));
            }

            pq = new PriorityQueue<AirPlane>();
            dp[1][0] = 0;
            // 시작 노드 1
            pq.add(new AirPlane(1, 0, 0));

            // BFS 시작
            while (!pq.isEmpty()) {
                AirPlane cur = pq.poll();

                // 목표노드 진입 시 종료
                if (cur.end == n) {
                    answer = Math.min(answer, cur.time);
                    break;
                }

                for (AirPlane next : list[cur.end]) {
                    int sumCost = cur.cost + next.cost;
                    // 비용 초과 시
                    if (sumCost > m) {
                        continue;
                    }

                    int sumTime = cur.time + next.time;
                    if (dp[next.end][sumCost] > sumTime) {
                        dp[next.end][sumCost] = sumTime;
                        pq.offer(new AirPlane(next.end, sumCost, sumTime));
                    }
                }
            }

            if (answer == Integer.MAX_VALUE) {
                bw.write("Poor KCM\n");
            } else {
                bw.write(String.valueOf(answer) + "\n");
            }

        }
        bw.flush();
        br.close();
        bw.close();

    }
    static class AirPlane implements Comparable<AirPlane> {
        int end;
        int cost;
        int time;

        AirPlane(int end, int cost, int time) {
            this.end = end;
            this.cost = cost;
            this.time = time;
        }

        @Override
        public int compareTo(AirPlane o) {
            // 시간 순으로(시간이 같으면 비용 순으로)
            if (this.time == o.time) {
                return cost - o.cost;
            } else {
                return this.time - o.time;
            }
        }
    }
}

