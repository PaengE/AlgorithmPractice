import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * no.2164 : 카드 2
 * hint : LinkedList를 이용한 Queue 사용
 */

public class BOJ2164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Queue<Integer> q = new LinkedList<Integer>();

        for (int i = 1; i <= n; i++) {
            q.offer(i);
        }
        while (q.size() > 1) {
            q.remove();
            q.offer(q.poll());
        }

        bw.write(q.peek() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
