import java.io.*;
import java.util.*;

/**
 *  No.1202: 보석 도둑
 *  URL: https://www.acmicpc.net/problem/1202
 *  Hint: PriorityQueue + Greedy
 */

public class BOJ1202 {
    static int n, m;
    static int[] bags;
    static Jewelry[] jewelries;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        jewelries = new Jewelry[n];
        bags = new int[m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewelries[i] = new Jewelry(w, v);
        }
        for (int i = 0; i < m; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewelries);
        Arrays.sort(bags);

        long answer = 0;
        int bagIdx = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < m; i++) {
            // 현재 가방에 넣을 수 있는 보석들의 가치를 다 pq에 넣음
            while (bagIdx < n && jewelries[bagIdx].weight <= bags[i]) {
                pq.add(jewelries[bagIdx++].value);
            }

            // 개 중 가치가 가장 높은 것을 뽑음
            // 가방의 크기가 작은 것부터 수행했으므로 현재 pq에 담겨있는 보석들은 다음 가방에도 넣을 수 있음
            if (!pq.isEmpty()) {
                answer += pq.poll();
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    static class Jewelry implements Comparable<Jewelry>{
        int weight, value;

        Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewelry o) {
            return this.weight - o.weight;
        }
    }
}
