import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ21773 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        PriorityQueue<Job> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            int prior = Integer.parseInt(st.nextToken());
            pq.offer(new Job(idx, time, prior));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            if (pq.isEmpty()) {
                break;
            }

            Job cur = pq.poll();
            sb.append(cur.idx + "\n");

            if (cur.execTime == 1) {
                continue;
            }

            pq.offer(new Job(cur.idx, cur.execTime - 1, cur.priority - 1));
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }

    static class Job implements Comparable<Job>{
        int idx, execTime, priority;

        Job(int idx, int execTime, int priority) {
            this.idx = idx;
            this.execTime = execTime;
            this.priority = priority;
        }

        @Override
        public int compareTo(Job o) {
            if (this.priority == o.priority) {
                return this.idx - o.idx;
            }
            return o.priority - this.priority;
        }
    }
}
