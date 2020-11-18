import java.io.*;
import java.util.PriorityQueue;

/**
 * no.1927 : 최소 힙
 * title : 최솟값을 빠르게 뽑는 자료구조를 배우는 문제
 * hint : PriorityQueue 사용
 */

public class BOJ1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Integer> q = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine());
        int val = 0;


        for (int i = 0; i < n; i++) {
            val = Integer.parseInt(br.readLine());
            if (val != 0) {
                q.add(val);
            } else if (q.isEmpty()) {
                bw.write("0\n");
            } else {
                bw.write(q.poll() + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
