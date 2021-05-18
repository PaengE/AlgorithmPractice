import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *  No.2109: 순회 강연
 *  URL: https://www.acmicpc.net/problem/2109
 *  Hint: Greedy
 */

public class BOJ2109 {
    static PriorityQueue<Lecture> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int maxDay = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            maxDay = Math.max(maxDay, d);
            pq.offer(new Lecture(d, p));
        }

        int[] cost = new int[maxDay + 1];
        while (!pq.isEmpty()) {
            Lecture lec = pq.poll();

            // 가능한 최대 일자부터 역순으로 강의를 할 수 있는지 체크한다.
            for (int i = lec.day; i > 0; i--) {
                if (cost[i] < lec.pay) {
                    cost[i] = lec.pay;
                    break;
                }
            }
        }

        bw.write(String.valueOf(Arrays.stream(cost).sum()));
        bw.close();
        br.close();
    }

    // pay 기준 내림차순으로 하되, pay가 같은 경우에는 day 기준 오름차순으로 정렬한다.
    static class Lecture implements Comparable<Lecture>{
        int day, pay;

        Lecture(int day, int pay) {
            this.day = day;
            this.pay = pay;
        }

        @Override
        public int compareTo(Lecture o) {
            if (this.pay == o.pay) {
                return this.day - o.day;

            }

            return o.pay - this.pay;
        }
    }
}
